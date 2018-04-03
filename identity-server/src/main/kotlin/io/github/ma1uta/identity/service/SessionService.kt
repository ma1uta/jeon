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
import java.text.MessageFormat
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.UUID

@Service
class SessionService(val query: Query, val template: NamedParameterJdbcTemplate, val mailSender: JavaMailSender,
                     val props: IdentityProperties, val associationService: AssociationService,
                     val invitationService: InvitationService) {

    /**
     * Create new session.
     *
     * @param clientSecret client secret.
     * @param email client email.
     * @param nextLink url to open.
     * @param sendAttempt attempt
     */
    fun create(clientSecret: String, email: String, sendAttempt: Long?, nextLink: String?): String {
        var create = true
        if (sendAttempt != null) {
            val sessions =
                    template.query(query.session.findBySecretEmail,
                            mutableMapOf(Pair("client_secret", clientSecret), Pair("address", email), Pair("medium", "email")),
                            SessionRowMapper())
            create = sessions.any { it.sendAttempt == null || it.sendAttempt < sendAttempt }
        }

        if (create) {
            val sid = UUID.randomUUID().toString()
            val token = UUID.randomUUID().toString()
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
            message.text = MessageFormat.format(props.email.body, token, clientSecret, sid,
                    validationUrl(token, clientSecret, sid))
            mailSender.send(message)
            return sid
        } else {
            return ""
        }
    }

    /**
     * Validate existing session.
     *
     * @param token validation token.
     * @param clientSecret client secret.
     * @param sid session id.
     */
    fun validate(token: String, clientSecret: String, sid: String): String? {
        val sessions =
                template.query(query.session.findBySecretTokenSid,
                        mutableMapOf(Pair("client_secret", clientSecret), Pair("token", token), Pair("sid", sid)),
                        SessionRowMapper())
        when {
            sessions.isEmpty() -> throw MatrixException(ErrorResponse.Code.M_THREEPID_NOT_FOUND, "Not found 3pid for this email.")
            sessions.size > 1 -> throw MatrixException(M_INTERNAL, "Too many sessions with the same id.")
            else -> {
                template.update(query.session.validate, mutableMapOf(Pair("sid", sessions[0].sid)))
                return sessions[0].nextLink
            }
        }
    }

    /**
     * Find validated session.
     *
     * @param sid session id.
     * @param clientSecret client secret.
     */
    fun getSession(sid: String, clientSecret: String): ValidationResponse {
        val response = ValidationResponse()
        val sessions = template.query(query.session.findBySecretSid, mutableMapOf(Pair("client_secret", clientSecret), Pair("sid", sid)),
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

    /**
     * Bind mxid and the 3pid.
     *
     * @param sid session id.
     * @param mxid matrix id.
     * @param clientSecret client secret.
     */
    fun publish(sid: String, clientSecret: String, mxid: String): Boolean {
        val sessions = template.query(query.session.findBySecretSid, mutableMapOf(Pair("client_secret", clientSecret), Pair("sid", sid)),
                SessionRowMapper())
        when {
            sessions.isEmpty() || sessions[0].validated == null -> throw MatrixException(ErrorResponse.Code.M_SESSION_NOT_VALIDATED,
                    "This validation session has not yet been completed")
            sessions.size > 1 -> throw MatrixException(ErrorResponse.Code.M_SESSION_NOT_VALIDATED, "Too many sessions")
            else -> {
                associationService.create(sessions[0], mxid)
                invitationService.sendInvite(sessions[0].medium, sessions[0].address, mxid)
            }
        }
        return true
    }

    /**
     * Url for session validation.
     *
     * @param clientSecret client secret.
     * @param sid session id.
     * @param token session token.
     */
    fun validationUrl(token: String, clientSecret: String, sid: String): String {
        return "https://${props.hostname}/_matrix/identity/api/v1/validate/email/submitToken?" +
                "token=${URLEncoder.encode(token, "UTF-8")}" +
                "&client_secret=${URLEncoder.encode(clientSecret, "UTF-8")}" +
                "&sid=${URLEncoder.encode(sid, "UTF-8")}"
    }

    /**
     * Delete expired sessions.
     */
    @Scheduled(cron = "%{identity.session.expire}")
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
