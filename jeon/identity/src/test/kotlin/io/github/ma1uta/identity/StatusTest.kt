package io.github.ma1uta.identity

import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Profile
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@RunWith(SpringRunner::class)
@SpringBootTest(classes = [Identity::class])
@Profile("test")
class StatusTest {

    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var webApplicationContext: WebApplicationContext

    @Before
    fun init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build()
    }

    @Ignore
    @Test
    fun statusTest() {
        mockMvc.perform(get("/_matrix/identity/api/v1")).andExpect(status().isOk)
    }
}
