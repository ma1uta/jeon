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

import io.github.ma1uta.matrix.Event;
import io.github.ma1uta.matrix.Page;
import io.github.ma1uta.matrix.server.model.federation.OpenIdResponse;
import io.github.ma1uta.matrix.server.model.federation.PublicRoomResponse;
import io.github.ma1uta.matrix.server.model.federation.Transaction;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * Matrix homeservers use the Federation APIs (also known as server-server APIs) to communicate with each other. Homeservers use
 * these APIs to push messages to each other in real-time, to request historic messages from each other, and to query profile and
 * presence information about users on each other's servers.
 * <br>
 * The APIs are implemented using HTTPS GETs and PUTs between each of the servers. These HTTPS requests are strongly authenticated
 * using public key signatures at the TLS transport layer and using public key signatures in HTTP Authorization headers at the HTTP layer.
 */
@Path("/_matrix/federation/v1")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface FederationApi {

    /**
     * For active pushing of messages representing live activity "as it happens".
     * <br>
     * The transaction_id path argument will override any ID given in the JSON body. The destination name will be set to that of the
     * receiving server itself. Each embedded PDU in the transaction body will be processed.
     *
     * @param transactionId   unique transaction identifier.
     * @param transaction     transaction data.
     * @param servletRequest  servlet request.
     * @param servletResponse servlet response.
     */
    @PUT
    @Path("/send/{transactionId}")
    void send(
        @PathParam("transactionId") String transactionId,
        Transaction transaction,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse
    );

    /**
     * To fetch all the state of a given room.
     * <br>
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
    Transaction state(
        @PathParam("roomId") String roomId,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse
    );

    /**
     * !!! Not described in spec.
     *
     * @param roomId          room identifier.
     * @param servletRequest  servlet request.
     * @param servletResponse servlet response.
     * @return Status code 200:
     */
    @GET
    @Path("/state_ids/{roomId}")
    Transaction stateIds(@PathParam("roomId") String roomId, @Context UriInfo uriInfo, @Context HttpHeaders httpHeaders,
                         @Context HttpServletResponse servletResponse);

    /**
     * To fetch a particular event.
     * <br>
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
    Transaction event(@PathParam("eventId") String eventId, @Context UriInfo uriInfo, @Context HttpHeaders httpHeaders,
                      @Context HttpServletResponse servletResponse);

    /**
     * To backfill events on a given room.
     * <br>
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
                         @Context UriInfo uriInfo, @Context HttpHeaders httpHeaders, @Context HttpServletResponse servletResponse);

    /**
     * To stream events all the events.
     * <br>
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
    Transaction pull(@QueryParam("v") String parentId, @QueryParam("limit") Long limit, @Context UriInfo uriInfo,
                     @Context HttpHeaders httpHeaders,
                     @Context HttpServletResponse servletResponse);

    /**
     * To make a query.
     * <br>
     * Performs a single query request on the receiving homeserver. The Query Type part of the path specifies the kind of query being
     * made, and its query arguments have a meaning specific to that kind of query. The response is a JSON-encoded object whose meaning
     * also depends on the kind of query.
     *
     * @param queryType       query type.
     * @param query           query data.
     * @param servletRequest  servlet request.
     * @param servletResponse servlet response.
     * @return Status code 200: Query result.
     */
    @GET
    @Path("/query/{queryType}")
    Response query(@PathParam("queryType") String queryType, Map<String, Object> query, @Context UriInfo uriInfo,
                   @Context HttpHeaders httpHeaders,
                   @Context HttpServletResponse servletResponse);

    /**
     * To make a join request.
     * <br>
     * !!! Not described in spec.
     *
     * @param context         context (?).
     * @param userId          user mxid.
     * @param servletRequest  servlet request.
     * @param servletResponse servlet response.
     * @return Status code 200: Partial Event.
     */
    @GET
    @Path("/make_join/{context}/{userId}")
    Event makeJoin(@PathParam("context") String context, @PathParam("userId") String userId, @Context UriInfo uriInfo,
                   @Context HttpHeaders httpHeaders,
                   @Context HttpServletResponse servletResponse);

    /**
     * To send a join request.
     * <br>
     * !!! Not described in spec.
     *
     * @param context         context (?).
     * @param eventId         event identifier.
     * @param servletRequest  servlet request.
     * @param servletResponse servlet response.
     * @return Status code 200: (?).
     */
    @PUT
    @Path("/send_join/{context}/{eventId}")
    Response sendJoin(@PathParam("context") String context, @PathParam("eventId") String eventId,
                      @Context UriInfo uriInfo, @Context HttpHeaders httpHeaders, @Context HttpServletResponse servletResponse);

    /**
     * To make a leave request.
     * <br>
     * !!! Not described in spec.
     *
     * @param context         context (?).
     * @param userId          user mxid.
     * @param servletRequest  servlet request.
     * @param servletResponse servlet response.
     * @return Status code 200: (?).
     */
    @GET
    @Path("/make_leave/{context}/{userId}")
    Response makeLeave(@PathParam("context") String context, @PathParam("userId") String userId, @Context UriInfo uriInfo,
                       @Context HttpHeaders httpHeaders,
                       @Context HttpServletResponse servletResponse);

    /**
     * To send a leave request.
     * <br>
     * !!! Not described in spec.
     *
     * @param roomId          room id.
     * @param txid            transaction id (?).
     * @param servletRequest  servlet request.
     * @param servletResponse servlet response.
     * @return Status code 200: (?).
     */
    @PUT
    @Path("/send_leave/{roomId}/{txid}")
    Response sendLeave(@PathParam("roomId") String roomId, @PathParam("txid") String txid, @Context UriInfo uriInfo,
                       @Context HttpHeaders httpHeaders,
                       @Context HttpServletResponse servletResponse);

    /**
     * (?).
     * <br>
     * !!! Not described in spec.
     *
     * @param context         context (?).
     * @param eventId         event id.
     * @param servletRequest  servlet request.
     * @param servletResponse servlet response.
     * @return Status code 200: (?).
     */
    @GET
    @Path("/event_auth/{context}/{eventId}")
    Response eventAuth(@PathParam("context") String context, @PathParam("eventId") String eventId,
                       @Context UriInfo uriInfo, @Context HttpHeaders httpHeaders, @Context HttpServletResponse servletResponse);

    /**
     * Send invite.
     * <br>
     * !!! Not described in spec.
     *
     * @param context         context (?).
     * @param eventId         event id.
     * @param servletRequest  servlet request.
     * @param servletResponse servlet response.
     * @return Status code 200: pdu of the invite event.
     */
    @PUT
    @Path("/invite/{context}/{eventId}")
    Response invite(@PathParam("context") String context, @PathParam("eventId") String eventId, @Context UriInfo uriInfo,
                    @Context HttpHeaders httpHeaders,
                    @Context HttpServletResponse servletResponse);

    /**
     * To get 3pid invites of the specified room.
     * <br>
     * !!! Not described in spec.
     *
     * @param roomId          room identifier.
     * @param servletRequest  servlet request.
     * @param servletResponse servlet response.
     * @return Status code 200: membership event.
     */
    @PUT
    @Path("/exchange_third_party_invite/{roomId}")
    Response exchangeThirdPartyInvite(@PathParam("roomId") String roomId, @Context UriInfo uriInfo, @Context HttpHeaders httpHeaders,
                                      @Context HttpServletResponse servletResponse);

    /**
     * Query a user keys (?).
     * <br>
     * !!! Not described in spec.
     *
     * @param query           query.
     * @param servletRequest  servlet request.
     * @param servletResponse servlet response.
     * @return Status code 200: user keys (?).
     */
    @POST
    @Path("/user/keys/query")
    Response userKeysQuery(Map<String, Map<String, List<String>>> query, @Context UriInfo uriInfo, @Context HttpHeaders httpHeaders,
                           @Context HttpServletResponse servletResponse);

    /**
     * To get user's devices.
     * <br>
     * !!! Not described in spec.
     *
     * @param userId          user identifier.
     * @param servletRequest  servlet request.
     * @param servletResponse servlet response.
     * @return Status code 200: user devices.
     */
    @GET
    @Path("/user/devices/{userId}")
    Response userDevices(@PathParam("userId") String userId, @Context UriInfo uriInfo, @Context HttpHeaders httpHeaders,
                         @Context HttpServletResponse servletResponse);

    /**
     * To claim user ont-time-key.
     * <br>
     * !!! Not described in spec.
     *
     * @param servletRequest  servlet request.
     * @param servletResponse servlet response.
     * @return Status code 200: user's one-time key.
     */
    @POST
    @Path("/user/keys/claim")
    Response userKeysClaim(@Context UriInfo uriInfo, @Context HttpHeaders httpHeaders, @Context HttpServletResponse servletResponse);

    /**
     * To query auth chains.
     * <br>
     * !!! Not described in spec.
     *
     * @param context         context (?).
     * @param eventId         event identifier.
     * @param request         request data (?).
     * @param servletRequest  servlet request.
     * @param servletResponse servlet response.
     * @return Status code 200: processed auth chains (?).
     */
    @POST
    @Path("/query_auth/{context}/{eventId}")
    Response queryAuth(@PathParam("context") String context, @PathParam("eventId") String eventId, Map<String, Object> request,
                       @Context UriInfo uriInfo, @Context HttpHeaders httpHeaders, @Context HttpServletResponse servletResponse);

    /**
     * To get missing events (?).
     * <br>
     * !!! Not described in spec.
     *
     * @param roomId          room identifier.
     * @param servletRequest  servlet request.
     * @param servletResponse servlet response.
     * @return Status code 200: missing events.
     */
    @POST
    @Path("/get_missing_events/{roomId}")
    Response getMissingEvents(@PathParam("roomId") String roomId, @Context UriInfo uriInfo, @Context HttpHeaders httpHeaders,
                              @Context HttpServletResponse servletResponse);

    /**
     * Exchange a bearer token for information about a user.
     * <br>
     * The response format should be compatible with:
     * <a href="http://openid.net/specs/openid-connect-core-1_0.html#UserInfoResponse">UserInfoResponse</a>
     * <br>
     * !!! Not described in spec.
     *
     * @param servletRequest  servlet request.
     * @param servletResponse servlet response.
     * @return Status code 200: user info.
     * Status code 401: missing access_token.
     */
    @GET
    @Path("/openid/userinfo")
    OpenIdResponse openId(@Context UriInfo uriInfo, @Context HttpHeaders httpHeaders, @Context HttpServletResponse servletResponse);

    /**
     * Fetch the public room list for this server.
     * <br>
     * This API returns information in the same format as /publicRooms on the
     * client API, but will only ever include local public rooms and hence is
     * intended for consumption by other home servers.
     * <br>
     * !!! Not described in spec.
     *
     * @param limit                limit retrieved rooms.
     * @param since                since token.
     * @param includeAllNetworks   include or not rooms from other servers.
     * @param thirdPartyInstanceId 3pid server id.
     * @param servletRequest       servlet request.
     * @param servletResponse      servlet response.
     * @return Status code 200: public rooms.
     */
    @GET
    @Path("/publicRooms")
    Page<PublicRoomResponse> publicRooms(@QueryParam("limit") Integer limit, @QueryParam("since") String since,
                                         @QueryParam("include_all_networks") Boolean includeAllNetworks,
                                         @QueryParam("third_party_instance_id") String thirdPartyInstanceId,
                                         @Context UriInfo uriInfo, @Context HttpHeaders httpHeaders,
                                         @Context HttpServletResponse servletResponse);
}
