package geek.ma1uta.jeon.server.auth

import geek.ma1uta.jeon.server.ServerProperties
import geek.ma1uta.jeon.server.service.DeviceService
import geek.ma1uta.jeon.server.service.TokenService
import org.springframework.stereotype.Component
import java.util.regex.Pattern
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.FilterConfig
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

const val SESSION_TOKEN = "sessionToken"

@Component
class AuthenticateFilter(val tokenService: TokenService, val deviceService: DeviceService, val serverProperties: ServerProperties) :
        Filter {
    val filterApplied = AuthenticateFilter::class.java.name + ".APPLIED"
    val bearerPattern: Pattern = Pattern.compile("^\\s*Bearer\\s+(\\w+)\\s*$")

    override fun destroy() {
    }

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        val applied = request?.getAttribute(filterApplied) == null
        if (applied) {
            chain?.doFilter(request, response)
        }

        try {
            request?.setAttribute(filterApplied, true)

            var accessToken: String? = null
            if (request is HttpServletRequest) {
                val header = request.getHeader("Authentication")
                if (header != null) {
                    val matcher = bearerPattern.matcher(header)
                    if (matcher.matches()) {
                        accessToken = matcher.group(1)
                    }
                }
            }

            if (accessToken == null) {
                accessToken = request?.getParameter("access_token")
            }

            if (accessToken != null && accessToken.isNotBlank()) {
                val token = tokenService.findByToken(accessToken)

                if (token != null) {
                    if (serverProperties.updateLastSeen) {
                        deviceService.updateLastSeen(token.device)
                    }
                    request?.setAttribute(SESSION_TOKEN, token)
                }
            }

            chain?.doFilter(request, response)

        } finally {
            request?.removeAttribute(SESSION_TOKEN)
            request?.removeAttribute(filterApplied)
        }
    }

    override fun init(filterConfig: FilterConfig?) {
    }
}
