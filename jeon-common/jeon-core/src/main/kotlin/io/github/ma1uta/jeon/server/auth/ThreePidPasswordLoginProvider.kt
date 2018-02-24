package io.github.ma1uta.jeon.server.auth

import io.github.ma1uta.jeon.exception.MatrixException
import io.github.ma1uta.jeon.server.ServerProperties
import io.github.ma1uta.jeon.server.service.DeviceService
import io.github.ma1uta.jeon.server.service.UserService
import io.github.ma1uta.matrix.ErrorResponse
import io.github.ma1uta.matrix.client.model.auth.AuthType
import io.github.ma1uta.matrix.client.model.auth.LoginRequest
import io.github.ma1uta.matrix.client.model.auth.LoginResponse
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest

@Component
class ThreePidPasswordLoginProvider(passwordEncoder: BCryptPasswordEncoder, userService: UserService,
                                    deviceService: DeviceService, serverProperties: ServerProperties) :
        AbstractPasswordLoginProvider(passwordEncoder, userService, deviceService, serverProperties) {

    override fun login(loginRequest: LoginRequest, request: HttpServletRequest): LoginResponse? {
        if (loginRequest.type.isNullOrBlank() || AuthType.PASSWORD != loginRequest.type
                || loginRequest.medium.isNullOrBlank() || loginRequest.address.isNullOrBlank()) {
            return null
        }
        if (loginRequest.password.isNullOrBlank() || !(loginRequest.medium == "email" || loginRequest.medium == "msisdn")) {
            throw MatrixException(ErrorResponse.Code.M_FORBIDDEN, "Invalid login or password", null, 403)
        }

        val medium = loginRequest.medium.trim()
        val address = loginRequest.address.trim()
        val password = loginRequest.password.trim()

        //TODO request to the identity server to find matrixid
        val username = "dummy"

        return authenticate(username, password, loginRequest, request)
    }
}
