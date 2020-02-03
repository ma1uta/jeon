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

import io.github.ma1uta.matrix.server.model.key.KeyResponse;
import io.github.ma1uta.matrix.server.model.key.QueryRequest;
import io.github.ma1uta.matrix.server.model.key.QueryResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
 * Each homeserver publishes its public keys under /_matrix/key/v2/server/. Homeservers query for keys by either
 * getting /_matrix/key/v2/server/ directly or by querying an intermediate notary server using a /_matrix/key/v2/query API.
 * Intermediate notary servers query the /_matrix/key/v2/server/ API on behalf of another server and sign the response with their own key.
 * A server may query multiple notary servers to ensure that they all report the same public keys.
 * <br>
 * This approach is borrowed from the Perspectives Project, but modified to include the NACL keys and to use JSON instead of XML.
 * It has the advantage of avoiding a single trust-root since each server is free to pick which notary servers they trust and can
 * corroborate the keys returned by a given notary server by querying other servers.
 */
@Path("/_matrix/key/v2")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface KeyApi {

    /**
     * Gets the homeserver's published TLS fingerprints and signing keys.
     * The homeserver may have any number of active keys and may have a number of old keys.
     * <p/>
     * Intermediate notary servers should cache a response for half of its lifetime to avoid serving a stale response.
     * Originating servers should avoid returning responses that expire in less than an hour to avoid repeated requests
     * for a certificate that is about to expire. Requesting servers should limit how frequently they query for certificates
     * to avoid flooding a server with requests.
     * <p/>
     * If the server fails to respond to this request, intermediate notary servers should continue to return the last response
     * they received from the server so that the signatures of old events can still be checked.
     * <br>
     * Return: {@link KeyResponse}
     * <p>Status code 200: The homeserver's keys</p>
     *
     * @param keyId         Deprecated. Servers should not use this parameter and instead opt to return all keys,
     *                      not just the requested one. The key ID to look up.
     * @param uriInfo       Request Information.
     * @param httpHeaders   Http headers.
     * @param asyncResponse Asynchronous response.
     */
    @Operation(
        summary = "Gets the homeserver's published TLS fingerprints and signing keys.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The homeservers's keys.",
                content = @Content(
                    schema = @Schema(
                        implementation = KeyResponse.class
                    )
                )
            )
        }
    )
    @GET
    @Path("/server/{keyId}")
    void key(
        @Parameter(
            name = "keyId",
            description = "Servers should not use this parameter and instead opt to return all keys, not just the requested one."
                + " The key ID to look up.",
            deprecated = true
        ) @PathParam("keyId") String keyId,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse
    );

    /**
     * Query for another server's keys. The receiving (notary) server must sign the keys returned by the queried server.
     * <br>
     * Return: {@link QueryResponse}.
     * <p>Status code 200: The keys for the server, or an empty array if the server could not be reached and no cached keys
     * were available.</p>
     *
     * @param serverName          Required. The server's DNS name to query.
     * @param keyId               Deprecated. Servers should not use this parameter and instead opt to return all keys, not just
     *                            the requested one. The key ID to look up.
     * @param minimumValidUntilTs A millisecond POSIX timestamp in milliseconds indicating when the returned certificates will need to be
     *                            valid until to be useful to the requesting server.
     *                            If not supplied, the current time as determined by the notary server is used.
     * @param uriInfo             Request Information.
     * @param httpHeaders         Http headers.
     * @param asyncResponse       Asynchronous response.
     */
    @Operation(
        summary = "Query for another server's keys. The receiving (notary) server must sign the keys returned by the queried server.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The keys for the server, or an empty array if the server could not be reached and no cached keys"
                    + " were available.",
                content = @Content(
                    schema = @Schema(
                        implementation = KeyResponse.class
                    )
                )
            )
        }
    )
    @GET
    @Path("/query/{serverName}/{keyId}")
    void query(
        @Parameter(
            name = "serverName",
            description = "The server's DNS name to query",
            required = true
        ) @PathParam("serverName") String serverName,
        @Parameter(
            name = "keyId",
            description = "Servers should not use this parameter and instead opt to return all keys, not just the requested one."
                + "The key ID to look up.",
            deprecated = true
        ) @PathParam("keyId") String keyId,
        @Parameter(
            name = "minimumValidUntilTs",
            description = "A millisecond POSIX timestamp in milliseconds indicating when the returned certificates will need to be"
                + " valid until to be useful to the requesting server."
        ) @QueryParam("minimumValidUntilTs") Long minimumValidUntilTs,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse
    );

    /**
     * Query for keys from multiple servers in a batch format. The receiving (notary) server must sign the keys returned by
     * the queried servers.
     * <br>
     * Return: {@link QueryResponse}.
     * <p>Status code 200: The keys for the queried servers, signed by the notary server. Servers which are offline and have no cached keys
     * will not be included in the result. This may result in an empty array.</p>
     *
     * @param request       Bulk query request.
     * @param uriInfo       Request Information.
     * @param httpHeaders   Http headers.
     * @param asyncResponse Asynchronous response.
     */
    @Operation(
        summary = "Query for another server's keys. The receiving (notary) server must sign the keys returned by the queried server.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The keys for the server, or an empty array if the server could not be reached and no cached keys"
                    + " were available.",
                content = @Content(
                    schema = @Schema(
                        implementation = KeyResponse.class
                    )
                )
            )
        }
    )
    @POST
    @Path("/query")
    void bulkQuery(
        @RequestBody(
            description = "Bulk query request."
        ) QueryRequest request,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse
    );
}
