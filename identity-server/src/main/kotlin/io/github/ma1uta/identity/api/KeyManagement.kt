package io.github.ma1uta.identity.api

import io.github.ma1uta.jeon.exception.MatrixException
import io.github.ma1uta.identity.M_MISSING_KEY
import io.github.ma1uta.identity.key.KeyService
import io.github.ma1uta.matrix.ErrorResponse
import io.github.ma1uta.matrix.identity.api.KeyManagementApi
import io.github.ma1uta.matrix.identity.model.key.KeyValidationResponse
import io.github.ma1uta.matrix.identity.model.key.PublicKeyResponse
import org.springframework.stereotype.Component

@Component
class KeyManagement(val keyService: KeyService) : KeyManagementApi {
    override fun get(keyId: String?): PublicKeyResponse {
        val key = keyId ?: throw MatrixException(ErrorResponse.Code.M_NOT_FOUND, "Missing key.")
        val (_, certificate) = keyService.key(key) ?: throw MatrixException(ErrorResponse.Code.M_NOT_FOUND, "Key not found", null, 404)
        val response = PublicKeyResponse()
        response.publicKey = certificate.publicKey.encoded.toString(Charsets.UTF_8)
        return response
    }

    override fun valid(publicKey: String?): KeyValidationResponse {
        if (publicKey == null) {
            throw MatrixException(M_MISSING_KEY, "Missing key.")
        }
        val response = KeyValidationResponse()
        response.valid = keyService.valid(publicKey, true)
        return response
    }

    override fun ephemeralValid(publicKey: String?): KeyValidationResponse {
        if (publicKey == null) {
            throw MatrixException(M_MISSING_KEY, "Missing key.")
        }
        val response = KeyValidationResponse()
        response.valid = keyService.valid(publicKey, false)
        return response
    }
}
