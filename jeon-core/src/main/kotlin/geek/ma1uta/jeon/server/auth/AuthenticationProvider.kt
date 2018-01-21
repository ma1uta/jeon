package geek.ma1uta.jeon.server.auth

import geek.ma1uta.jeon.server.service.DeviceService
import geek.ma1uta.matrix.client.model.ErrorMessage
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

@Component
class AuthenticationProvider(val deviceService: DeviceService, val tokenService: TokenService) : AuthenticationProvider {
    override fun supports(authentication: Class<*>?) = UserAuthentication::class.java.isAssignableFrom(authentication)

    override fun authenticate(authentication: Authentication?): Authentication {

        val userAuthentication: UserAuthentication = authentication as UserAuthentication

        val token = userAuthentication.details
                ?: throw AuthenticationException("Missing authorization token", ErrorMessage.Code.M_MISSING_TOKEN)

        if (tokenService.validate(token as String)) {
            val device = deviceService.findByToken(token)

            val authenticated = UserAuthentication(device, token)
            authenticated.isAuthenticated = true
            return authenticated
        } else {
            throw AuthenticationException("Unknown authorization token", ErrorMessage.Code.M_UNKNOWN_TOKEN)
        }
    }
}
