package geek.ma1uta.jeon.server.model

data class Device(val deviceId: String, val user: User, val displayName: String?, val lastSeenIp: String, val lastSeenTs: Long)
