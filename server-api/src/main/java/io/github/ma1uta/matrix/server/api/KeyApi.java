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

package io.github.ma1uta.matrix.server.api;

import io.github.ma1uta.matrix.server.model.key.KeyResponse;
import io.github.ma1uta.matrix.server.model.key.QueryRequest;
import io.github.ma1uta.matrix.server.model.key.QueryResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

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
     * Homeservers publish the allowed TLS fingerprints and signing keys in a JSON object at /_matrix/key/v2/server/{key_id}.
     * The response contains a list of verify_keys that are valid for signing federation requests made by the server and for signing events.
     * It contains a list of old_verify_keys which are only valid for signing events. Finally the response contains a list of TLS
     * certificate fingerprints to validate any connection made to the server.
     * <br>
     * A server may have multiple keys active at a given time. A server may have any number of old keys. It is recommended that servers
     * return a single JSON response listing all of its keys whenever any key_id is requested to reduce the number of round trips needed
     * to discover the relevant keys for a server. However a server may return a different responses for a different key_id.
     * <br>
     * The tls_certificates contain a list of hashes of the X.509 TLS certificates currently used by the server. The list must
     * include SHA-256 hashes for every certificate currently in use by the server. These fingerprints are valid until the millisecond
     * POSIX timestamp in valid_until_ts.
     * <br>
     * The verify_keys can be used to sign requests and events made by the server until the millisecond POSIX timestamp in
     * valid_until_ts. If a homeserver receives an event with a origin_server_ts after the valid_until_ts then it should request
     * that key_id for the originating server to check whether the key has expired.
     * <br>
     * The old_verify_keys can be used to sign events with an origin_server_ts before the expired_ts. The expired_ts is a millisecond
     * POSIX timestamp of when the originating server stopped using that key.
     * <br>
     * Intermediate notary servers should cache a response for half of its remaining life time to avoid serving a stale response.
     * Originating servers should avoid returning responses that expire in less than an hour to avoid repeated requests for an about
     * to expire certificate. Requesting servers should limit how frequently they query for certificates to avoid flooding
     * a server with requests.
     * <br>
     * If a server goes offline intermediate notary servers should continue to return the last response they received from that
     * server so that the signatures of old events sent by that server can still be checked.
     *
     * @param keyId           key id.
     * @param servletRequest  servlet request.
     * @param servletResponse servlet response.
     * @return <p>Status code 200: server key.</p>
     */
    @GET
    @Path("/server/{keyId}")
    KeyResponse key(@PathParam("keyId") String keyId, @Context HttpServletRequest servletRequest,
                    @Context HttpServletResponse servletResponse);

    /**
     * Servers may offer a query API _matrix/key/v2/query/ for getting the keys for another server. This API used to GET at
     * list of JSON objects for a given server. Either way the response is a list of JSON objects containing the JSON published
     * by the server under _matrix/key/v2/server/ signed by both the originating server and by this server.
     * <br>
     * This API can return keys for servers that are offline be using cached responses taken from when the server was online.
     * Keys can be queried from multiple servers to mitigate against DNS spoofing.
     *
     * @param serverName          server name.
     * @param keyId               key identifier
     * @param minimumValidUntilTs is a millisecond POSIX timestamp indicating when the returned certificate will need to be valid
     *                            until to be useful to the requesting server. This can be set using the maximum origin_server_ts of an
     *                            batch of events that a requesting server is trying to validate. This allows an intermediate notary
     *                            server to give a prompt cached response even if the originating server is offline.
     * @param servletRequest      servlet request.
     * @param servletResponse     servlet response.
     * @return <p>Status code 200: queried key.</p>
     */
    @GET
    @Path("/query/${serverName}/${keyId}")
    QueryResponse query(@PathParam("serverName") String serverName, @PathParam("keyId") String keyId,
                        @QueryParam("minimumValidUntilTs") Long minimumValidUntilTs, @Context HttpServletRequest servletRequest,
                        @Context HttpServletResponse servletResponse);

    /**
     * Servers may offer a query API _matrix/key/v2/query/ for getting the keys for another server. This API used to POST a bulk query
     * for a number of keys from a number of servers. Either way the response is a list of JSON objects containing the JSON published
     * by the server under _matrix/key/v2/server/ signed by both the originating server and by this server.
     * <br>*
     * This API can return keys for servers that are offline be using cached responses taken from when the server was online.
     * Keys can be queried from multiple servers to mitigate against DNS spoofing.
     *
     * @param request         bulk query request.
     * @param servletRequest  servlet request.
     * @param servletResponse servlet response.
     * @return <p>Status code 200: queried keys.</p>
     */
    @POST
    @Path("/query")
    QueryResponse bulkQuery(QueryRequest request, @Context HttpServletRequest servletRequest, @Context HttpServletResponse servletResponse);
}
