package geek.ma1uta.jeon.server

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import org.glassfish.jersey.server.ResourceConfig
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
@EnableConfigurationProperties(value = [ServerProperties::class, Query::class])
@EnableGlobalMethodSecurity
@EnableWebSecurity
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
    fun passwordEncoder() = BCryptPasswordEncoder()
}
