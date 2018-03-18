package io.github.ma1uta.identity.service

import io.github.ma1uta.identity.jdbc.Query
import io.github.ma1uta.identity.key.KeyService
import io.github.ma1uta.jeon.exception.MatrixException
import io.github.ma1uta.matrix.ErrorResponse
import io.github.ma1uta.matrix.identity.model.invitation.InvitationResponse
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Service
import java.util.*

@Service
class InvitationService(val query: Query, val template: NamedParameterJdbcTemplate, val associationService: AssociationService,
                        val keyService: KeyService) {

    fun create(medium: String, address: String, roomId: String, sender: String): InvitationResponse {
        if ("email" != medium) {
            throw MatrixException(ErrorResponse.Code.M_BAD_JSON, "Wrong medium.", null, 400)
        }
        val index = address.indexOf('@')
        if (index == -1) {
            throw MatrixException(ErrorResponse.Code.M_BAD_JSON, "Wrong address", null, 400)
        }
        val lookup = associationService.lookup(medium, address, false)
        if (lookup.mxid != null) {
            throw MatrixException(ErrorResponse.Code.M_THREEPID_IN_USE, "Medium and address are used.", null, 400)
        }
        val token = UUID.randomUUID().toString()
        val ephemeralKey = "stub"
        val displayName = address.substring(0, index)

        template.update(query.invitation.insert, mutableMapOf(Pair("medium", medium), Pair("address", address), Pair("room_id", roomId),
                Pair("sender", sender), Pair("token", token), Pair("public_key", ephemeralKey), Pair("display_name", displayName)))

        val response = InvitationResponse()
        response.displayName = displayName
        response.token = token
        response.publicKeys = listOf(ephemeralKey)
        return response
    }

    fun sendInvite(medium: String, address: String, mxid: String) {

    }
}
