package io.github.ma1uta.jeon.server.model

data class Device(val deviceId: String, val token: String?, val user: User, val displayName: String?, val lastSeenIp: String, val lastSeenTs: Long)
