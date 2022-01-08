/*
 * Copyright Anatoliy Sablin tolya@sablin.xyz
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

package io.github.ma1uta.matrix.application.api;

import io.github.ma1uta.matrix.application.model.RoomVisibility;
import io.github.ma1uta.matrix.common.EmptyResponse;
import io.github.ma1uta.matrix.common.Secured;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 * Application services can maintain their own room directories for their defined third party protocols.
 * These room directories may be accessed by clients through additional parameters on the /publicRooms client-server endpoint.
 */
@Path("/_matrix/client/{v:r0|v3}/directory/list/appservice")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface RoomServiceApi {

    /**
     * Room visibility.
     */
    class Visibility {

        protected Visibility() {
        }

        /**
         * Public visibility.
         */
        public static final String PUBLIC = "public";

        /**
         * Private visibility.
         */
        public static final String PRIVATE = "private";
    }

    /**
     * Updates the visibility of a given room on the application service's room directory.
     * <br>
     * This API is similar to the room directory visibility API used by clients to update the homeserver's more general room directory.
     * <br>
     * This API requires the use of an application service access token (as_token) instead of a typical client's access_token.
     * This API cannot be invoked by users who are not identified as application services.
     * <br>
     * Return: {@link EmptyResponse}.
     * <p>Status code 200: The room's directory visibility has been updated.</p>
     *
     * @param networkId      Required. The protocol (network) ID to update the room list for. This would have been provided by
     *                       the application service as being listed as a supported protocol.
     * @param roomId         Required. The room ID to add to the directory.
     * @param roomVisibility Required. JSON body request.
     * @param uriInfo        Information about the request.
     * @param httpHeaders    Http headers.
     * @param asyncResponse  Asynchronous response.
     */
    @Operation(
        summary = "Updates the visibility of a given room on the application service's room directory.",
        description = "This API is similar to the room directory visibility API used by clients to update the homeserver's more general"
            + " room directory.\nThis API requires the use of an application service access token (as_token) instead of a typical"
            + " client's access_token. This API cannot be invoked by users who are not identified as application services.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The room's directory visibility has been updated.",
                content = @Content(
                    schema = @Schema(
                        implementation = EmptyResponse.class
                    )
                )
            )
        }
    )
    @Secured
    @PUT
    @Path("/{networkId}/{roomId}")
    void updateVisibility(
        @Parameter(
            description = "The protocol (network) ID to update the room list for. This would have been provided by the application service"
                + " as being listed as a supported protocol.",
            required = true
        ) @PathParam("networkId") String networkId,
        @Parameter(
            description = "The room ID to add to the directory.",
            required = true
        ) @PathParam("roomId") String roomId,
        @RequestBody(
            description = "JSON body request.",
            required = true
        ) RoomVisibility roomVisibility,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse
    );
}
