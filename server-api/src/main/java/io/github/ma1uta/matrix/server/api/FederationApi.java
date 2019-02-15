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
import io.github.ma1uta.matrix.server.model.federation.QueryAuth;
import io.github.ma1uta.matrix.server.model.federation.Transaction;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.List;
import java.util.Map;
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
     * Push messages representing live activity to another server. The destination name will be set to that of the receiving server itself.
     * Each embedded PDU in the transaction body will be processed.
     * <br>
     * Return: {@link List} of {@link int} and {@link io.github.ma1uta.matrix.server.model.federation.PduProcessingResults}.
     * <p>Status code 200: The result of processing the transaction. The server is to use this response even in the event of one or more
     * PDUs failing to be processed.</p>
     *
     * @param txnId         Required. The transaction ID.
     * @param transaction   transaction data.
     * @param uriInfo       Request Information.
     * @param httpHeaders   Http headers.
     * @param asyncResponse Asynchronous response.
     */
    @Operation(
        summary = "Push messages representing live activity to another server.",
        description = "The destination name will be set to that of the receiving server itself. Each embedded PDU in the transaction"
            + " body will be processed.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The result of processing the transaction. The server is to use this response even in the event of one"
                    + " or more PDUs failing to be processed.",
                content = @Content(
                    schema = @Schema(
                        implementation = List.class
                    )
                )
            )
        }
    )
    @PUT
    @Path("/send/{txnId}")
    void send(
        @Parameter(
            name = "transactionId",
            description = "The transaction ID."
        ) @PathParam("txnId") String txnId,
        @RequestBody Transaction transaction,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse
    );

    /**
     * Retrieves the complete auth chain for a given event.
     * <br>
     * Return: List of the {@link io.github.ma1uta.matrix.server.model.federation.PersistedDataUnit}.
     * <p>Status code 200: The auth chain for the event.</p>
     *
     * @param roomId        Required. The room ID to get the auth chain for.
     * @param eventId       Required. The event ID to get the auth chain of.
     * @param uriInfo       Request Information.
     * @param httpHeaders   Http headers.
     * @param asyncResponse Asynchronous response.
     */
    @Operation(
        summary = "Retrieves the complete auth chain for a given event.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The auth chain for the event.",
                content = @Content(
                    schema = @Schema(
                        implementation = List.class
                    )
                )
            )
        }
    )
    @GET
    @Path("/event_auth/{roomId}/{eventId}")
    void eventAuth(
        @Parameter(
            name = "roomId",
            description = "The room ID to get the auth chain for.",
            required = true
        ) @PathParam("roomId") String roomId,
        @Parameter(
            name = "eventId",
            description = "The event ID to get the auth chain of.",
            required = true
        ) @PathParam("eventId") String eventId,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse
    );

    /**
     * Compares the auth chain provided with what the receiving server has for the room ID and event ID combination.
     * <br>
     * The auth difference can be calculated in two parts, where the "remote auth" is the auth chain provided by the sending server
     * and the "local auth" is the auth chain the receiving server has. With those lists, the algorithm works bottom-up after sorting
     * each chain by depth then by event ID. The differences are then discovered and returned as the response to this API call.
     * <br>
     * Return: {@link QueryAuth}.
     * <b>Requires auth</b>: Yes.
     * <p>Status code 200: The auth chain differences, as determined by the receiver.</p>
     *
     * @param roomId        Required. The room ID to compare the auth chain in.
     * @param eventId       Required. The event ID to compare the auth chain of.
     * @param request       Request JSON body.
     * @param uriInfo       Request Information.
     * @param httpHeaders   Http headers.
     * @param asyncResponse Asynchronous response.
     */
    @Operation(
        summary = "Compares the auth chain provided with what the receiving server has for the room ID and event ID combination.",
        description = "The auth difference can be calculated in two parts, where the \"remote auth\" is the auth chain provided by"
            + " the sending server and the \"local auth\" is the auth chain the receiving server has. With those lists, the algorithm works"
            + " bottom-up after sorting each chain by depth then by event ID.The differences are then discovered and returned as"
            + " the response to this API call.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The auth chain differences, as determined by the receiver.",
                content = @Content(
                    schema = @Schema(
                        implementation = QueryAuth.class
                    )
                )
            )
        }
    )
    @POST
    @Path("/query_auth/{roomId}/{eventId}")
    void queryAuth(
        @Parameter(
            name = "roomId",
            description = "The room ID to compare the auth chain in.",
            required = true
        ) @PathParam("roomId") String roomId,
        @Parameter(
            name = "eventId",
            description = "The event ID to compare the auth chain of.",
            required = true
        ) @PathParam("eventId") String eventId,
        @RequestBody QueryAuth request,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse
    );

    /**
     * Retrieves a sliding-window history of previous PDUs that occurred on the given room. Starting from the PDU ID(s) given in the
     * "v" argument, the PDUs that preceded it are retrieved, up to a total number given by the "limit".
     * <br>
     * Return: {@link Transaction}.
     * <p>Status code 200: A transaction containing the PDUs that preceded the given event(s), including the given event(s),
     * up to the given limit.</p>
     *
     * @param roomId        Required. The room ID to backfill.
     * @param parentId      Required. The event IDs to backfill from.
     * @param limit         Required. The maximum number of PDUs to retrieve, including the given events.
     * @param uriInfo       Request Information.
     * @param httpHeaders   Http headers.
     * @param asyncResponse Asynchronous response.
     */
    @Operation(
        summary = "Retrieves a sliding-window history of previous PDUs that occurred on the given room.",
        description = "Starting from the PDU ID(s) given in the \"v\" argument, the PDUs that preceded it are retrieved, up to"
            + " a total number given by the \"limit\".",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "A transaction containing the PDUs that preceded the given event(s), including the given event(s),"
                    + " up to the given limit.",
                content = @Content(
                    schema = @Schema(
                        implementation = Transaction.class
                    )
                )
            )
        }
    )
    @GET
    @Path("/backfill/{roomId}")
    void backfill(
        @Parameter(
            name = "roomId",
            description = "The room ID to backfill.",
            required = true
        ) @PathParam("roomId") String roomId,
        @Parameter(
            name = "v",
            description = "The event IDs to backfill from.",
            required = true
        ) @QueryParam("v") String parentId,
        @Parameter(
            name = "limit",
            description = "The maximum number of PDUs to retrieve, including the given events.",
            required = true
        ) @QueryParam("limit") Long limit,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse);

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
