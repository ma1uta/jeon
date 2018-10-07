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

import io.github.ma1uta.matrix.ErrorResponse;
import io.github.ma1uta.matrix.identity.model.signing.SigningRequest;
import io.github.ma1uta.matrix.identity.model.signing.SigningResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 * To aid clients who may not be able to perform crypto themselves, the identity server offers some crypto functionality to help
 * in accepting invitations. This is less secure than the client doing it itself, but may be useful where this isn't possible.
 */
@Path("/_matrix/identity/api/v1")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface SigningApi {

    /**
     * Sign invitation details.
     * <br>
     * The identity server will look up token which was stored in a call to store-invite, and fetch the sender of the invite.
     * <br>
     * Return: {@link SigningResponse}.
     * <p>Status code 200: The signed JSON of the mxid, sender, and token.</p>
     * <p>Status code 404: The token was not found.</p>
     *
     * @param request       JSON body request.
     * @param uriInfo       Request information.
     * @param httpHeaders   Http headers.
     * @param asyncResponse Asynchronous response.
     */
    @Operation(
        summary = "Sign invitation details.",
        description = "The identity server will look up token which was stored in a call to store-invite, and fetch the sender"
            + " of the invite.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The signed JSON of the mxid, sender, and token.",
                content = @Content(
                    schema = @Schema(
                        implementation = SigningResponse.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "404",
                description = "The token was not found.",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponse.class
                    )
                )
            )
        }
    )
    @POST
    @Path("/sign-ed25519")
    void sign(
        @RequestBody(
            description = "JSON body request.",
            required = true
        ) SigningRequest request,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse
    );
}
