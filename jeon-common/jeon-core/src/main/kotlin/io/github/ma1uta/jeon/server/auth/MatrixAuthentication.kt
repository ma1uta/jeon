package io.github.ma1uta.jeon.server.auth

import io.github.ma1uta.jeon.server.model.Device
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.GrantedAuthority

class MatrixAuthentication(val device: Device, authorities: List<GrantedAuthority>?) : AbstractAuthenticationToken(authorities) {

    constructor(device: Device) : this(device, null)

    override fun getCredentials() = device.token

    override fun getPrincipal() = device
}
