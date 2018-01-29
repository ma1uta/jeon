package io.github.ma1uta.jeon.server.auth

import io.github.ma1uta.jeon.server.model.Token
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.GrantedAuthority

class MatrixAuthentication(val token: Token, authorities: List<GrantedAuthority>?) : AbstractAuthenticationToken(authorities) {

    constructor(token: Token) : this(token, null)

    override fun getCredentials() = token.token

    override fun getPrincipal() = token
}
