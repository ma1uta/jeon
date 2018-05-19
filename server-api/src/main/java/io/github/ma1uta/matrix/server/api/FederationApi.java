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

import io.github.ma1uta.matrix.server.model.federation.Transaction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Matrix homeservers use the Federation APIs (also known as server-server APIs) to communicate with each other. Homeservers use
 * these APIs to push messages to each other in real-time, to request historic messages from each other, and to query profile and
 * presence information about users on each other's servers.
 * <p/>
 * The APIs are implemented using HTTPS GETs and PUTs between each of the servers. These HTTPS requests are strongly authenticated
 * using public key signatures at the TLS transport layer and using public key signatures in HTTP Authorization headers at the HTTP layer.
 */
@Path("/_matrix/federation/v1")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface FederationApi {

    /**
     * For active pushing of messages representing live activity "as it happens".
     * <p/>
     * The transaction_id path argument will override any ID given in the JSON body. The destination name will be set to that of the
     * receiving server itself. Each embedded PDU in the transaction body will be processed.
     *
     * @param transactionId   unique transaction identifier.
     * @param transaction     transaction data.
     * @param servletRequest  servlet request.
     * @param servletResponse servlet response.
     * @return Status code 200: not described in spec.
     */
    @PUT
    @Path("/send/{transactionId}")
    Object send(@PathParam("transactionId") String transactionId, Transaction transaction, @Context HttpServletRequest servletRequest,
                @Context HttpServletResponse servletResponse);

    /**
     * To fetch all the state of a given room.
     * <p/>
     * Retrieves a snapshot of the entire current state of the given room. The response will contain a single Transaction, inside
     * which will be a list of PDUs that encode the state.
     *
     * @param roomId          room identifier.
     * @param servletRequest  servlet request.
     * @param servletResponse servlet response.
     * @return Status code 200: current state of the specified room.
     */
    @GET
    @Path("/state/{roomId}")
    Transaction state(@PathParam("roomId") String roomId, @Context HttpServletRequest servletRequest,
                      @Context HttpServletResponse servletResponse);

    /**
     * To fetch a particular event.
     * <p/>
     * Retrieves a single event. The response will contain a partial Transaction, having just the origin, origin_server_ts and pdus fields;
     * the event will be encoded as the only PDU in the pdus list.
     *
     * @param eventId         event identifier.
     * @param servletRequest  servlet request.
     * @param servletResponse servlet response.
     * @return Status code 200: single event.
     */
    @GET
    @Path("/event/{eventId}")
    Transaction event(@PathParam("eventId") String eventId, @Context HttpServletRequest servletRequest,
                      @Context HttpServletResponse servletResponse);

    /**
     * To backfill events on a given room.
     * <p/>
     * Retrieves a sliding-window history of previous PDUs that occurred on the given room. Starting from the PDU ID(s) given in the
     * "v" argument, the PDUs that preceded it are retrieved, up to a total number given by the "limit" argument.
     *
     * @param roomId          room identifier.
     * @param parentId        parent PDU ID.
     * @param limit           total number of the PDUs.
     * @param servletRequest  servlet request.
     * @param servletResponse servlet response.
     * @return Status code 200: backfill history.
     */
    @GET
    @Path("/backfill/{roomId}")
    Transaction backfill(@PathParam("roomId") String roomId, @QueryParam("v") String parentId, @QueryParam("limit") Long limit,
                         @Context HttpServletRequest servletRequest, @Context HttpServletResponse servletResponse);

    /**
     * To stream events all the events.
     * <p/>
     * Retrieves all of the transactions later than any version given by the "v" arguments.
     *
     * @param parentId        parent PDU ID.
     * @param limit           total number of the PDU ID.
     * @param servletRequest  servlet request.
     * @param servletResponse servlet response.
     * @return Status code 200: stream events.
     */
    @GET
    @Path("/pull")
    Transaction pull(@QueryParam("v") String parentId, @QueryParam("limit") Long limit, @Context HttpServletRequest servletRequest,
                     @Context HttpServletResponse servletResponse);

    /**
     * To make a query.
     * <p/>
     * Performs a single query request on the receiving homeserver. The Query Type part of the path specifies the kind of query being
     * made, and its query arguments have a meaning specific to that kind of query. The response is a JSON-encoded object whose meaning
     * also depends on the kind of query.
     *
     * @param queryType       query type
     * @param servletRequest  servlet request.
     * @param servletResponse servlet response.
     * @return Status code 200: Query result.
     */
    @GET
    @Path("/query/{queryType}")
    Response query(@PathParam("queryType") String queryType, @Context HttpServletRequest servletRequest,
                   @Context HttpServletResponse servletResponse);
}
