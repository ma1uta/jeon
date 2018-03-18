package io.github.ma1uta.identity.api

import io.github.ma1uta.identity.lookup.AssociationService
import io.github.ma1uta.jeon.exception.MatrixException
import io.github.ma1uta.matrix.ErrorResponse
import io.github.ma1uta.matrix.identity.api.LookupApi
import io.github.ma1uta.matrix.identity.model.lookup.BulkLookupRequest
import io.github.ma1uta.matrix.identity.model.lookup.BulkLookupResponse
import io.github.ma1uta.matrix.identity.model.lookup.LookupResponse
import org.springframework.stereotype.Component

@Component
class Lookup(val associationService: AssociationService) : LookupApi {
    override fun lookup(medium: String?, address: String?): LookupResponse {
        if (medium.isNullOrBlank() || address.isNullOrBlank()) {
            throw MatrixException(ErrorResponse.Code.M_BAD_JSON, "Missing medium or address.")
        }
        return associationService.lookup(address!!, medium!!)
    }

    override fun bulkLookup(request: BulkLookupRequest?): BulkLookupResponse {
        if (request == null) {
            throw MatrixException(ErrorResponse.Code.M_BAD_JSON, "Missing medium or address.")
        }
        return associationService.lookup(request)
    }
}
