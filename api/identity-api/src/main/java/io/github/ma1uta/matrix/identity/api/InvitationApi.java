package io.github.ma1uta.matrix.identity.api;

import io.github.ma1uta.matrix.identity.model.invitation.InvitationResponse;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * An identity service can store pending invitations to a user's 3pid, which will be retrieved and can be either notified on or
 * look up when the 3pid is associated with a Matrix user ID.
 *
 * @author ma1uta
 */
@Path("/_matrix/identity/v1")
public interface InvitationApi extends IdentityApi {

    /**
     * An identity service can store pending invitations to a user's 3pid, which will be retrieved and can be either notified
     * on or look up when the 3pid is associated with a Matrix user ID.
     *
     * @param medium  The literal string email.
     * @param address The email address of the invited user.
     * @param roomId  The Matrix room ID to which the user is invited.
     * @param sender  The matrix user ID of the inviting user.
     * @return result of the pending invitation.
     */
    @POST
    @Path("/store-invite")
    @Produces(MediaType.APPLICATION_JSON)
    InvitationResponse invite(@FormParam("medium") String medium, @FormParam("address") String address,
                              @FormParam("room_id") String roomId, @FormParam("sender") String sender);
}
