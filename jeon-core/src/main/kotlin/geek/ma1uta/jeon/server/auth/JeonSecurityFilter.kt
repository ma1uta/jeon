package geek.ma1uta.jeon.server.auth

import geek.ma1uta.jeon.server.exception.MatrixException
import geek.ma1uta.matrix.client.model.ErrorMessage
import java.util.regex.Pattern
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.FilterConfig
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

class JeonSecurityFilter(val tokenService: TokenService) : Filter {

    private val filterApplied = JeonSecurityFilter::class.java.name + ".APPLIED"
    private val bearerPattern = Pattern.compile("^\\s*Bearer\\s+(\\w+)\\s*$", Pattern.UNICODE_CHARACTER_CLASS)

    override fun destroy() {
    }

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {

        if (request?.getAttribute(filterApplied) != null) {
            chain?.doFilter(request, response)
        }

        try {
            request?.setAttribute(filterApplied, true)

            var token: String? = null
            if (request is HttpServletRequest) {
                val header = request.getHeader("Authorization")
                if (header != null) {
                    val matcher = bearerPattern.matcher(header)
                    if (matcher.find()) {
                        token = matcher.group(1)
                    }
                }
            }

            if (token == null) {
                token = request?.getParameter("access_token")
            }

            if (token == null) {
                throw MatrixException(ErrorMessage.Code.M_MISSING_TOKEN, "Missing access token.")
            }

            if (tokenService.validate(token)) {
                chain?.doFilter(request, response)
            } else {
                throw MatrixException(ErrorMessage.Code.M_UNKNOWN_TOKEN, "Unrecognized access token.")
            }

        } finally {
            request?.removeAttribute(filterApplied)
        }
    }

    override fun init(filterConfig: FilterConfig?) {
    }
}
