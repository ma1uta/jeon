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

package io.github.ma1uta.matrix.identity.api;

import io.github.ma1uta.matrix.EmptyResponse;
import io.github.ma1uta.matrix.identity.model.authentication.UserAccepts;
import io.github.ma1uta.matrix.identity.model.terms.TermsResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

/**
 * Identity Servers are encouraged to have terms of service (or similar policies) to ensure that users have agreed to their
 * data being processed by the server. To facilitate this, an identity server can respond to almost any authenticated API
 * endpoint with a HTTP 403 and the error code M_TERMS_NOT_SIGNED. The error code is used to indicate that the user must
 * accept new terms of service before being able to continue.
 * <br>
 * All endpoints which support authentication can return the M_TERMS_NOT_SIGNED error. When clients receive the error,
 * they are expected to make a call to GET /terms to find out what terms the server offers.
 * The client compares this to the m.accepted_terms account data for the user (described later) and presents the user with option
 * to accept the still-missing terms of service. After the user has made their selection, if applicable,
 * the client sends a request to POST /terms to indicate the user's acceptance.
 * The server cannot expect that the client will send acceptance for all pending terms, and the client should not expect that
 * the server will not respond with another M_TERMS_NOT_SIGNED on their next request.
 * The terms the user has just accepted are appended to m.accepted_terms.
 */
@Path("/_matrix/identity/v2/terms")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface TermsApi {

    /**
     * Gets all the terms of service offered by the server. The client is expected to filter through the terms to determine which
     * terms need acceptance from the user. Note that this endpoint does not require authentication.
     * <br>
     * Return: {@link TermsResponse}.
     * <p>Status code 200: The terms of service offered by the server.</p>
     *
     * @param uriInfo       Request information.
     * @param httpHeaders   Http headers.
     * @param asyncResponse Asynchronous response.
     */
    @Operation(
        summary = "Gets all the terms of service offered by the server.",
        description = "The client is expected to filter through the terms to determine which terms need acceptance from the user. "
            + "Note that this endpoint does not require authentication.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The terms of service offered by the server.",
                content = @Content(
                    schema = @Schema(
                        implementation = TermsResponse.class
                    )
                )
            )
        }
    )
    @GET
    @Path("")
    void get(
        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse
    );

    /**
     * Called by a client to indicate that the user has accepted/agreed to the included set of URLs.
     * Servers MUST NOT assume that the client will be sending all previously accepted URLs and should therefore append
     * the provided URLs to what the server already knows has been accepted.
     * <br>
     * Clients MUST provide the URL of the policy in the language that was presented to the user.
     * Servers SHOULD consider acceptance of any one language's URL as acceptance for all other languages of that policy.
     * <br>
     * The server should avoid returning M_TERMS_NOT_SIGNED because the client may not be accepting all terms at once.
     * <br>
     * <b>Requires auth</b>:Yes.
     * <br>
     * Return: {@link EmptyResponse}.
     * <p>Status code 200: The server has considered the user as having accepted the provided URLs.</p>
     *
     * @param userAccepts     JSON body request.
     * @param uriInfo         Request information.
     * @param httpHeaders     Http headers.
     * @param asyncResponse   Asynchronous response.
     * @param securityContext Security context.
     */
    @Operation(
        summary = "Called by a client to indicate that the user has accepted/agreed to the included set of URLs.",
        description = "Servers MUST NOT assume that the client will be sending all previously accepted URLs and should therefore append "
            + "the provided URLs to what the server already knows has been accepted.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The server has considered the user as having accepted the provided URLs.",
                content = @Content(
                    schema = @Schema(
                        implementation = EmptyResponse.class
                    )
                )
            )
        }
    )
    @POST
    @Path("")
    void accepts(
        UserAccepts userAccepts,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );
}
