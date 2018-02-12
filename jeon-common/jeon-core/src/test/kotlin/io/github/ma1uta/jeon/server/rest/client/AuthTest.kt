package io.github.ma1uta.jeon.server.rest.client

import io.github.ma1uta.matrix.ErrorMessage
import io.github.ma1uta.matrix.client.model.auth.LoginRequest
import io.github.ma1uta.matrix.client.model.auth.LoginResponse
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.transaction.annotation.Transactional
import javax.inject.Inject

@Transactional
@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class AuthTest {

    @Inject
    lateinit var restTemplate: TestRestTemplate

    @Before
    @Sql(scripts = ["/sql/loginPassword.sql"], executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    fun before() {

    }

    @Ignore
    @Test
    fun loginFailed() {
        val loginRequest = LoginRequest()
        loginRequest.type = "m.login.password"
        loginRequest.user = "dummy"
        loginRequest.password = "dummy"
        val loginResponse =
                restTemplate.postForObject("/_matrix/client/r0/login", loginRequest, io.github.ma1uta.matrix.ErrorMessage::class.java) ?: error("empty response")
        assert(loginResponse.errcode == io.github.ma1uta.matrix.ErrorMessage.Code.M_NOT_FOUND)
    }

    @Ignore
    @Test
    fun loginPassword() {
        val loginRequest = LoginRequest()
        loginRequest.type = "m.login.password"
        loginRequest.user = "dummy"
        loginRequest.password = "dummy"
        restTemplate.postForObject("/_matrix/client/r0/login", loginRequest, LoginResponse::class.java) ?: error("empty response")
    }
}
