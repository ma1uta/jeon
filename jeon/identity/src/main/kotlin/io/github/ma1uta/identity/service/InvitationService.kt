package io.github.ma1uta.identity.service

import com.fasterxml.jackson.databind.ObjectMapper
import io.github.ma1uta.identity.jdbc.Query
import io.github.ma1uta.identity.key.KeyService
import io.github.ma1uta.identity.model.Invitation
import io.github.ma1uta.jeon.exception.MatrixException
import io.github.ma1uta.matrix.EmptyResponse
import io.github.ma1uta.matrix.ErrorResponse
import io.github.ma1uta.matrix.Id
import io.github.ma1uta.matrix.identity.model.invitation.InvitationResponse
import io.github.ma1uta.matrix.server.model.bind.Invite
import io.github.ma1uta.matrix.server.model.bind.OnBindRequest
import io.github.ma1uta.matrix.server.model.bind.Signed
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.sql.ResultSet
import java.util.*

@Service
class InvitationService(val query: Query, val template: NamedParameterJdbcTemplate, val associationService: AssociationService,
                        val keyService: KeyService, val restTemplate: RestTemplate, val objectMapper: ObjectMapper) {

    /**
     * Store invitation.
     *
     * @param medium 'email' or 'msisdn'.
     * @param address email or phone number.
     * @param roomId roomId to invite.
     * @param sender who send invite.
     */
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
        val ephemeralKey = keyService.nextKey(false)
        val longTermKey = keyService.nextKey(true)
        val displayName = address.substring(0, index)

        template.update(query.invitation.insert, mutableMapOf(Pair("medium", medium), Pair("address", address), Pair("room_id", roomId),
                Pair("sender", sender), Pair("token", token), Pair("public_key", mutableListOf(ephemeralKey, longTermKey)),
                Pair("display_name", displayName)))

        val response = InvitationResponse()
        response.displayName = displayName
        response.token = token
        response.publicKeys = listOf(ephemeralKey, longTermKey)
        return response
    }

    /**
     * Send invite to the user's home server.
     *
     * @param medium 'email' or 'msisdn'.
     * @param address email or phone number.
     * @param mxid matrix id.
     */
    fun sendInvite(medium: String, address: String, mxid: String) {
        val invitations =
                template.query(query.invitation.findByMediumAddress, mutableMapOf(Pair("medium", medium), Pair("address", address)),
                        InvitationRowMapper())
        if (!invitations.isEmpty()) {
            val request = OnBindRequest()
            request.medium = medium
            request.address = address
            request.mxid = mxid
            request.invites = invitations.map {
                val invite = Invite()
                invite.medium = it.medium
                invite.address = it.address
                invite.mxid = mxid
                invite.roomId = it.roomId
                invite.sender = it.sender
                val signed = Signed()
                signed.mxid = mxid
                signed.token = it.token
                val signature = keyService.sign(objectMapper.writeValueAsString(signed))
                signed.signatures = signature
                invite.signed = signed
                invite
            }
            val domain = Id.domain(mxid)
            restTemplate.getForObject("https://$domain:8448/_matrix/federation/v1/3pid/onbind", EmptyResponse::class.java, request)
        }
    }

    class InvitationRowMapper : RowMapper<Invitation> {
        override fun mapRow(rs: ResultSet?, rowNum: Int) = Invitation(rs!!.getString("medium"),
                rs.getString("Address"),
                rs.getString("room_id"),
                rs.getString("sender"),
                rs.getString("token"),
                rs.getString("publick_key"),
                rs.getString("display_name"))
    }
}
