package geek.ma1uta.jeon.server.rest.client

import geek.ma1uta.matrix.client.model.ErrorMessage
import geek.ma1uta.matrix.client.model.auth.LoginRequest
import geek.ma1uta.matrix.client.model.version.VersionsResponse
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.junit4.SpringRunner
import javax.inject.Inject

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthTest {

    @Inject
    lateinit var restTemplate: TestRestTemplate

    @Test
    fun version() {
        val versionsResponse = restTemplate.getForObject("/_matrix/client/versions", VersionsResponse::class.java) ?: error("empty response")
        assert(versionsResponse.versions.contentEquals(arrayOf("r0.3.0")), { "wrong version" })
    }

    @Test
    fun loginWithPassword() {
        val loginRequest = LoginRequest()
        loginRequest.type = "m.login.password"
        loginRequest.user = "dummy"
        loginRequest.password = "dummy"
        val loginResponse =
                restTemplate.postForObject("/_matrix/client/r0/login", loginRequest, ErrorMessage::class.java) ?: error("empty response")
        assert(loginResponse.errcode == ErrorMessage.Code.M_NOT_FOUND)
    }
}
