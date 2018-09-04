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

import io.github.ma1uta.matrix.identity.model.key.KeyValidationResponse;
import io.github.ma1uta.matrix.identity.model.key.PublicKeyResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * An identity service has some long-term public-private keypairs. These are named in a scheme algorithm:identifier, e.g. ed25519:0.
 * When signing an association, the standard Signing JSON algorithm applies.
 * <br>
 * The identity server may also keep track of some short-term public-private keypairs, which may have different usage
 * and lifetime characteristics than the service's long-term keys.
 */
@Api(
    value = "Key management API.",
    description = "An identity service has some long-term public-private keypairs. These are named in a scheme algorithm:identifier,"
        + " e.g. ed25519:0. When signing an association, the standard Signing JSON algorithm applies.\nThe identity server may also keep"
        + " track of some short-term public-private keypairs, which may have different usage and lifetime characteristics than"
        + " the service's long-term keys."
)
@Path("/_matrix/identity/api/v1/pubkey")
@Produces(MediaType.APPLICATION_JSON)
public interface KeyManagementApi {

    /**
     * Get the public key for the passed key ID.
     *
     * @param keyId           Required. The ID of the key. This should take the form algorithm:identifier where algorithm
     *                        identifies the signing algorithm, and the identifier is an opaque string.
     * @param servletRequest  Servlet request.
     * @param servletResponse Servlet response.
     * @return <p>Status code 200: The public key exists.</p>
     * <p>Status code 404: The public key was not found.</p>
     */
    @ApiOperation(
        value = "Get the public key for the passed key ID."
    )
    @ApiResponses( {
        @ApiResponse(code = 200, message = "The public key exists."),
        @ApiResponse(code = 404, message = "The public key was not found.")
    })
    @GET
    @Path("/{keyId}")
    PublicKeyResponse get(
        @ApiParam(
            name = "keyId",
            value = "The ID of the key. This should take the form algorithm:identifier where algorithm identifies the signing"
                + " algorithm, and the identifier is an opaque string.",
            required = true
        ) @PathParam("keyId") String keyId,

        @Context HttpServletRequest servletRequest,
        @Context HttpServletResponse servletResponse
    );

    /**
     * Check whether a long-term public key is valid. The response should always be the same, provided the key exists.
     *
     * @param publicKey       Required. The unpadded base64-encoded public key to check.
     * @param servletRequest  Servlet request.
     * @param servletResponse Servlet response.
     * @return <p>Status code 200: The validity of the public key.</p>
     */
    @ApiOperation(
        value = "Check whether a long-term public key is valid.",
        notes = "The response should always be the same, provided the key exists."
    )
    @ApiResponses( {
        @ApiResponse(code = 200, message = "The validity of the public key.")
    })
    @GET
    @Path("/isvalid")
    KeyValidationResponse valid(
        @ApiParam(
            name = "public_key",
            value = "The unpadded base64-encoded public key to check.",
            required = true
        ) @QueryParam("public_key") String publicKey,

        @Context HttpServletRequest servletRequest,
        @Context HttpServletResponse servletResponse
    );

    /**
     * Check whether a short-term public key is valid.
     *
     * @param publicKey       Required. The unpadded base64-encoded public key to check.
     * @param servletRequest  Servlet request.
     * @param servletResponse Servlet response.
     * @return <p>Status code 200: Whether the public key is recognised and is currently valid.</p>
     */
    @ApiOperation(
        value = "Check whether a short-term public key is valid."
    )
    @ApiResponses( {
        @ApiResponse(code = 200, message = "The validity of the public key.")
    })
    @GET
    @Path("/ephemeral/isvalid")
    KeyValidationResponse ephemeralValid(
        @ApiParam(
            name = "public_key",
            value = "The unpadded base64-encoded public key to check.",
            required = true
        ) @QueryParam("public_key") String publicKey,

        @Context HttpServletRequest servletRequest,
        @Context HttpServletResponse servletResponse
    );
}
