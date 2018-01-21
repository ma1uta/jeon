package geek.ma1uta.jeon.server.auth

import geek.ma1uta.matrix.client.model.auth.LoginRequest
import geek.ma1uta.matrix.client.model.auth.LoginResponse
import javax.servlet.http.HttpServletRequest

interface LoginProvider {

    fun login(loginRequest: LoginRequest, request: HttpServletRequest): LoginResponse?
}
