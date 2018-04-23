/*
 * Copyright sablintolya@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
@Path("/_matrix/identity/api/v1")
public interface InvitationApi {

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
