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

import io.github.ma1uta.matrix.EmptyResponse;
import io.github.ma1uta.matrix.ErrorResponse;
import io.github.ma1uta.matrix.server.model.federation.DeviceResponse;
import io.github.ma1uta.matrix.server.model.federation.DirectoryResponse;
import io.github.ma1uta.matrix.server.model.federation.EventContainer;
import io.github.ma1uta.matrix.server.model.federation.InviteV1Request;
import io.github.ma1uta.matrix.server.model.federation.KeyClaimRequest;
import io.github.ma1uta.matrix.server.model.federation.KeyClaimResponse;
import io.github.ma1uta.matrix.server.model.federation.KeyQueryRequest;
import io.github.ma1uta.matrix.server.model.federation.KeyQueryResponse;
import io.github.ma1uta.matrix.server.model.federation.MakeResponse;
import io.github.ma1uta.matrix.server.model.federation.OnBindRequest;
import io.github.ma1uta.matrix.server.model.federation.PersistedDataUnit;
import io.github.ma1uta.matrix.server.model.federation.ProfileResponse;
import io.github.ma1uta.matrix.server.model.federation.PublicRoomsResponse;
import io.github.ma1uta.matrix.server.model.federation.QueryAuth;
import io.github.ma1uta.matrix.server.model.federation.RoomStateResponse;
import io.github.ma1uta.matrix.server.model.federation.SendRequest;
import io.github.ma1uta.matrix.server.model.federation.StateIdResponse;
import io.github.ma1uta.matrix.server.model.federation.StateResponse;
import io.github.ma1uta.matrix.server.model.federation.ThirdPartyInvite;
import io.github.ma1uta.matrix.server.model.federation.Transaction;
import io.github.ma1uta.matrix.server.model.federation.UserInfoResponse;
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
        ) @PathParam("roomId") String roomId,
        @Parameter(
            name = "userId",
            description = "The user ID the join event will be for.",
            required = true
        ) @PathParam("userId") String userId,
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
     * The receiving server will verify the partial m.room.member event given in the request body. If valid, the receiving server will
     * issue an invite as per the Inviting to a room section before returning a response to this request.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <br>
     * Return: {@link EmptyResponse}.
     * <p>Status code 200: The invite has been issued successfully.</p>
     *
     * @param roomId        Required. The room ID to exchange a third party invite in.
     * @param request       JSON body request.
     * @param uriInfo       Request Information.
     * @param httpHeaders   Http headers.
     * @param asyncResponse Asynchronous response.
     */
    @Operation(
        summary = "The receiving server will verify the partial m.room.member event given in the request body.",
        description = "If valid, the receiving server will issue an invite as per the Inviting to a room section before returning"
            + " a response to this request.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The invite has been issued successfully.",
                content = @Content(
                    schema = @Schema(
                        implementation = EmptyResponse.class
                    )
                )
            )
        }
    )
    @PUT
    @Path("/exchange_third_party_invite/{roomId}")
    void exchangeThirdPartyInvite(
        @Parameter(
            name = "roomId",
            description = "The room ID to exchange a third party invite in.",
            required = true
        ) @PathParam("roomId") String roomId,
        @RequestBody ThirdPartyInvite request,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse
    );

    /**
     * Used by identity servers to notify the homeserver that one of its users has bound a third party identifier successfully,
     * including any pending room invites the identity server has been made aware of.
     * Return: {@link EmptyResponse}.
     * <p>Status code 200: The homeserver has processed the notification.</p>
     *
     * @param onBindRequest Request.
     * @param uriInfo       Request Information.
     * @param httpHeaders   Http headers.
     * @param asyncResponse Asynchronous response.
     */
    @Operation(
        summary = "Used by identity servers to notify the homeserver that one of its users has bound a third party identifier successfully,"
            + " including any pending room invites the identity server has been made aware of.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The homeserver has processed the notification.",
                content = @Content(
                    schema = @Schema(
                        implementation = EmptyResponse.class
                    )
                )
            )
        }
    )
    @Path("/3pid/onbind")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    void onBind(
        @RequestBody OnBindRequest onBindRequest,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse
    );

    /**
     * Gets all the public rooms for the homeserver. This should not return rooms that are listed on another homeserver's directory,
     * just those listed on the receiving homeserver's directory.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <br>
     * Return: {@link PublicRoomsResponse}.
     * <p>Status code 200: The public room list for the homeserver.</p>
     *
     * @param limit                The maximum number of rooms to return. Defaults to 0 (no limit).
     * @param since                A pagination token from a previous call to this endpoint to fetch more rooms.
     * @param includeAllNetworks   Whether or not to include all networks/protocols defined by application services on the homeserver.
     *                             Defaults to false.
     * @param thirdPartyInstanceId The specific third party network/protocol to request from the homeserver.
     *                             Can only be used if include_all_networks is false.
     * @param uriInfo              Request Information.
     * @param httpHeaders          Http headers.
     * @param asyncResponse        Asynchronous response.
     */
    @Operation(
        summary = "Gets all the public rooms for the homeserver.",
        description = "This should not return rooms that are listed on another homeserver's directory, just those listed on"
            + " the receiving homeserver's directory.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The public room list for the homeserver.",
                content = @Content(
                    schema = @Schema(
                        implementation = PublicRoomsResponse.class
                    )
                )
            )
        }
    )
    @GET
    @Path("/publicRooms")
    void publicRooms(
        @Parameter(
            name = "limit",
            description = "The maximum number of rooms to return.",
            schema = @Schema(
                defaultValue = "0"
            )
        ) @QueryParam("limit") Integer limit,
        @Parameter(
            name = "since",
            description = "A pagination token from a previous call to this endpoint to fetch more rooms."
        ) @QueryParam("since") String since,
        @Parameter(
            name = "include_all_networks",
            description = "Whether or not to include all networks/protocols defined by application services on the homeserver",
            schema = @Schema(
                defaultValue = "false"
            )
        ) @QueryParam("include_all_networks") Boolean includeAllNetworks,
        @Parameter(
            name = "third_party_instance_id",
            description = "The specific third party network/protocol to request from the homeserver. Can only be used if"
                + " include_all_networks is false."
        ) @QueryParam("third_party_instance_id") String thirdPartyInstanceId,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse
    );

    /**
     * Performs a query to get the mapped room ID and list of resident homeservers in the room for a given room alias.
     * Homeservers should only query room aliases that belong to the target server (identified by the DNS Name in the alias).
     * <br>
     * Servers may wish to cache the response to this query to avoid requesting the information too often.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <br>
     * Return: {@link DirectoryResponse}.
     * <p>Status code 200: The corresponding room ID and list of known resident homeservers for the room.</p>
     * <p>Status code 404: The room alias was not found.</p>
     *
     * @param roomAlias     Required. The room alias to query.
     * @param uriInfo       Request Information.
     * @param httpHeaders   Http headers.
     * @param asyncResponse Asynchronous response.
     */
    @Operation(
        summary = "Performs a query to get the mapped room ID and list of resident homeservers in the room for a given room alias.",
        description = "Servers may wish to cache the response to this query to avoid requesting the information too often.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The corresponding room ID and list of known resident homeservers for the room.",
                content = @Content(
                    schema = @Schema(
                        implementation = DirectoryResponse.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "404",
                description = "The room alias was not found.",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponse.class
                    )
                )
            )
        }
    )
    @GET
    @Path("/query/directory")
    void queryDirectory(
        @Parameter(
            name = "room_alias",
            description = "The room alias to query.",
            required = true
        ) @QueryParam("room_alias") String roomAlias,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse
    );

    /**
     * The field to query.
     */
    class QueryProfileField {

        protected QueryProfileField() {
            //singleton
        }

        /**
         * Display name.
         */
        public static final String DISPLAYNAME = "displayname";

        /**
         * Avatar url.
         */
        public static final String AVATAR_URL = "avatar_url";
    }

    /**
     * Performs a query to get profile information, such as a display name or avatar, for a given user. Homeservers should only query
     * profiles for users that belong to the target server (identified by the DNS Name in the user ID).
     * <br>
     * Servers may wish to cache the response to this query to avoid requesting the information too often.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <br>
     * Return: {@link ProfileResponse}.
     * <p>Status code 200: The profile for the user. If a field is specified in the request, only the matching field should be included
     * in the response. If no field was specified, the response should include the fields of the user's profile that can be made public,
     * such as the display name and avatar. If the user does not have a particular field set on their profile, the server should exclude
     * it from the response body or give it the value null.</p>
     * <p>Status code 404: The user does not exist or does not have a profile.</p>
     *
     * @param userId        Required. The user ID to query.
     * @param field         The field to query. If specified, the server will only return the given field in the response.
     *                      If not specified, the server will return the full profile for the user. One of: ["displayname", "avatar_url"]
     * @param uriInfo       Request Information.
     * @param httpHeaders   Http headers.
     * @param asyncResponse Asynchronous response.
     */
    @Operation(
        summary = "Performs a query to get profile information, such as a display name or avatar, for a given user.",
        description = " Homeservers should only query profiles for users that belong to the target server (identified by the DNS Name"
            + " in the user ID). Servers may wish to cache the response to this query to avoid requesting the information too often.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The profile for the user. If a field is specified in the request, only the matching field should be included"
                    + " in the response. If no field was specified, the response should include the fields of the user's profile that"
                    + " can be made public, such as the display name and avatar.If the user does not have a particular field set"
                    + " on their profile, the server should exclude it from the response body or give it the value null.",
                content = @Content(
                    schema = @Schema(
                        implementation = ProfileResponse.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "404",
                description = "The room alias was not found.",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponse.class
                    )
                )
            )
        }
    )
    @GET
    @Path("/query/profile")
    void queryProfile(
        @Parameter(
            name = "user_id",
            description = "The user ID to query.",
            required = true
        ) @QueryParam("user_id") String userId,
        @QueryParam("field") String field,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse
    );

    /**
     * Exchanges an OpenID access token for information about the user who generated the token. Currently this only exposes
     * the Matrix User ID of the owner.
     * <br>
     * Return: {@link UserInfoResponse}.
     * <p>Status code 200: Information about the user who generated the OpenID access token.</p>
     * <p>Status code 401: The token was not recognized or has expired.</p>
     *
     * @param accessToken   Required. The OpenID access token to get information about the owner for.
     * @param uriInfo       Request Information.
     * @param httpHeaders   http headers.
     * @param asyncResponse Asynchronous response.
     */
    @Operation(
        summary = "Exchanges an OpenID access token for information about the user who generated the token.",
        description = "Currently this only exposes the Matrix User ID of the owner.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Information about the user who generated the OpenID access token.",
                content = @Content(
                    schema = @Schema(
                        implementation = UserInfoResponse.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "401",
                description = "The token was not recognized or has expired.",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponse.class
                    )
                )
            )
        }
    )
    @GET
    @Path("/openid/userinfo")
    void userInfo(
        @QueryParam("access_token") String accessToken,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse
    );

    /**
     * Gets information on all of the user's devices.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <br>
     * Return: {@link DeviceResponse}.
     * <p>Status code 200: The user's devices.</p>
     *
     * @param userId        Required. The user ID to retrieve devices for. Must be a user local to the receiving homeserver.
     * @param uriInfo       Request Information.
     * @param httpHeaders   Http headers.
     * @param asyncResponse Asynchronous response.
     */
    @Operation(
        summary = "Gets information on all of the user's devices.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The user's devices",
                content = @Content(
                    schema = @Schema(
                        implementation = DeviceResponse.class
                    )
                )
            )
        }
    )
    @GET
    @Path("/user/devices/{userId}")
    void userDevices(
        @Parameter(
            name = "userId",
            description = "The user ID to retrieve devices for. Must be a user local to the receiving homeserver.",
            required = true
        ) @PathParam("userId") String userId,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse
    );

    /**
     * Claims one-time keys for use in pre-key messages.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <br>
     * Return: {@link Map} of user ID to {@link Map} from devices to a {@link Map} from &lt;algorithm&gt;:&lt;key_id&gt; to key object.
     * <p>Status code 200: The claimed keys.</p>
     *
     * @param request       Required. The keys to be claimed. A map from user ID, to a map from device ID to algorithm name.
     * @param uriInfo       Request Information.
     * @param httpHeaders   Http headers.
     * @param asyncResponse Asynchronous response.
     */
    @Operation(
        summary = "Claims one-time keys for use in pre-key messages.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The claimed keys",
                content = @Content(
                    schema = @Schema(
                        implementation = KeyClaimResponse.class
                    )
                )
            )
        }
    )
    @POST
    @Path("/user/keys/claim")
    void userKeysClaim(
        @RequestBody KeyClaimRequest request,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse
    );

    /**
     * Returns the current devices and identity keys for the given users.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <br>
     * Return: {@link KeyQueryResponse}.
     * <p>Status code 200: The device information.</p>
     *
     * @param request       JSON request.
     * @param uriInfo       Request Information.
     * @param httpHeaders   Http headers.
     * @param asyncResponse Asynchronous response.
     */
    @Operation(
        summary = "Returns the current devices and identity keys for the given users.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The device information",
                content = @Content(
                    schema = @Schema(
                        implementation = KeyQueryResponse.class
                    )
                )
            )
        }
    )
    @POST
    @Path("/user/keys/query")
    void userKeysQuery(
        @RequestBody KeyQueryRequest request,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse
    );
}
