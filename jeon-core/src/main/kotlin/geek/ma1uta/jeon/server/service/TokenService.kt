package geek.ma1uta.jeon.server.service

import geek.ma1uta.jeon.server.Query
import geek.ma1uta.jeon.server.model.Token
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Service

@Service
class TokenService(val query: Query, val template: NamedParameterJdbcTemplate) {

    fun insertOrUpdate(token: Token): Boolean = template.execute(query.token.insertOrUpdate,
            mutableMapOf(Pair("token", token.token), Pair("device_id", token.device.deviceId), Pair("user_id", token.user.id)),
            { ps -> ps.execute() })
}
