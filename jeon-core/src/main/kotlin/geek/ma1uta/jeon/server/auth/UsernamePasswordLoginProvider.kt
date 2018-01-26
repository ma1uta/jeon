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
import geek.ma1uta.matrix.client.model.auth.AuthType
import geek.ma1uta.matrix.client.model.auth.LoginRequest
import geek.ma1uta.matrix.client.model.auth.LoginResponse
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
        if (loginRequest.type.isNullOrBlank() || AuthType.PASSWORD != loginRequest.type) {
            return null
        }
        if (loginRequest.user.isNullOrBlank() || loginRequest.password.isNullOrBlank()) {
            throw MatrixException(ErrorMessage.Code.M_FORBIDDEN, "Invalid login or password", null, 403)
        }

        val username = loginRequest.user.trim()
        val password = loginRequest.password.trim()

        val user =
                userService.read(username) ?: throw MatrixException(ErrorMessage.Code.M_FORBIDDEN, "Invalid login or password.", null, 403)

        if (!passwordEncoder.matches(password, user.password)) {
            throw MatrixException(ErrorMessage.Code.M_FORBIDDEN, "Invalid login or password.", null, 403)
        }

        val deviceId =
                if (loginRequest.deviceId.isNullOrBlank())
                    MacaroonsBuilder.create(serverProperties.name, serverProperties.macaroon, user.id).serialize()
                else loginRequest.deviceId
        val deviceName = if (!loginRequest.initialDeviceDisplayName.isNullOrBlank()) loginRequest.initialDeviceDisplayName else null
        val lastSeenTs = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)

        val device = Device(deviceId, User(username, user.password), deviceName, request.remoteAddr, lastSeenTs)

        deviceService.insertOrUpdate(device)

        val tokenId = MacaroonsBuilder.create(serverProperties.name, serverProperties.macaroon, username + deviceId).serialize()
        val token = Token(tokenId, device, user)

        tokenService.deleteByUserAndDevice(user, device)
        tokenService.insertOrUpdate(token)

        val loginResponse = LoginResponse()
        loginResponse.userId = user.id
        loginResponse.homeServer = serverProperties.name
        loginResponse.deviceId = device.deviceId
        loginResponse.accessToken = token.token

        return loginResponse
    }
}
