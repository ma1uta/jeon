package geek.ma1uta.jeon.server

import geek.ma1uta.jeon.server.auth.AuthenticationProvider
import geek.ma1uta.jeon.server.auth.JeonSecurityFilter
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter
import javax.inject.Inject

@Configuration
class SecurityConfiguration : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity?) {
        http?.authenticationProvider(authenticationProvider)?.addFilterBefore(jeonSecurityFilter(), AbstractPreAuthenticatedProcessingFilter::class.java)

        http?.formLogin()?.disable()?.csrf()?.disable()

        http?.authorizeRequests()?.antMatchers("/_matrix/client/versions")?.permitAll()
    }

    @Inject
    lateinit var authenticationProvider: AuthenticationProvider

    @Inject
    lateinit var authenticationManager: AuthenticationManager

    @Inject
    lateinit var applicationEventPublisher: ApplicationEventPublisher

    fun jeonSecurityFilter(): JeonSecurityFilter {
        val securityFilter = JeonSecurityFilter()
        securityFilter.setAuthenticationManager(authenticationManager)
        securityFilter.setApplicationEventPublisher(applicationEventPublisher)
        securityFilter.setAuthenticationDetailsSource(WebAuthenticationDetailsSource())
        return securityFilter
    }
}
