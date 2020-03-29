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

package io.github.ma1uta.matrix.server.api;

import io.github.ma1uta.matrix.server.model.federation.QueryAuth;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
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
 * Matrix homeservers use the Federation APIs (also known as server-server APIs) to communicate with each other. Homeservers use
 * these APIs to push messages to each other in real-time, to request historic messages from each other, and to query profile and
 * presence information about users on each other's servers.
 * <br>
 * The APIs are implemented using HTTPS GETs and PUTs between each of the servers. These HTTPS requests are strongly authenticated
 * using public key signatures at the TLS transport layer and using public key signatures in HTTP Authorization headers at the HTTP layer.
 */
@Path("/_matrix/federation/v2")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface DeprecatedApi {

    /**
     * Compares the auth chain provided with what the receiving server has for the room ID and event ID combination.
     * <br>
     * The auth difference can be calculated in two parts, where the "remote auth" is the auth chain provided by the sending server
     * and the "local auth" is the auth chain the receiving server has. With those lists, the algorithm works bottom-up after sorting
     * each chain by depth then by event ID. The differences are then discovered and returned as the response to this API call.
     * <br>
     * Return: {@link QueryAuth}.
     * <b>Requires auth</b>: Yes.
     * <p>Status code 200: The auth chain differences, as determined by the receiver.</p>
     *
     * @param roomId        Required. The room ID to compare the auth chain in.
     * @param eventId       Required. The event ID to compare the auth chain of.
     * @param request       Request JSON body.
     * @param uriInfo       Request Information.
     * @param httpHeaders   Http headers.
     * @param asyncResponse Asynchronous response.
     *
     * @deprecated https://github.com/matrix-org/matrix-doc/pull/2451
     */
    @Deprecated(forRemoval = true)
    @Operation(
        summary = "Compares the auth chain provided with what the receiving server has for the room ID and event ID combination.",
        description = "The auth difference can be calculated in two parts, where the \"remote auth\" is the auth chain provided by"
            + " the sending server and the \"local auth\" is the auth chain the receiving server has. With those lists, the algorithm works"
            + " bottom-up after sorting each chain by depth then by event ID.The differences are then discovered and returned as"
            + " the response to this API call.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The auth chain differences, as determined by the receiver.",
                content = @Content(
                    schema = @Schema(
                        implementation = QueryAuth.class
                    )
                )
            )
        }
    )
    @POST
    @Path("/query_auth/{roomId}/{eventId}")
    void queryAuth(
        @Parameter(
            name = "roomId",
            description = "The room ID to compare the auth chain in.",
            required = true
        ) @PathParam("roomId") String roomId,
        @Parameter(
            name = "eventId",
            description = "The event ID to compare the auth chain of.",
            required = true
        ) @PathParam("eventId") String eventId,
        @RequestBody QueryAuth request,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse
    );


}
