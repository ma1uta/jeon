package geek.ma1uta.jeon.server

import geek.ma1uta.jeon.server.auth.JeonSecurityFilter
import geek.ma1uta.jeon.server.auth.TokenService
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
class SecurityConfiguration : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity?) {
        http?.authorizeRequests()?.antMatchers("/_matrix/client/versions")?.permitAll()
    }

    @Bean
    fun securityFilter(tokenService: TokenService) = FilterRegistrationBean(JeonSecurityFilter(tokenService))
}
