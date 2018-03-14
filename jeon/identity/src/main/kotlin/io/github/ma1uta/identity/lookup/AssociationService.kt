package io.github.ma1uta.identity.lookup

import io.github.ma1uta.identity.IdentityProperties
import io.github.ma1uta.identity.jdbc.Query
import io.github.ma1uta.identity.model.Association
import io.github.ma1uta.identity.model.Session
import io.github.ma1uta.jeon.exception.M_INTERNAL
import io.github.ma1uta.jeon.exception.MatrixException
import io.github.ma1uta.matrix.ErrorResponse
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.net.URLEncoder
import java.sql.ResultSet
import java.util.*

@Service
class AssociationService(val query: Query, val template: NamedParameterJdbcTemplate, val mailSender: JavaMailSender,
                         val props: IdentityProperties) {

    fun create(clientSecret: String, email: String, sendAttempt: Long?, nextLink: String?): String {
        val sid = UUID.randomUUID().toString()
        val token = UUID.randomUUID().toString()

        var create = true
        if (sendAttempt != null) {
            val sessions =
                    template.query(query.session.findBySecretAndEmail,
                            mutableMapOf(Pair("client_secret", clientSecret), Pair("email", email)),
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
                    Pair("email", email),
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
                rs.getString("email"),
                rs.getLong("send_attempt"),
                rs.getString("next_link"),
                rs.getTimestamp("validated").toLocalDateTime())
    }

    class AssociationRowMapper : RowMapper<Association> {
        override fun mapRow(rs: ResultSet?, rowNum: Int) = Association(rs!!.getString("address"),
                rs.getString("medium"),
                rs.getString("mxid"),
                rs.getLong("created"),
                rs.getLong("expired"),
                rs.getLong("ts"))
    }
}
