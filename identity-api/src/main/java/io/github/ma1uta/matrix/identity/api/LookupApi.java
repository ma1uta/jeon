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

import io.github.ma1uta.matrix.identity.model.lookup.HashDetails;
import io.github.ma1uta.matrix.identity.model.lookup.LookupRequest;
import io.github.ma1uta.matrix.identity.model.lookup.LookupResponse;
import io.github.ma1uta.matrix.identity.model.lookup.SingleLookupResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
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
 * Association lookup.
 */
@Path("/_matrix/identity/v2")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface LookupApi {

    /**
     * Gets parameters for hashing identifiers from the server. This can include any of the algorithms defined in this specification.
     * <br>
     * <b>Requires auth</b>:Yes.
     * <br>
     * Return: {@link HashDetails}.
     * <p>Status code 200: The hash function information.</p>
     *
     * @param uriInfo         Request information.
     * @param httpHeaders     Http headers.
     * @param asyncResponse   Asynchronous response.
     * @param securityContext Security context.
     */
    @Operation(
        summary = "Gets parameters for hashing identifiers from the server.",
        description = "This can include any of the algorithms defined in this specification.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The hash function information.",
                content = @Content(
                    schema = @Schema(
                        implementation = HashDetails.class
                    )
                )
            )
        }
    )
    @GET
    @Path("/hash_details")
    void hashDetails(
        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );

    /**
     * Looks up the set of Matrix User IDs which have bound the 3PIDs given, if bindings are available.
     * Note that the format of the addresses is defined later in this specification.
     * <br>
     * <b>Requires auth</b>:Yes.
     * <br>
     * Return: {@link LookupResponse}.
     * <p>Status code 200: The associations for any matched addresses.</p>
     * <p>Status code 400: The client's request was invalid in some way. One possible problem could be the pepper being invalid after
     * the server has rotated it - this is presented with the M_INVALID_PEPPER error code.
     * Clients SHOULD make a call to /hash_details to get a new pepper in this scenario, being careful to avoid retry loops.
     * M_INVALID_PARAM can also be returned to indicate the client supplied an algorithm that is unknown to the server.</p>
     *
     * @param lookupRequest JSON body request.
     * @param uriInfo       Request information.
     * @param httpHeaders   Http headers.
     * @param asyncResponse Asynchronous response.
     */
    @Operation(
        summary = "Look up the Matrix user ID for a 3pid.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The association for that 3pid, or an empty object if no association is known.",
                content = @Content(
                    schema = @Schema(
                        implementation = SingleLookupResponse.class
                    )
                )
            )

        }
    )
    @POST
    @Path("/lookup")
    void lookup(
        @RequestBody(
            description = "JSON body request."
        ) LookupRequest lookupRequest,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse
    );
}
