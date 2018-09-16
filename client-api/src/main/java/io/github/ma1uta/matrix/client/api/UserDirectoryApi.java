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

import io.github.ma1uta.matrix.RateLimit;
import io.github.ma1uta.matrix.Secured;
import io.github.ma1uta.matrix.client.model.userdirectory.SearchRequest;
import io.github.ma1uta.matrix.client.model.userdirectory.SearchResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

/**
 * User directory.
 * <br>
 * Provides search over all users.
 */
@Api(
    value = "UserDirectory",
    description = "User directory."
)
@Path("/_matrix/client/r0/user_directory")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface UserDirectoryApi {

    /**
     * Performs a search for users on the homeserver. The homeserver may determine which subset of users are searched, however
     * the homeserver MUST at a minimum consider the users the requesting user shares a room with and those who reside in public
     * rooms (known to the homeserver). The search MUST consider local users to the homeserver, and SHOULD query remote users as
     * part of the search.
     * <br>
     * The search is performed case-insensitively on user IDs and display names preferably using a collation determined based upon
     * the Accept-Language header provided in the request, if present.
     * <br>
     * <b>Rate-limited</b>: Yes.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <br>
     * Return: {@link SearchResponse}.
     * <p>Status code 200: The results of the search.</p>
     * <p>Status code 429: This request was rate-limited.</p>
     *
     * @param request         JSON body request.
     * @param servletRequest  Servlet request.
     * @param asyncResponse   Asynchronous response.
     * @param securityContext Security context.
     */
    @ApiOperation(value = "Performs a search for users on the homeserver.",
        notes = "The homeserver may determine which subset of users are searched, however the homeserver MUST at a minimum"
            + " consider the users the requesting user shares a room with and those who reside in public rooms (known to the homeserver)."
            + " The search MUST consider local users to the homeserver, and SHOULD query remote users as part of the search."
            + " The search is performed case-insensitively on user IDs and display names preferably using a collation determined based upon"
            + " * the Accept-Language header provided in the request, if present.",
        response = SearchResponse.class
    )
    @ApiResponses( {
        @ApiResponse(code = 200, message = "The results of the search."),
        @ApiResponse(code = 429, message = "This request was rate-limited.")
    })
    @POST
    @RateLimit
    @Secured
    @Path("/search")
    void searchUsers(
        @ApiParam(
            value = "JSON body request."
        ) SearchRequest request,

        @Context HttpServletRequest servletRequest,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );
}
