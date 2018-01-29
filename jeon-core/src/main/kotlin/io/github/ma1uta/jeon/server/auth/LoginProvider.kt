package io.github.ma1uta.jeon.server.auth

import io.github.ma1uta.matrix.client.model.auth.LoginRequest
import io.github.ma1uta.matrix.client.model.auth.LoginResponse
import javax.servlet.http.HttpServletRequest

interface LoginProvider {

    fun login(loginRequest: LoginRequest, request: HttpServletRequest): LoginResponse?
}
