package io.github.ma1uta.identity.service

import io.github.ma1uta.identity.IdentityProperties
import io.github.ma1uta.identity.jdbc.Query
import io.github.ma1uta.identity.model.Session
import io.github.ma1uta.jeon.exception.M_INTERNAL
import io.github.ma1uta.jeon.exception.MatrixException
import io.github.ma1uta.matrix.ErrorResponse
import io.github.ma1uta.matrix.identity.model.validation.ValidationResponse
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.net.URLEncoder
import java.sql.ResultSet
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*

@Service
class SessionService(val query: Query, val template: NamedParameterJdbcTemplate, val mailSender: JavaMailSender,
                     val props: IdentityProperties, val associationService: AssociationService) {

    fun create(clientSecret: String, email: String, sendAttempt: Long?, nextLink: String?): String {
        val sid = UUID.randomUUID().toString()
        val token = UUID.randomUUID().toString()

        var create = true
        if (sendAttempt != null) {
            val sessions =
                    template.query(query.session.findBySecretAndEmail,
                            mutableMapOf(Pair("client_secret", clientSecret), Pair("address", email), Pair("medium", "email")),
                            SessionRowMapper())
            if (sessions.size == 1) {
                val savedAttempt = sessions[0].sendAttempt
                create = savedAttempt != null && savedAttempt < sendAttempt
            } else if (sessions.size > 1) {
                create = sessions.any { it.sendAttempt == null || sendAttempt > it.sendAttempt }
            }
        }

        if (create) {
            template.update(query.session.insertOrUpdate, mutableMapOf(Pair("sid", sid),
                    Pair("token", token),
                    Pair("client_secret", clientSecret),
                    Pair("address", email),
                    Pair("send_attempt", sendAttempt),
                    Pair("next_link", nextLink)))

            val message = SimpleMailMessage()
            message.from = props.email.hostname
            message.setTo(email)
            message.subject = props.email.subject
            message.text = String.format(props.email.body, token, clientSecret, sid,
                    validationUrl(token, clientSecret, sid))
            mailSender.send(message)
            return sid
        } else {
            return ""
        }
    }

    fun validate(token: String, clientSecret: String, sid: String) {
        val sessions =
                template.query(query.session.findBySecretTokenSid,
                        mutableMapOf(Pair("client_secret", clientSecret), Pair("token", token), Pair("sid", sid)),
                        SessionRowMapper())
        when {
            sessions.isEmpty() -> throw MatrixException(ErrorResponse.Code.M_THREEPID_NOT_FOUND, "Not found threepid for this email.")
            sessions.size > 1 -> throw MatrixException(M_INTERNAL, "Too many sessions with the same id.")
            else -> template.update(query.session.validate, mutableMapOf(Pair("sid", sessions[0].sid)))
        }
    }

    fun getSession(sid: String, clientSecret: String): ValidationResponse {
        val response = ValidationResponse()
        val sessions = template.query(query.session.findBySecretAndSid, mutableMapOf(Pair("client_secret", clientSecret), Pair("sid", sid)),
                SessionRowMapper())
        when {
            sessions.isEmpty() || sessions[0].validated == null -> throw MatrixException(ErrorResponse.Code.M_SESSION_NOT_VALIDATED,
                    "This validation session has not yet been completed")
            sessions.size > 1 -> throw MatrixException(ErrorResponse.Code.M_SESSION_NOT_VALIDATED, "Too many sessions")
            else -> {
                response.address = sessions[0].address
                response.medium = sessions[0].medium
                val offset = ZoneOffset.systemDefault().rules.getOffset(LocalDateTime.now())
                response.validatedAt = sessions[0].validated!!.toEpochSecond(offset)
            }
        }
        return response
    }

    fun publish(sid: String, clientSecret: String, mxid: String): Boolean {
        val sessions = template.query(query.session.findBySecretAndSid, mutableMapOf(Pair("client_secret", clientSecret), Pair("sid", sid)),
                SessionRowMapper())
        when {
            sessions.isEmpty() || sessions[0].validated == null -> throw MatrixException(ErrorResponse.Code.M_SESSION_NOT_VALIDATED,
                    "This validation session has not yet been completed")
            sessions.size > 1 -> throw MatrixException(ErrorResponse.Code.M_SESSION_NOT_VALIDATED, "Too many sessions")
            else -> associationService.create(sessions[0], mxid)
        }
        return true
    }

    fun validationUrl(token: String, clientSecret: String, sid: String): String {
        return "https://${props.hostname}/_matrix/identity/api/v1/validate/email/submitToken?" +
                "token=${URLEncoder.encode(token, "UTF-8")}" +
                "&client_secret=${URLEncoder.encode(clientSecret, "UTF-8")}" +
                "&sid=${URLEncoder.encode(sid, "UTF-8")}"
    }

    @Scheduled(cron = "0 0 0 * * ?")
    fun cleanup() {
        template.update(query.session.deleteOldest, mutableMapOf<String, Any>())
    }

    class SessionRowMapper : RowMapper<Session> {
        override fun mapRow(rs: ResultSet?, rowNum: Int) = Session(rs!!.getString("sid"),
                rs.getString("token"),
                rs.getString("client_secret"),
                rs.getString("address"),
                rs.getString("medium"),
                rs.getLong("send_attempt"),
                rs.getString("next_link"),
                rs.getTimestamp("validated").toLocalDateTime())
    }
}
