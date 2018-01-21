package geek.ma1uta.jeon.server.auth

import com.github.nitram509.jmacaroons.MacaroonsBuilder
import geek.ma1uta.jeon.server.ServerProperties
import geek.ma1uta.jeon.server.exception.MatrixException
import geek.ma1uta.jeon.server.model.Device
import geek.ma1uta.jeon.server.model.Token
import geek.ma1uta.jeon.server.model.User
import geek.ma1uta.jeon.server.service.DeviceService
import geek.ma1uta.jeon.server.service.TokenService
import geek.ma1uta.jeon.server.service.UserService
import geek.ma1uta.matrix.client.model.ErrorMessage
import geek.ma1uta.matrix.client.model.auth.LoginRequest
import geek.ma1uta.matrix.client.model.auth.LoginResponse
import org.apache.commons.lang3.StringUtils
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.ZoneOffset
import javax.servlet.http.HttpServletRequest

@Component
class UsernamePasswordLoginProvider(val passwordEncoder: BCryptPasswordEncoder, val userService: UserService,
                                    val deviceService: DeviceService, val tokenService: TokenService,
                                    val serverProperties: ServerProperties) : LoginProvider {

    override fun login(loginRequest: LoginRequest, request: HttpServletRequest): LoginResponse? {
        if (StringUtils.isBlank(loginRequest.user) || StringUtils.isBlank(loginRequest.password)) {
            return null
        }

        val username = loginRequest.user.trim()
        val password = passwordEncoder.encode(loginRequest.password.trim())

        val user = userService.read(username) ?: throw MatrixException(ErrorMessage.Code.M_NOT_FOUND, "User not found")

        if (user.password != password) {
            throw MatrixException(ErrorMessage.Code.M_INVALID_PASSWORD, "Invalid password")
        }

        val deviceId =
                if (StringUtils.isBlank(loginRequest.deviceId)) MacaroonsBuilder.create(serverProperties.name, serverProperties.macaroon,
                        user.id).serialize() else loginRequest.deviceId
        val deviceName = if (StringUtils.isNotBlank(loginRequest.initialDeviceDisplayName)) loginRequest.initialDeviceDisplayName else null
        val lastSeenIp = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)

        val device = Device(deviceId, User(username, password), deviceName, request.remoteAddr, lastSeenIp)

        deviceService.insertOrUpdate(device)

        val tokenId = MacaroonsBuilder.create(serverProperties.name, serverProperties.macaroon, username + deviceId).serialize()
        val token = Token(tokenId, device, user)

        tokenService.insertOrUpdate(token)

        val loginResponse = LoginResponse()
        loginResponse.userId = user.id
        loginResponse.homeServer = serverProperties.name
        loginResponse.deviceId = device.deviceId
        loginResponse.accessToken = token.token

        return loginResponse
    }
}
