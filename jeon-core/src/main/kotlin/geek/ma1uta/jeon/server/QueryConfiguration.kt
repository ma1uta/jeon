package geek.ma1uta.jeon.server

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
    fun query(): Query {
        val user = Query.User(env.getProperty("user.read"))

        val device = Query.Device(env.getProperty("device.insertOrUpdate"),
                env.getProperty("device.updateLastSeen"))

        val token = Query.Token(env.getProperty("token.insertOrUpdate"),
                env.getProperty("token.deleteByUserAndDevice"),
                env.getProperty("token.findByToken"),
                env.getProperty("token.deleteByToken"))

        return Query(user, device, token)
    }
}
