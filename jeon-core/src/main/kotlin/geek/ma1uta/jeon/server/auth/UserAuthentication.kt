package geek.ma1uta.jeon.server.auth

import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority

class UserAuthentication(val userId: String, val deviceId: String, val sessionId: String, val token: String) : Authentication {

    private var authenticated = false

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setAuthenticated(isAuthenticated: Boolean) {
        authenticated = true
    }

    override fun getName(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCredentials(): Any {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPrincipal(): Any {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isAuthenticated(): Boolean {
        return authenticated;
    }

    override fun getDetails(): Any {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
