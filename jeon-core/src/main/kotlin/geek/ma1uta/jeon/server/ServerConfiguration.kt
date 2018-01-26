package geek.ma1uta.jeon.server

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import geek.ma1uta.jeon.server.model.User
import geek.ma1uta.jeon.server.service.UserService
import org.glassfish.jersey.server.ResourceConfig
import org.springframework.beans.factory.InitializingBean
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.security.SecureRandom

@Configuration
@EnableConfigurationProperties(value = [ServerProperties::class])
@EnableGlobalMethodSecurity
class ServerConfiguration {

    @Bean
    fun jerseyContext() = ResourceConfig()

    @Bean
    fun mapper(): ObjectMapper {
        val mapper = ObjectMapper()
        mapper.propertyNamingStrategy = PropertyNamingStrategy.LOWER_CAMEL_CASE
        return mapper
    }

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder {
        val secureRandom = SecureRandom(byteArrayOf(10, 20))
        secureRandom.setSeed(10)
        return BCryptPasswordEncoder(10, secureRandom)
    }

    @Bean
    fun propertySourcesPlaceholderConfigurer() = PropertySourcesPlaceholderConfigurer().apply { setPlaceholderPrefix("%{") }

    @Bean
    fun initialUserCreation(userService: UserService) = InitializingBean {
        if (userService.read("dummy") == null) {
            userService.insert(User("dummy", "1"))
        }
    }
}
