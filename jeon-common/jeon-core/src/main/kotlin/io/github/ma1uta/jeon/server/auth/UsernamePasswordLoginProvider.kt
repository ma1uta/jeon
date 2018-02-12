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
class UsernamePasswordLoginProvider(passwordEncoder: BCryptPasswordEncoder, userService: UserService,
                                    deviceService: DeviceService, serverProperties: ServerProperties) :
        AbstractPasswordLoginProvider(passwordEncoder, userService, deviceService, serverProperties) {

    override fun login(loginRequest: LoginRequest, request: HttpServletRequest): LoginResponse? {
        if (loginRequest.type.isNullOrBlank() || AuthType.PASSWORD != loginRequest.type || loginRequest.user.isNullOrBlank()) {
            return null
        }
        if (loginRequest.password.isNullOrBlank()) {
            throw MatrixException(io.github.ma1uta.matrix.ErrorMessage.Code.M_FORBIDDEN, "Invalid login or password", null, 403)
        }

        val username = loginRequest.user.trim()
        val password = loginRequest.password.trim()

        return authenticate(username, password, loginRequest, request)
    }
}
