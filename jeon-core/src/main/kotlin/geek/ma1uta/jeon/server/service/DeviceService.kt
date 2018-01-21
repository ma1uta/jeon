package geek.ma1uta.jeon.server.service

import geek.ma1uta.jeon.server.Query
import geek.ma1uta.jeon.server.model.Device
import geek.ma1uta.jeon.server.model.User
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Service
import java.net.InetAddress
import java.sql.ResultSet

@Service
class DeviceService(val query: Query, val template: NamedParameterJdbcTemplate) {

    fun findByToken(token: String) = template.queryForObject(query.user.findByToken, mutableMapOf(Pair("token", token)), DeviceRowMapper())

    class DeviceRowMapper : RowMapper<Device> {
        override fun mapRow(rs: ResultSet?, rowNum: Int) = Device(
                rs!!.getString("d.device_id"),
                User(rs.getString("u.id"), rs.getString("u.display_name"), rs.getString("u.avatar_url")),
                rs.getString("d.displayName"),
                rs.getObject("d.lastSeenIp") as InetAddress,
                rs.getLong("d.lastSeenTs")
        )

    }
}
