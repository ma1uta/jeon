package geek.ma1uta.jeon.server.auth

import geek.ma1uta.jeon.server.model.Device
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.GrantedAuthority

class UserAuthentication(private val device: Device?, private val token: String?, authorities: MutableCollection<GrantedAuthority>?) : AbstractAuthenticationToken(authorities) {

    constructor(device: Device?, token: String?) : this(device, token, null)

    constructor(token: String?) : this(null, token)

    override fun getCredentials(): Any? {
        return token
    }

    override fun getPrincipal(): Any? {
        return device
    }
}
