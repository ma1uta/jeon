package geek.ma1uta.jeon.server.auth

import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import java.util.regex.Pattern
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JeonSecurityFilter : AbstractAuthenticationProcessingFilter(AntPathRequestMatcher("/_matrix/client/r0/login", "POST")) {
    private val bearerPattern = Pattern.compile("^\\s*Bearer\\s+(\\w+)\\s*$", Pattern.UNICODE_CHARACTER_CLASS)

    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {

        var token: String? = null
        val header = request?.getHeader("Authentication")
        if (header != null) {
            val matcher = bearerPattern.matcher(header)
            if (matcher.matches()) {
                token = matcher.group(1)
            }
        }

        if (token == null) {
            token = request?.getParameter("access_token")
        }

        val userAuthentication = UserAuthentication(token)

        userAuthentication.details = this.authenticationDetailsSource.buildDetails(request)

        return this.authenticationManager.authenticate(userAuthentication)
    }
}
