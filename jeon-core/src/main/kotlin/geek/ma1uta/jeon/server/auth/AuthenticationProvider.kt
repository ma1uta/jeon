package geek.ma1uta.jeon.server.auth

import geek.ma1uta.jeon.server.service.UserService
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

@Component
class AuthenticationProvider(val userService: UserService) : AuthenticationProvider {
    override fun supports(authentication: Class<*>?) = UserAuthentication::class.java.isAssignableFrom(authentication)

    override fun authenticate(authentication: Authentication?): Authentication {

        if (authentication == null) {
            throw RuntimeException("empty user")
        }

        val user = userService.read(authentication.name)
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
