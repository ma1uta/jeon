package io.github.ma1uta.jidentity.api

import io.github.ma1uta.jeon.exception.MatrixException
import io.github.ma1uta.jidentity.key.KeyService
import io.github.ma1uta.matrix.ErrorResponse
import io.github.ma1uta.matrix.identity.api.KeyManagementApi
import io.github.ma1uta.matrix.identity.model.key.KeyValidationResponse
import io.github.ma1uta.matrix.identity.model.key.PublicKeyResponse
import org.springframework.stereotype.Component

@Component
class KeyManagement(val keyService: KeyService) : KeyManagementApi {
    override fun get(keyId: String?): PublicKeyResponse {
        val key = keyId ?: throw MatrixException(ErrorResponse.Code.M_NOT_FOUND, "Missing key.")
        val (_, certificate) = keyService.key(key)
        val response = PublicKeyResponse()
        response.publicKey = certificate.publicKey.encoded.toString(Charsets.UTF_8)
        return response
    }

    override fun valid(publicKey: String?): KeyValidationResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun ephemeralValid(publicKey: String?): KeyValidationResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
