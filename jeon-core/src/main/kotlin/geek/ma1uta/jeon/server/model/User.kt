package geek.ma1uta.jeon.server.model

import java.util.*

data class User(val id: String, val displayName: String, val avatar: Array<Byte>) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + displayName.hashCode()
        result = 31 * result + Arrays.hashCode(avatar)
        return result
    }
}
