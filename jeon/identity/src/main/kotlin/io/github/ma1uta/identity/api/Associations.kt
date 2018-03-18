package io.github.ma1uta.identity.api

import io.github.ma1uta.identity.lookup.SessionService
import io.github.ma1uta.jeon.exception.MatrixException
import io.github.ma1uta.matrix.ErrorResponse
import io.github.ma1uta.matrix.identity.api.AssociationsApi
import io.github.ma1uta.matrix.identity.model.associations.SessionResponse
import io.github.ma1uta.matrix.identity.model.associations.ValidationResponse
import org.springframework.stereotype.Component

@Component
class Associations(val sessionService: SessionService) : AssociationsApi {
    override fun create(clientSecret: String?, email: String?, sendAttempt: Long?, nextLink: String?): SessionResponse {
        if (clientSecret == null || email == null) {
            throw MatrixException(ErrorResponse.Code.M_BAD_JSON, "Missing client secret or email.")
        }
        val response = SessionResponse()
        response.sid = sessionService.create(clientSecret, email, sendAttempt, nextLink)
        return response
    }

    override fun getValidate(sid: String?, clientSecret: String?, token: String?): ValidationResponse {
        return validate(sid, clientSecret, token)
    }

    override fun postValidate(sid: String?, clientSecret: String?, token: String?): ValidationResponse {
        return validate(sid, clientSecret, token)
    }

    fun validate(sid: String?, clientSecret: String?, token: String?): ValidationResponse {
        if (sid == null || clientSecret == null || token == null) {
            throw MatrixException(ErrorResponse.Code.M_BAD_JSON, "Missing client secret, sid or token")
        }
        sessionService.validate(token, clientSecret, sid)
        val response = ValidationResponse()
        response.validated = true
        return response
    }
}
