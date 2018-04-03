package io.github.ma1uta.identity.api

import io.github.ma1uta.identity.service.InvitationService
import io.github.ma1uta.jeon.exception.MatrixException
import io.github.ma1uta.matrix.ErrorResponse
import io.github.ma1uta.matrix.identity.api.InvitationApi
import io.github.ma1uta.matrix.identity.model.invitation.InvitationResponse
import org.springframework.stereotype.Component

@Component
class Invitation(val invitationService: InvitationService) : InvitationApi {
    override fun invite(medium: String?, address: String?, roomId: String?, sender: String?): InvitationResponse {
        if (medium == null || address == null || roomId == null || sender == null) {
            throw MatrixException(ErrorResponse.Code.M_BAD_JSON, "Some of the required fields are missing")
        }
        return invitationService.create(medium, address, roomId, sender)
    }
}
