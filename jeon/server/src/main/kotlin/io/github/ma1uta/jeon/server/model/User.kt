package io.github.ma1uta.jeon.server.model

data class User(val id: String, val password: String, val displayName: String?, val avatarUrl: String?, val kind: String = "user") {
    constructor(id: String, password: String) : this(id, password, null, null)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
