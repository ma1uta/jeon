package geek.ma1uta.jeon.server.model

data class User(val id: String, val password: String, val displayName: String?, val avatarUrl: String?) {
    constructor(id: String, password: String) : this(id, password, null, null)
}
