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

import io.github.ma1uta.matrix.EmptyResponse;
import io.github.ma1uta.matrix.ErrorResponse;
import io.github.ma1uta.matrix.Id;
import io.github.ma1uta.matrix.Page;
import io.github.ma1uta.matrix.server.model.federation.EventContainer;
import io.github.ma1uta.matrix.server.model.federation.InviteV1Request;
import io.github.ma1uta.matrix.server.model.federation.MakeResponse;
import io.github.ma1uta.matrix.server.model.federation.OpenIdResponse;
import io.github.ma1uta.matrix.server.model.federation.PersistedDataUnit;
import io.github.ma1uta.matrix.server.model.federation.PublicRoomResponse;
import io.github.ma1uta.matrix.server.model.federation.QueryAuth;
import io.github.ma1uta.matrix.server.model.federation.RoomStateResponse;
import io.github.ma1uta.matrix.server.model.federation.SendRequest;
import io.github.ma1uta.matrix.server.model.federation.StateIdResponse;
import io.github.ma1uta.matrix.server.model.federation.StateResponse;
import io.github.ma1uta.matrix.server.model.federation.Transaction;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.List;
import java.util.Map;
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
public interface FederationV1Api {

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
     * Retrieves previous events that the sender is missing. This is done by doing a breadth-first walk of the prev_events for
     * the latest_events, ignoring any events in earliest_events and stopping at the limit.
     * <br>
     * <b>Requires auth</b>: Yes.
     * Return: List of the {@link List} of the {@link io.github.ma1uta.matrix.server.model.federation.PersistedDataUnit}.
     * <p>Status code 200: The previous events for latest_events, excluding any earliest_events, up to the provided limit.</p>
     *
     * @param roomId         Required. The room ID to search in.
     * @param limit          The maximum number of events to retrieve. Defaults to 10.
     * @param minDepth       The minimum depth of events to retrieve. Defaults to 0.
     * @param earliestEvents Required. The latest event IDs that the sender already has. These are skipped when retrieving
     *                       the previous events of latest_events.
     * @param latestEvents   Required. The event IDs to retrieve the previous events for.
     * @param uriInfo        Request Information.
     * @param httpHeaders    Http headers.
     * @param asyncResponse  Asynchronous response.
     */
    @Operation(
        summary = "Retrieves previous events that the sender is missing.",
        description = "This is done by doing a breadth-first walk of the prev_events for the latest_events,"
            + " ignoring any events in earliest_events and stopping at the limit.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The previous events for latest_events, excluding any earliest_events, up to the provided limit.",
                content = @Content(
                    schema = @Schema(
                        implementation = List.class
                    )
                )
            )
        }
    )
    @POST
    @Path("/get_missing_events/{roomId}")
    void getMissingEvents(
        @Parameter(
            name = "roomId",
            description = "The room ID to search in.",
            required = true
        ) @PathParam("roomId") String roomId,
        @Parameter(
            name = "limit",
            description = "The maximum number of events to retrieve.",
            schema = @Schema(
                defaultValue = "10"
            )
        ) @QueryParam("limit") Integer limit,
        @Parameter(
            name = "min_depth",
            description = "The minimum depth of events to retrieve.",
            schema = @Schema(
                defaultValue = "0"
            )
        ) @QueryParam("min_depth") Integer minDepth,
        @Parameter(
            name = "earliest_events",
            description = "The latest event IDs that the sender already has. These are skipped when retrieving"
                + " the previous events of latest_events.",
            required = true
        ) @QueryParam("earliest_events") List<String> earliestEvents,
        @Parameter(
            name = "latest_events",
            description = "The event IDs to retrieve the previous events for.",
            required = true
        ) @QueryParam("latest_events") List<String> latestEvents,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse
    );

    /**
     * Retrieves a snapshot of a room's state at a given event.
     * <br>
     * Return: {@link StateResponse}.
     * <b>Requires auth</b>: Yes.
     * <p>Status code 200: The fully resolved state for the room, prior to considering any state changes induced by the requested event.
     * Includes the authorization chain for the events.</p>
     *
     * @param roomId        Required. The room ID to get state for.
     * @param eventId       Required. An event ID in the room to retrieve the state at.
     * @param uriInfo       Request Information.
     * @param httpHeaders   Http headers.
     * @param asyncResponse Asynchronous response.
     */
    @Operation(
        summary = "Retrieves a snapshot of a room's state at a given event.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The fully resolved state for the room, prior to considering any state changes induced by"
                    + " the requested event. Includes the authorization chain for the events.",
                content = @Content(
                    schema = @Schema(
                        implementation = StateResponse.class
                    )
                )
            )
        }
    )
    @GET
    @Path("/state/{roomId}")
    void state(
        @Parameter(
            name = "roomId",
            description = "The room ID to get state for.",
            required = true
        ) @PathParam("roomId") String roomId,
        @Parameter(
            name = "event_id",
            description = "An event ID in the room to retrieve the state at.",
            required = true
        ) @QueryParam("event_id") String eventId,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse
    );

    /**
     * Retrieves a snapshot of a room's state at a given event, in the form of event IDs.
     * This performs the same function as calling /state/{roomId}, however this returns just the event IDs rather than the full events.
     * <br>
     * Return: {@link StateIdResponse}.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <p>Status code 200: The fully resolved state for the room, prior to considering any state changes induced by the requested event.
     * Includes the authorization chain for the events.</p>
     *
     * @param roomId        Required. The room ID to get state for.
     * @param eventId       Required. An event ID in the room to retrieve the state at.
     * @param uriInfo       Request Information.
     * @param httpHeaders   Http headers.
     * @param asyncResponse Asynchronous response.
     */
    @Operation(
        summary = "Retrieves a snapshot of a room's state at a given event, in the form of event IDs.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The fully resolved state for the room, prior to considering any state changes induced by"
                    + " the requested event. Includes the authorization chain for the events.",
                content = @Content(
                    schema = @Schema(
                        implementation = StateIdResponse.class
                    )
                )
            )
        }
    )
    @GET
    @Path("/state_ids/{roomId}")
    void stateIds(
        @Parameter(
            name = "roomId",
            description = "The room ID to get state for.",
            required = true
        ) @PathParam("roomId") String roomId,
        @Parameter(
            name = "event_id",
            description = "An event ID in the room to retrieve the state at.",
            required = true
        ) @QueryParam("event_id") String eventId,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse
    );

    /**
     * Retrieves a single event.
     * <br>
     * Return: {@link PersistedDataUnit}.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <p>Status code 200: A transaction containing a single PDU which is the event requested.</p>
     *
     * @param eventId       Required. The event ID to get.
     * @param uriInfo       Request Information.
     * @param httpHeaders   Http headers.
     * @param asyncResponse Asynchronous response.
     */
    @Operation(
        summary = "Retrieves a single event.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "A transaction containing a single PDU which is the event requested.",
                content = @Content(
                    schema = @Schema(
                        implementation = PersistedDataUnit.class
                    )
                )
            )
        }
    )
    @GET
    @Path("/event/{eventId}")
    void event(
        @Parameter(
            name = "eventId",
            description = "The event ID to get.",
            required = true
        ) @PathParam("eventId") String eventId,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse
    );

    /**
     * Asks the receiving server to return information that the sending server will need to prepare a join event to get into the room.
     * <br>
     * <b>Requires auth</b>: Yes.
     * Return: {@link MakeResponse}.
     * <p>Status code 200: A template to be used for the rest of the Joining Rooms handshake. Note that events have a different format
     * depending on the room version - check the room version specification for precise event formats. The response body here describes
     * the common event fields in more detail and may be missing other required fields for a PDU.</p>
     * <p>Status code 400: The request is invalid or the room the server is attempting to join has a version that is not listed
     * in the ver parameters. The error should be passed through to clients so that they may give better feedback to users.</p>
     *
     * @param roomId        Required. The room ID that is about to be joined.
     * @param userId        Required. The user ID the join event will be for.
     * @param ver           The room versions the sending server has support for. Defaults to [1].
     * @param uriInfo       Request Information.
     * @param httpHeaders   Http headers.
     * @param asyncResponse Asynchronous response.
     */
    @Operation(
        summary = "Asks the receiving server to return information that the sending server will need to prepare a join event to"
            + " get into the room.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "A template to be used for the rest of the Joining Rooms handshake. Note that events have a different format"
                    + " depending on the room version - check the room version specification for precise event formats. The response body"
                    + " here describes the common event fields in more detail and may be missing other required fields for a PDU.",
                content = @Content(
                    schema = @Schema(
                        implementation = MakeResponse.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "400",
                description = "The request is invalid or the room the server is attempting to join has a version that is not listed in"
                    + " the ver parameters. The error should be passed through to clients so that they may give better feedback to users.",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponse.class
                    )
                )
            )
        }
    )
    @GET
    @Path("/make_join/{roomId}/{userId}")
    void makeJoin(
        @Parameter(
            name = "roomId",
            description = "The room ID that is about to be joined.",
            required = true
        ) @PathParam("roomId") Id roomId,
        @Parameter(
            name = "userId",
            description = "The user ID the join event will be for.",
            required = true
        ) @PathParam("userId") Id userId,
        @Parameter(
            name = "ver",
            description = "The room versions the sending server has support for.",
            schema = @Schema(
                defaultValue = "1"
            )
        ) @QueryParam("ver") List<String> ver,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse
    );

    /**
     * Submits a signed join event to the resident server for it to accept it into the room's graph. Note that events have a different
     * format depending on the room version - check the room version specification for precise event formats. The request and response
     * body here describes the common event fields in more detail and may be missing other required fields for a PDU.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <br>
     * Return: list with status and {@link io.github.ma1uta.matrix.server.model.federation.RoomStateResponse}.
     * <p>Status code 200: The full state for the room, having accepted the join event.</p>
     *
     * @param roomId        Required. The room ID that is about to be joined.
     * @param eventId       Required. The event ID for the join event.
     * @param request       Requires. JSON body request.
     * @param uriInfo       Request Information.
     * @param httpHeaders   Http headers.
     * @param asyncResponse Asynchronous response.
     */
    @Operation(
        summary = "Submits a signed join event to the resident server for it to accept it into the room's graph.",
        description = "Note that events have a different format depending on the room version - check the room version specification"
            + " for precise event formats. The request and response body here describes the common event fields in more detail and"
            + " may be missing other required fields for a PDU.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The full state for the room, having accepted the join event.",
                content = @Content(
                    array = @ArraySchema(
                        schema = @Schema(
                            anyOf = {
                                Integer.class,
                                RoomStateResponse.class
                            }
                        )
                    )
                )
            ),
            @ApiResponse(
                responseCode = "400",
                description = "The request is invalid or the room the server is attempting to join has a version that is not listed in"
                    + " the ver parameters. The error should be passed through to clients so that they may give better feedback to users.",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponse.class
                    )
                )
            )
        }
    )
    @PUT
    @Path("/send_join/{roomId}/{eventId}")
    void sendJoin(
        @Parameter(
            name = "roomId",
            description = "The room ID that is about to be joined.",
            required = true
        ) @PathParam("roomId") String roomId,
        @Parameter(
            name = "eventId",
            description = "The event ID for the join event.",
            required = true
        ) @PathParam("eventId") String eventId,
        @RequestBody SendRequest request,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse
    );

    /**
     * Invites a remote user to a room. Once the event has been signed by both the inviting homeserver and the invited homeserver,
     * it can be sent to all of the servers in the room by the inviting homeserver.
     * <br>
     * Servers should prefer to use the v2 API for invites instead of the v1 API. Servers which receive a v1 invite request must assume
     * that the room version is either "1" or "2".
     * <br>
     * Note that events have a different format depending on the room version - check the room version specification for precise
     * event formats. The request and response bodies here describe the common event fields in more detail and may be missing other
     * required fields for a PDU.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <br>
     * Return: List of the {@link Integer} and {@link EventContainer}.
     * <p>Status code 200: The event with the invited server's signature added. All other fields of the events should remain untouched.
     * Note that events have a different format depending on the room version - check the room version specification for precise event
     * formats.</p>
     * <p>Status code 403: The invite is not allowed. This could be for a number of reasons, including:</p>
     * <ul>
     * <li>The sender is not allowed to send invites to the target user/homeserver.</li>
     * <li>The homeserver does not permit anyone to invite its users.</li>
     * <li>The homeserver refuses to participate in the room.</li>
     * </ul>
     *
     * @param roomId        Required. The room ID that the user is being invited to.
     * @param eventId       Required. The event ID for the invite event, generated by the inviting server.
     * @param request       Invite JSON request.
     * @param uriInfo       Request Information.
     * @param httpHeaders   Http headers.
     * @param asyncResponse Asynchronous response.
     */
    @Operation(
        summary = "Invites a remote user to a room.",
        description = "Once the event has been signed by both the inviting homeserver and the invited homeserver, it can be sent to all"
            + " of the servers in the room by the inviting homeserver. Servers should prefer to use the v2 API for invites instead"
            + " of the v1 API. Servers which receive a v1 invite request must assume that the room version is either \"1\" or \"2\"."
            + " Note that events have a different format depending on the room version-check the room version specification for precise"
            + " event formats.The request and response bodies here describe the common event fields in more detail and may be missing other"
            + " required fields for a PDU.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The event with the invited server's signature added. All other fields of the events should remain untouched."
                    + " Note that events have a different format depending on the room version - check the room version specification"
                    + " for precise event formats.",
                content = @Content(
                    array = @ArraySchema(
                        schema = @Schema(
                            anyOf = {
                                Integer.class,
                                EventContainer.class
                            }
                        )
                    )
                )
            ),
            @ApiResponse(
                responseCode = "403",
                description = "The invite is not allowed.",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponse.class
                    )
                )
            )
        }
    )
    @PUT
    @Path("/invite/{roomId}/{eventId}")
    void invite(
        @Parameter(
            name = "roomId",
            description = "The room ID that the user is being invited to.",
            required = true
        ) @PathParam("roomId") String roomId,
        @Parameter(
            name = "eventId",
            description = "The event ID for the invite event, generated by the inviting server.",
            required = true
        ) @PathParam("eventId") String eventId,
        @RequestBody InviteV1Request request,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse
    );

    /**
     * Asks the receiving server to return information that the sending server will need to prepare a leave event to get out of the room.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <br>
     * Return: List of the {@link Integer} and {@link MakeResponse}.
     * <p>Status code 200: A template to be used to call /send_leave. Note that events have a different format depending on
     * the room version - check the room version specification for precise event formats. The response body here describes the common event
     * fields in more detail and may be missing other required fields for a PDU.</p>
     * <p>Status code 403: The request is not authorized. This could mean that the user is not in the room.</p>
     *
     * @param roomId        Required. The room ID that is about to be left.
     * @param userId        Required. The user ID the leave event will be for.
     * @param uriInfo       Request Information.
     * @param httpHeaders   Http headers.
     * @param asyncResponse Asynchronous response.
     */
    @Operation(
        summary = "Asks the receiving server to return information that the sending server will need to prepare a leave event"
            + " to get out of the room.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "A template to be used to call /send_leave. Note that events have a different format depending on"
                    + " the room version - check the room version specification for precise event formats. The response body here describes"
                    + " the common event fields in more detail and may be missing other required fields for a PDU.",
                content = @Content(
                    array = @ArraySchema(
                        schema = @Schema(
                            anyOf = {
                                Integer.class,
                                MakeResponse.class
                            }
                        )
                    )
                )
            ),
            @ApiResponse(
                responseCode = "403",
                description = "The request is not authorized. This could mean that the user is not in the room.",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponse.class
                    )
                )
            )
        }
    )
    @GET
    @Path("/make_leave/{roomId}/{userId}")
    void makeLeave(
        @Parameter(
            name = "roomId",
            description = "The room ID that is about to be left.",
            required = true
        ) @PathParam("roomId") String roomId,
        @Parameter(
            name = "userId",
            description = "The user ID the leave event will be for.",
            required = true
        ) @PathParam("userId") String userId,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse
    );

    /**
     * Submits a signed leave event to the resident server for it to accept it into the room's graph. Note that events have a different
     * format depending on the room version - check the room version specification for precise event formats. The request and response
     * body here describes the common event fields in more detail and may be missing other required fields for a PDU.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <br>
     * Return: List of {@link Integer} and {@link EmptyResponse}.
     * <p>Status code 200: An empty response to indicate the event was accepted into the graph by the receiving homeserver.</p>
     *
     * @param roomId        Required. The room ID that is about to be left.
     * @param eventId       Required. The event ID for the leave event.
     * @param request       JSON body request.
     * @param uriInfo       Request Information.
     * @param httpHeaders   Http headers.
     * @param asyncResponse Asynchronous response.
     */
    @Operation(
        summary = "Submits a signed leave event to the resident server for it to accept it into the room's graph.",
        description = "Note that events have a different format depending on the room version - check the room version specification"
            + " for precise event formats. The request and response body here describes the common event fields in more detail and may be"
            + " missing other required fields for a PDU.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "An empty response to indicate the event was accepted into the graph by the receiving homeserver.",
                content = @Content(
                    array = @ArraySchema(
                        schema = @Schema(
                            anyOf = {
                                Integer.class,
                                EmptyResponse.class
                            }
                        )
                    )
                )
            )
        }
    )
    @PUT
    @Path("/send_leave/{roomId}/{eventId}")
    void sendLeave(
        @Parameter(
            name = "roomId",
            description = "The room ID that is about to be left.",
            required = true
        ) @PathParam("roomId") String roomId,
        @Parameter(
            name = "eventId",
            description = "The event ID for the leave event.",
            required = true
        ) @PathParam("eventId") String eventId,
        @RequestBody SendRequest request,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse
    );

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
