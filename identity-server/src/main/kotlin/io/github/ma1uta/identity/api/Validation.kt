package io.github.ma1uta.identity.api

import io.github.ma1uta.identity.service.SessionService
import io.github.ma1uta.jeon.exception.MatrixException
import io.github.ma1uta.matrix.ErrorResponse
import io.github.ma1uta.matrix.identity.api.ValidationApi
import io.github.ma1uta.matrix.identity.model.validation.PublishResponse
import io.github.ma1uta.matrix.identity.model.validation.ValidationResponse
import org.springframework.stereotype.Component

@Component
class Validation(val sessionService: SessionService) : ValidationApi {
    override fun validate(sid: String?, clientSecret: String?): ValidationResponse {
        if (sid == null || clientSecret == null) {
            throw MatrixException(ErrorResponse.Code.M_BAD_JSON, "Sid or client secret are missing")
        }
        return sessionService.getSession(sid, clientSecret)
    }

    override fun publish(sid: String?, clientSecret: String?, mxid: String?): PublishResponse {
        if (sid == null || clientSecret == null || mxid == null) {
            throw MatrixException(ErrorResponse.Code.M_BAD_JSON, "Sid, client secret or mxid are missing")
        }
        val response = PublishResponse()
        response.published = sessionService.publish(sid, clientSecret, mxid)
        return response
    }
}
