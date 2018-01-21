package geek.ma1uta.jeon.server.rest.client

import geek.ma1uta.jeon.server.exception.MatrixException
import geek.ma1uta.jeon.server.service.LoginService
import geek.ma1uta.matrix.client.api.AuthApi
import geek.ma1uta.matrix.client.model.EmptyResponse
import geek.ma1uta.matrix.client.model.ErrorMessage
import geek.ma1uta.matrix.client.model.auth.LoginRequest
import geek.ma1uta.matrix.client.model.auth.LoginResponse
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.ws.rs.core.Context

@Component
class Auth(val loginService: LoginService) : AuthApi {

    @Context
    lateinit var request: HttpServletRequest

    override fun login(loginRequest: LoginRequest?): LoginResponse {

        if (loginRequest == null) {
            throw MatrixException(ErrorMessage.Code.M_NOT_JSON, "Missing json.")
        }

        return loginService.login(loginRequest, request)
    }

    override fun logout(): EmptyResponse {
        loginService.logout()
        return EmptyResponse()
    }
}
