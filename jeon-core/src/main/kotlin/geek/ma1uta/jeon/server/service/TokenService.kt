package geek.ma1uta.jeon.server.service

import geek.ma1uta.jeon.server.Query
import geek.ma1uta.jeon.server.model.Device
import geek.ma1uta.jeon.server.model.Token
import geek.ma1uta.jeon.server.model.User
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Service
import java.sql.ResultSet

@Service
class TokenService(val query: Query, val template: NamedParameterJdbcTemplate) {

    fun insertOrUpdate(token: Token) {
        template.update(query.token.insertOrUpdate,
                mutableMapOf(Pair("token", token.token), Pair("device_id", token.device.deviceId), Pair("user_id", token.user.id)))
    }

    fun deleteByUserAndDevice(user: User, device: Device) {
        template.update(query.token.deleteByUserAndDevice, mutableMapOf(Pair("user_id", user.id), Pair("device_id", device.deviceId)))
    }

    fun findByToken(token: String): Token? = try {
        template.queryForObject(query.token.findByToken, mutableMapOf(Pair("token", token)), TokenRowMapper())
    } catch (e: EmptyResultDataAccessException) {
        null
    }

    fun deleteByToken(token: Token) = template.update(query.token.deleteByToken, mutableMapOf(Pair("token", token.token),
            Pair("device_id", token.device.deviceId), Pair("user_id", token.user.id)))

    class TokenRowMapper : RowMapper<Token> {
        override fun mapRow(rs: ResultSet?, rowNum: Int): Token {
            val user = User(rs!!.getString("u.id"), "",
                    rs.getString("u.display_name"),
                    rs.getString("u.avatar_url"),
                    rs.getString("u.kind"))

            val device = Device(rs.getString("d.deviceId"), user,
                    rs.getString("display_name"),
                    rs.getString("last_seen_ip"),
                    rs.getLong("last_seen_ts"))

            return Token(rs.getString("token"), device, user)
        }
    }
}
