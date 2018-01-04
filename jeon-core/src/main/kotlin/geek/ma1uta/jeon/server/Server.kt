package geek.ma1uta.jeon.server

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import org.glassfish.jersey.server.ResourceConfig
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.web.support.SpringBootServletInitializer
import org.springframework.context.annotation.Bean

@SpringBootApplication
@EnableConfigurationProperties(ServerProperties::class)
class Server : SpringBootServletInitializer() {

    override fun configure(builder: SpringApplicationBuilder?) = builder!!.sources(Server::class.java)!!

    @Bean
    fun jerseyContext() = ResourceConfig()

    @Bean
    fun mapper(): ObjectMapper {
        val mapper = ObjectMapper()
        mapper.propertyNamingStrategy = PropertyNamingStrategy.LOWER_CAMEL_CASE
        return mapper
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(Server::class.java, *args)
}
