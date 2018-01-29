package io.github.ma1uta.jeon.server.model

data class User(val id: String, val password: String, val displayName: String?, val avatarUrl: String?, val kind: String = "user") {
    constructor(id: String, password: String) : this(id, password, null, null)
}
