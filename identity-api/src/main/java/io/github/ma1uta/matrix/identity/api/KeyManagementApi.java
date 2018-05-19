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
 * When signing an association, the Matrix standard JSON signing format is used, as specified in the server-server
 * API specification under the heading "Signing Events".
 * <p/>
 * In the event of key compromise, the identity service may revoke any of its keys. An HTTP API is offered to get public keys,
 * and check whether a particular key is valid.
 * <p/>
 * The identity server may also keep track of some short-term public-private keypairs, which may have different usage
 * and lifetime characteristics than the service's long-term keys.
 *
 * @author ma1uta
 */
@Path("/_matrix/identity/api/v1/pubkey")
@Produces(MediaType.APPLICATION_JSON)
public interface KeyManagementApi {

    /**
     * Get the public key for the passed key ID.
     *
     * @param keyId           Required. The ID of the key. This should take the form algorithm:identifier where algorithm
     *                        identifies the signing algorithm, and the identifier is an opaque string.
     * @param servletRequest  servlet request.
     * @param servletResponse servlet response.
     * @return The public key exists.
     */
    @GET
    @Path("/{keyId}")
    PublicKeyResponse get(@PathParam("keyId") String keyId, @Context HttpServletRequest servletRequest,
                          @Context HttpServletResponse servletResponse);

    /**
     * Check whether a long-term public key is valid.
     *
     * @param publicKey       Required. The unpadded base64-encoded public key to check.
     * @param servletRequest  servlet request.
     * @param servletResponse servlet response.
     * @return Whether the public key is recognised and is currently valid.
     */
    @GET
    @Path("/isvalid")
    KeyValidationResponse valid(@QueryParam("public_key") String publicKey, @Context HttpServletRequest servletRequest,
                                @Context HttpServletResponse servletResponse);

    /**
     * Check whether a short-term public key is valid.
     *
     * @param publicKey       Required. The unpadded base64-encoded public key to check.
     * @param servletRequest  servlet request.
     * @param servletResponse servlet response.
     * @return Whether the public key is recognised and is currently valid.
     */
    @GET
    @Path("/ephemeral/isvalid")
    KeyValidationResponse ephemeralValid(@QueryParam("public_key") String publicKey, @Context HttpServletRequest servletRequest,
                                         @Context HttpServletResponse servletResponse);
}
