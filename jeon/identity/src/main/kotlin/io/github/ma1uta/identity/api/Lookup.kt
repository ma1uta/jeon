package io.github.ma1uta.identity.api

import io.github.ma1uta.matrix.identity.api.LookupApi
import io.github.ma1uta.matrix.identity.model.lookup.BulkLookupRequest
import io.github.ma1uta.matrix.identity.model.lookup.BulkLookupResponse
import io.github.ma1uta.matrix.identity.model.lookup.LookupResponse
import org.springframework.stereotype.Component

@Component
class Lookup : LookupApi {
    override fun lookup(medium: String?, address: String?): LookupResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun bulkLookup(request: BulkLookupRequest?): BulkLookupResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
