package geek.ma1uta.jeon.server.auth

import geek.ma1uta.jeon.server.Query
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Component

@Component
class TokenService(val template: NamedParameterJdbcTemplate, val query: Query) {

    fun validate(token: String): Boolean {
        return false
    }
}
