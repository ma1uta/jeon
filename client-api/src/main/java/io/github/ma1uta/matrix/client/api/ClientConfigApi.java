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
import io.github.ma1uta.matrix.Secured;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

/**
 * Client Behaviour.
 */
@Api(
    value = "ClientConfig",
    description = "Client Behaviour"
)
@Path("/_matrix/client/r0/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface ClientConfigApi {

    /**
     * Set some account_data for the client. This config is only visible to the user that set the account_data. The config will be
     * synced to clients in the top-level account_data.
     * <br>
     * <b>Requires auth</b>: Yes.
     *
     * @param userId          Required. The id of the user to set account_data for. The access token must be authorized to make
     *                        requests for this user id.
     * @param type            Required. The event type of the account_data to set. Custom types should be namespaced to avoid clashes.
     * @param accountData     Account data.
     * @param servletRequest  Servlet request.
     * @param servletResponse Servlet response.
     * @param securityContext Security context.
     * @return <p>Status code 200: The account_data was successfully added.</p>
     */
    @ApiOperation(
        value = "Set some account_data for the client.",
        notes = "This config is only visible to the user that set the account_data. The config will be "
            + "synced to clients in the top-level account_data."
    )
    @ApiResponses( {
        @ApiResponse(code = 200, message = "The account_data was successfully added.")
    })
    @PUT
    @Secured
    @Path("/{userId}/account_data/{type}")
    EmptyResponse addConfig(
        @ApiParam(
            value = "The id of the user to set account_data for. The access token must be authorized to make requests for this user id.",
            required = true
        ) @PathParam("userId") String userId,
        @ApiParam(
            value = "The event type of the account_data to set. Custom types should be namespaced to avoid clashes.",
            required = true
        ) @PathParam("type") String type,
        @ApiParam(
            value = "Account data"
        ) Map<String, String> accountData,

        @Context HttpServletRequest servletRequest,
        @Context HttpServletResponse servletResponse,
        @Context SecurityContext securityContext
    );

    /**
     * Set some account_data for the client on a given room. This config is only visible to the user that set the account_data.
     * The config will be synced to clients in the per-room account_data.
     * <br>
     * <b>Requires auth</b>: Yes.
     *
     * @param userId          Required. The id of the user to set account_data for. The access token must be authorized to make requests for
     *                        this user id.
     * @param roomId          Required. The id of the room to set account_data on.
     * @param type            Required. The event type of the account_data to set. Custom types should be namespaced to avoid clashes.
     * @param accountData     account data.
     * @param servletRequest  Servlet request.
     * @param servletResponse Servlet response.
     * @param securityContext Security context.
     * @return <p>Status code 200: The account_data was successfully added.</p>
     */
    @ApiOperation(
        value = "Set some account_data for the client on a given room.",
        notes = "This config is only visible to the user that set the account_data. The config will be synced to clients in the "
            + "per-room account_data."
    )
    @ApiResponses( {
        @ApiResponse(code = 200, message = "The account_data was successfully added.")
    })
    @PUT
    @Secured
    @Path("/{userId}/rooms/{roomId}/account_data/{type}")
    EmptyResponse addRoomConfig(
        @ApiParam(
            value = "The id of the user to set account_data for. The access token must be authorized to make requests for this user id.",
            required = true
        ) @PathParam("userId") String userId,
        @ApiParam(
            value = "The id of the room to set account_data on.",
            required = true
        ) @PathParam("roomId") String roomId,
        @ApiParam(
            value = "The event type of the account_data to set. Custom types should be namespaced to avoid clashes.",
            required = true
        ) @PathParam("type") String type,
        @ApiParam(
            value = "Account data"
        ) Map<String, String> accountData,

        @Context HttpServletRequest servletRequest,
        @Context HttpServletResponse servletResponse,
        @Context SecurityContext securityContext
    );
}
