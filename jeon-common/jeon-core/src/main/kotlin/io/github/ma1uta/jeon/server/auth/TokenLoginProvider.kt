package io.github.ma1uta.jeon.server.auth

import io.github.ma1uta.jeon.server.ServerProperties
import io.github.ma1uta.jeon.exception.MatrixException
import io.github.ma1uta.jeon.server.service.DeviceService
import io.github.ma1uta.jeon.server.service.UserService
import io.github.ma1uta.matrix.ErrorMessage
import io.github.ma1uta.matrix.client.model.auth.AuthType
import io.github.ma1uta.matrix.client.model.auth.LoginRequest
import io.github.ma1uta.matrix.client.model.auth.LoginResponse
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest

@Component
class TokenLoginProvider(passwordEncoder: BCryptPasswordEncoder, userService: UserService,
                         deviceService: DeviceService, serverProperties: ServerProperties) :
        AbstractPasswordLoginProvider(passwordEncoder, userService, deviceService, serverProperties) {

    override fun login(loginRequest: LoginRequest, request: HttpServletRequest): LoginResponse? {
        if (loginRequest.type.isNullOrBlank() || AuthType.TOKEN != loginRequest.type) {
            return null
        }
        if (loginRequest.token.isNullOrBlank()) {
            throw MatrixException(io.github.ma1uta.matrix.ErrorMessage.Code.M_FORBIDDEN, "Invalid token", null, 403)
        }

        //TODO receive username and password by token via identity server
        val username = "dummy"
        val password = "dummy"

        return authenticate(username, password, loginRequest, request)
    }
}
