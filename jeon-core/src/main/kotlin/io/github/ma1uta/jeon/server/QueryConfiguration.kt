package io.github.ma1uta.jeon.server

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.core.env.Environment
import javax.inject.Inject

@PropertySource("classpath:/query.properties")
@Configuration
class QueryConfiguration {

    @Inject
    lateinit var env: Environment

    @Bean
    fun query(): io.github.ma1uta.jeon.server.Query {
        val user = io.github.ma1uta.jeon.server.Query.User(env.getProperty("user.read"), env.getProperty("user.insert"))

        val device = io.github.ma1uta.jeon.server.Query.Device(env.getProperty("device.insertOrUpdate"),
                env.getProperty("device.updateLastSeen"))

        val token = io.github.ma1uta.jeon.server.Query.Token(env.getProperty("token.insertOrUpdate"),
                env.getProperty("token.deleteByUserAndDevice"),
                env.getProperty("token.findByToken"),
                env.getProperty("token.deleteByToken"))

        return io.github.ma1uta.jeon.server.Query(user, device, token)
    }
}
