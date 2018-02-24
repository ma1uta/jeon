package io.github.ma1uta.jeon.server.service

import io.github.ma1uta.jeon.server.model.User
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.sql.ResultSet

@Service
class UserService(val query: io.github.ma1uta.jeon.server.Query, val template: NamedParameterJdbcTemplate, val passwordEncoder: BCryptPasswordEncoder) {

    fun read(userId: String): User? {
        return try {
            template.queryForObject(query.user.read, mutableMapOf(Pair("id", userId)), UserRowMapper())
        } catch (e: EmptyResultDataAccessException) {
            null
        }
    }

    fun insert(user: User) {
        template.update(query.user.insert, mutableMapOf(Pair("id", user.id),
                Pair("password", passwordEncoder.encode(user.password.trim())),
                Pair("display_name", user.displayName),
                Pair("avatar_url", user.avatarUrl),
                Pair("kind", user.kind)))
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
