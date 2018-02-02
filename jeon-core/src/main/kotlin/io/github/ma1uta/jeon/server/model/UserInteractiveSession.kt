package io.github.ma1uta.jeon.server.model

data class UserInteractiveSession(val sessionId: String, val completed: Array<String>) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserInteractiveSession

        return sessionId == other.sessionId
    }

    override fun hashCode(): Int {
        return sessionId.hashCode()
    }
}
