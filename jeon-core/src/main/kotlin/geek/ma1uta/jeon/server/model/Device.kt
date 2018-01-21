package geek.ma1uta.jeon.server.model

import java.net.InetAddress

class Device(val deviceId: String, val user: User, val displayName: String?, val lastSeenIp: InetAddress, val lastSeenTs: Long)
