package geek.ma1uta.jeon.server.service

import geek.ma1uta.jeon.server.auth.LoginProvider
import geek.ma1uta.jeon.server.auth.MatrixAuthentication
import geek.ma1uta.jeon.server.exception.MatrixException
import geek.ma1uta.matrix.client.model.ErrorMessage
import geek.ma1uta.matrix.client.model.auth.LoginRequest
import geek.ma1uta.matrix.client.model.auth.LoginResponse
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import javax.servlet.http.HttpServletRequest

@Service
class LoginService(val loginProviders: List<LoginProvider>, val tokenService: TokenService) {

    fun login(loginRequest: LoginRequest, request: HttpServletRequest): LoginResponse {
        var loginResponse: LoginResponse? = null
        for (loginProvider in loginProviders) {
            loginResponse = loginProvider.login(loginRequest, request)
            if (loginResponse != null) {
                break
            }
        }

        if (loginResponse == null) {
            throw MatrixException(ErrorMessage.Code.M_BAD_JSON, "Bad login type.", null, 400)
        }

        return loginResponse
    }

    @PreAuthorize("isFullyAuthenticated()")
    fun logout() {
        val authentication = SecurityContextHolder.getContext().authentication as MatrixAuthentication
        tokenService.deleteByToken(authentication.token)
    }
}
