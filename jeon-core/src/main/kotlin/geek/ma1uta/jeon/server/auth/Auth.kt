package geek.ma1uta.jeon.server.auth

import geek.ma1uta.jeon.server.exception.MatrixException
import geek.ma1uta.jeon.server.model.Token
import geek.ma1uta.matrix.client.model.ErrorMessage
import javax.servlet.http.HttpServletRequest

object Auth {

    fun currentToken(request: HttpServletRequest) = request.getAttribute(SESSION_TOKEN) as Token?

    fun isAuthenticated(request: HttpServletRequest) = currentToken(request) != null

    fun requiredAuthentication(request: HttpServletRequest) {
        if (!isAuthenticated(request)) {
            throw MatrixException(ErrorMessage.Code.M_FORBIDDEN, "Forbidden.", null, 403)
        }
    }
}
