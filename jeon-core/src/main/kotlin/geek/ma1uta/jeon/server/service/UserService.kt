package geek.ma1uta.jeon.server.service

import geek.ma1uta.jeon.server.Query
import geek.ma1uta.jeon.server.model.User
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Service
import java.sql.ResultSet

@Service
class UserService(val query: Query, val template: NamedParameterJdbcTemplate) {

    fun read(userId: String): User? {
        return try {
            template.queryForObject(query.user.read, mutableMapOf(Pair("id", userId)), UserRowMapper())
        } catch (e: EmptyResultDataAccessException) {
            null
        }
    }

    class UserRowMapper : RowMapper<User> {
        override fun mapRow(rs: ResultSet?, rowNum: Int) = User(
                rs!!.getString("id"),
                rs.getString("password"),
                rs.getString("display_name"),
                rs.getString("avatar_url")
        )
    }
}
