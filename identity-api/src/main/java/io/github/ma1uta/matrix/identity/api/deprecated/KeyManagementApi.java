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

package io.github.ma1uta.matrix.identity.api.deprecated;

import io.github.ma1uta.matrix.ErrorResponse;
import io.github.ma1uta.matrix.identity.model.key.KeyValidationResponse;
import io.github.ma1uta.matrix.identity.model.key.PublicKeyResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 * An identity service has some long-term public-private keypairs. These are named in a scheme algorithm:identifier, e.g. ed25519:0.
 * When signing an association, the standard Signing JSON algorithm applies.
 * <br>
 * The identity server may also keep track of some short-term public-private keypairs, which may have different usage
 * and lifetime characteristics than the service's long-term keys.
 *
 * @deprecated in favor of v2.
 */
@Path("/_matrix/identity/api/v1/pubkey")
@Produces(MediaType.APPLICATION_JSON)
@Deprecated
public interface KeyManagementApi {

    /**
     * Get the public key for the passed key ID.
     * <br>
     * Return: {@link PublicKeyResponse}.
     * <p>Status code 200: The public key exists.</p>
     * <p>Status code 404: The public key was not found.</p>
     *
     * @param keyId         Required. The ID of the key. This should take the form algorithm:identifier where algorithm
     *                      identifies the signing algorithm, and the identifier is an opaque string.
     * @param uriInfo       Request information.
     * @param httpHeaders   Http headers.
     * @param asyncResponse Asynchronous response.
     */
    @Operation(
        summary = "Get the public key for the passed key ID.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The public key exists.",
                content = @Content(
                    schema = @Schema(
                        implementation = PublicKeyResponse.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "404",
                description = "The public key was not found.",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponse.class
                    )
                )
            )
        }
    )
    @GET
    @Path("/{keyId}")
    void get(
        @Parameter(
            name = "keyId",
            description = "The ID of the key. This should take the form algorithm:identifier where algorithm identifies the signing"
                + " algorithm, and the identifier is an opaque string.",
            required = true
        ) @PathParam("keyId") String keyId,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse
    );

    /**
     * Check whether a long-term public key is valid. The response should always be the same, provided the key exists.
     * <br>
     * Return: {@link KeyValidationResponse}.
     * <p>Status code 200: The validity of the public key.</p>
     *
     * @param publicKey     Required. The unpadded base64-encoded public key to check.
     * @param uriInfo       Request information.
     * @param httpHeaders   Http headers.
     * @param asyncResponse Asynchronous response.
     */
    @Operation(
        summary = "Check whether a long-term public key is valid.",
        description = "The response should always be the same, provided the key exists.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The validity of the public key.",
                content = @Content(
                    schema = @Schema(
                        implementation = KeyValidationResponse.class
                    )
                )
            )
        }
    )
    @GET
    @Path("/isvalid")
    void valid(
        @Parameter(
            name = "public_key",
            description = "The unpadded base64-encoded public key to check.",
            required = true
        ) @QueryParam("public_key") String publicKey,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse
    );

    /**
     * Check whether a short-term public key is valid.
     * <br>
     * Return: {@link KeyValidationResponse}.
     * <p>Status code 200: Whether the public key is recognised and is currently valid.</p>
     *
     * @param publicKey     Required. The unpadded base64-encoded public key to check.
     * @param uriInfo       Request information.
     * @param httpHeaders   Http headers.
     * @param asyncResponse Asynchronous response.
     */
    @Operation(
        summary = "Check whether a short-term public key is valid.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The validity of the public key.",
                content = @Content(
                    schema = @Schema(
                        implementation = KeyValidationResponse.class
                    )
                )
            )
        }
    )
    @GET
    @Path("/ephemeral/isvalid")
    void ephemeralValid(
        @Parameter(
            name = "public_key",
            description = "The unpadded base64-encoded public key to check.",
            required = true
        ) @QueryParam("public_key") String publicKey,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse
    );
}
