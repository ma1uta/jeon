package geek.ma1uta.jeon.server.service

import geek.ma1uta.jeon.server.Query
import geek.ma1uta.jeon.server.model.Device
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Service

@Service
class DeviceService(val query: Query, val template: NamedParameterJdbcTemplate) {

    fun insertOrUpdate(device: Device) =
            template.update(query.device.insertOrUpdate,
                    mutableMapOf(Pair("device_id", device.deviceId),
                            Pair("user_id", device.user.id),
                            Pair("display_name", device.displayName),
                            Pair("last_seen_ip", device.lastSeenIp),
                            Pair("last_seen_ts", device.lastSeenTs)))
}
