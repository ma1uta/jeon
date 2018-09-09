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

import io.github.ma1uta.matrix.identity.model.signing.SigningRequest;
import io.github.ma1uta.matrix.identity.model.signing.SigningResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * To aid clients who may not be able to perform crypto themselves, the identity server offers some crypto functionality to help
 * in accepting invitations. This is less secure than the client doing it itself, but may be useful where this isn't possible.
 */
@Api(
    value = "EphemeralInvitationSigning",
    description = "To aid clients who may not be able to perform crypto themselves, the identity server offers some crypto functionality"
        + " to help in accepting invitations. This is less secure than the client doing it itself, but may be useful where this"
        + " isn't possible."
)
@Path("/_matrix/identity/api/v1")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface SigningApi {

    /**
     * Sign invitation details.
     * <br>
     * The identity server will look up token which was stored in a call to store-invite, and fetch the sender of the invite.
     *
     * @param request         JSON body request.
     * @param servletRequest  Servlet request.
     * @param servletResponse Servlet response.
     * @return <p>Status code 200: The signed JSON of the mxid, sender, and token.</p>
     * <p>Status code 404: The token was not found.</p>
     */
    @ApiOperation(
        value = "Sign invitation details.",
        notes = "The identity server will look up token which was stored in a call to store-invite, and fetch the sender of the invite."
    )
    @ApiResponses( {
        @ApiResponse(code = 200, message = "The signed JSON of the mxid, sender, and token."),
        @ApiResponse(code = 404, message = "The token was not found.")
    })
    @POST
    @Path("/sign-ed25519")
    SigningResponse sign(
        @ApiParam(
            value = "JSON body request.",
            required = true
        ) SigningRequest request,

        @Context HttpServletRequest servletRequest,
        @Context HttpServletResponse servletResponse
    );
}
