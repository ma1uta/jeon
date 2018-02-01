package io.github.ma1uta.jeon.server.service

import io.github.ma1uta.jeon.server.model.Device
import io.github.ma1uta.jeon.server.model.User
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Service
import java.sql.ResultSet

@Service
class DeviceService(val query: io.github.ma1uta.jeon.server.Query, val template: NamedParameterJdbcTemplate) {

    fun findByToken(token: String): Device =
            template.queryForObject(query.device.findByToken, mutableMapOf(Pair("token", token)), DeviceRowMapper())

    fun deleteToken(device: Device) {
        template.update(query.device.deleteToken, mutableMapOf(Pair("device_id", device.deviceId)))
    }

    fun insertOrUpdate(device: Device) =
            template.update(query.device.insertOrUpdate,
                    mutableMapOf(Pair("device_id", device.deviceId),
                            Pair("user_id", device.user.id),
                            Pair("display_name", device.displayName),
                            Pair("last_seen_ip", device.lastSeenIp),
                            Pair("last_seen_ts", device.lastSeenTs)))

    fun updateLastSeen(device: Device) = template.update(query.device.updateLastSeen,
            mutableMapOf(Pair("last_seen_ip", device.lastSeenIp),
                    Pair("last_seen_ts", device.lastSeenTs),
                    Pair("device_id", device.deviceId),
                    Pair("user_id", device.user.id)))

    class DeviceRowMapper : RowMapper<Device> {
        override fun mapRow(rs: ResultSet?, rowNum: Int) = Device(rs!!.getString("d_device_id"),
                rs.getString("d_token"), User(rs.getString("u_id"), rs.getString("u_password"),
                rs.getString("u_display_name"), rs.getString("u_avatar_url"), rs.getString("u_kind")),
                rs.getString("d_display_name"), rs.getString("d_last_seen_ip"),
                rs.getLong("d_last_seen_ts"))

    }
}
