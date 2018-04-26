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

package io.github.ma1uta.matrix.client.api;

import io.github.ma1uta.matrix.EmptyResponse;

import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Client Behaviour.
 * <p/>
 * <a href="https://matrix.org/docs/spec/client_server/r0.3.0.html#id424">Specification.</a>
 */
@Path("/_matrix/client/r0/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface ClientConfigApi {

    /**
     * Set some account_data for the client. This config is only visible to the user that set the account_data. The config will be
     * synced to clients in the top-level account_data.
     * <b>Requires auth</b>: Yes.
     *
     * @param userId      Required. The id of the user to set account_data for. The access token must be authorized to make
     *                    requests for this user id.
     * @param type        Required. The event type of the account_data to set. Custom types should be namespaced to avoid clashes.
     * @param accountData account data.
     * @return Status code 200: The account_data was successfully added.
     */
    @PUT
    @Path("/{userId}/account_data/{type}")
    EmptyResponse addConfig(@PathParam("userId") String userId, @PathParam("type") String type, Map<String, String> accountData);

    /**
     * Set some account_data for the client on a given room. This config is only visible to the user that set the account_data.
     * The config will be synced to clients in the per-room account_data.
     * <b>Requires auth</b>: Yes.
     *
     * @param userId      Required. The id of the user to set account_data for. The access token must be authorized to make requests for
     *                    this user id.
     * @param type        Required. The id of the room to set account_data on.
     * @param roomId      Required. The event type of the account_data to set. Custom types should be namespaced to avoid clashes.
     * @param accountData account data.
     * @return Status code 200: The account_data was successfully added.
     */
    @PUT
    @Path("/{userId}/rooms/{roomId}/account_data/{type}")
    EmptyResponse addConfig(@PathParam("userId") String userId, @PathParam("type") String type, @PathParam("roomId") String roomId,
                            Map<String, String> accountData);
}
