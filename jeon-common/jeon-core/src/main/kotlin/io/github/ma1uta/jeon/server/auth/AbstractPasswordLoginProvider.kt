package io.github.ma1uta.jeon.server.auth

import com.github.nitram509.jmacaroons.MacaroonsBuilder
import io.github.ma1uta.jeon.server.ServerProperties
import io.github.ma1uta.jeon.exception.MatrixException
import io.github.ma1uta.jeon.server.model.Device
import io.github.ma1uta.jeon.server.model.User
import io.github.ma1uta.jeon.server.service.DeviceService
import io.github.ma1uta.jeon.server.service.UserService
import io.github.ma1uta.matrix.ErrorMessage
import io.github.ma1uta.matrix.client.model.auth.LoginRequest
import io.github.ma1uta.matrix.client.model.auth.LoginResponse
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.time.LocalDateTime
import java.time.ZoneOffset
import javax.servlet.http.HttpServletRequest

abstract class AbstractPasswordLoginProvider(val passwordEncoder: BCryptPasswordEncoder, val userService: UserService,
                                             val deviceService: DeviceService, val serverProperties: ServerProperties) : LoginProvider {

    fun validateUsernameAndPassword(username: String, password: CharSequence): User {
        val user =
                userService.read(username) ?: throw MatrixException(io.github.ma1uta.matrix.ErrorMessage.Code.M_FORBIDDEN,
                        "Invalid login or password.", null, 403)

        if (!passwordEncoder.matches(password, user.password)) {
            throw MatrixException(io.github.ma1uta.matrix.ErrorMessage.Code.M_FORBIDDEN, "Invalid login or password.", null, 403)
        }

        return user
    }

    fun authenticate(username: String, password: CharSequence, loginRequest: LoginRequest, request: HttpServletRequest): LoginResponse {
        val user = validateUsernameAndPassword(username, password)

        val deviceId =
                if (loginRequest.deviceId.isNullOrBlank())
                    MacaroonsBuilder.create(serverProperties.name, serverProperties.macaroon, user.id).serialize()
                else loginRequest.deviceId
        val deviceName = if (!loginRequest.initialDeviceDisplayName.isNullOrBlank()) loginRequest.initialDeviceDisplayName else null
        val lastSeenTs = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)

        val token = MacaroonsBuilder.create(serverProperties.name, serverProperties.macaroon, username + deviceId).serialize()
        val device = Device(deviceId, token, User(username, user.password), deviceName, request.remoteAddr, lastSeenTs)

        deviceService.insertOrUpdate(device)

        val loginResponse = LoginResponse()
        loginResponse.userId = user.id
        loginResponse.homeServer = serverProperties.name
        loginResponse.deviceId = device.deviceId
        loginResponse.accessToken = device.token

        return loginResponse
    }
}
