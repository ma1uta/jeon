package io.github.ma1uta.jeon.server.model

data class Device(val deviceId: String, val token: String?, val user: User, val displayName: String?, val lastSeenIp: String,
                  val lastSeenTs: Long) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Device

        return deviceId == other.deviceId
    }

    override fun hashCode(): Int {
        return deviceId.hashCode()
    }
}
