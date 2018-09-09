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

package io.github.ma1uta.matrix.application.api;

import io.github.ma1uta.matrix.EmptyResponse;
import io.github.ma1uta.matrix.Secured;
import io.github.ma1uta.matrix.application.model.RoomVisibility;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * Application services can maintain their own room directories for their defined third party protocols.
 * These room directories may be accessed by clients through additional parameters on the /publicRooms client-server endpoint.
 */
@Api(
    value = "RoomService",
    description = "Application services can maintain their own room directories for their defined third party protocols."
        + " These room directories may be accessed by clients through additional parameters on the /publicRooms client-server endpoint."
)
@Path("/_matrix/client/r0/directory/list/appservice")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface RoomServiceApi {

    /**
     * Room visibility.
     */
    class Visibility {

        protected Visibility() {
        }

        public static final String PUBLIC = "public";

        public static final String PRIVATE = "private";
    }

    /**
     * Updates the visibility of a given room on the application service's room directory.
     * <br>
     * This API is similar to the room directory visibility API used by clients to update the homeserver's more general room directory.
     * <br>
     * This API requires the use of an application service access token (as_token) instead of a typical client's access_token.
     * This API cannot be invoked by users who are not identified as application services.
     *
     * @param networkId       Required. The protocol (network) ID to update the room list for. This would have been provided by
     *                        the application service as being listed as a supported protocol.
     * @param roomId          Required. The room ID to add to the directory.
     * @param roomVisibility  Required. JSON body request.
     * @param servletRequest  Servlet request.
     * @param servletResponse Servlet response.
     * @return <p>Status code 200: The room's directory visibility has been updated.</p>
     */
    @ApiOperation(
        value = "Updates the visibility of a given room on the application service's room directory.",
        notes = "This API is similar to the room directory visibility API used by clients to update the homeserver's more general"
            + " room directory.\nThis API requires the use of an application service access token (as_token) instead of a typical"
            + " client's access_token. This API cannot be invoked by users who are not identified as application services."
    )
    @ApiResponses( {
        @ApiResponse(code = 200, message = "The room's directory visibility has been updated.")
    })
    @Secured
    @PUT
    @Path("/{networkId}/{roomId}")
    EmptyResponse updateVisibility(
        @PathParam("networkId") String networkId,
        @PathParam("roomId") String roomId,
        RoomVisibility roomVisibility,

        @Context HttpServletRequest servletRequest,
        @Context HttpServletResponse servletResponse
    );
}
