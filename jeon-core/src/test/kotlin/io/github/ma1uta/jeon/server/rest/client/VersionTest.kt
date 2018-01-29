package io.github.ma1uta.jeon.server.rest.client

import io.github.ma1uta.matrix.client.model.version.VersionsResponse
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.junit4.SpringRunner
import javax.inject.Inject

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class VersionTest {

    @Inject
    lateinit var restTemplate: TestRestTemplate

    @Test
    fun version() {
        val versionsResponse = restTemplate.getForObject("/_matrix/client/versions", VersionsResponse::class.java) ?: error("empty response")
        assert(versionsResponse.versions.contentEquals(arrayOf("r0.3.0")), { "wrong version" })
    }
}
