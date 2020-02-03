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

package io.github.ma1uta.matrix.client.api;

import io.github.ma1uta.matrix.ErrorResponse;
import io.github.ma1uta.matrix.Page;
import io.github.ma1uta.matrix.Secured;
import io.github.ma1uta.matrix.client.model.event.JoinedMembersResponse;
import io.github.ma1uta.matrix.client.model.event.MembersResponse;
import io.github.ma1uta.matrix.client.model.event.RedactRequest;
import io.github.ma1uta.matrix.client.model.event.SendEventResponse;
import io.github.ma1uta.matrix.event.Event;
import io.github.ma1uta.matrix.event.content.EventContent;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

/**
 * There are several APIs provided to GET events for a room.
 */
@Path("/_matrix/client/r0/rooms")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface EventApi {

    /**
     * Get a single event based on roomId/eventId. You must have permission to retrieve this event e.g. by being a member in the
     * room for this event.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <br>
     * Return: {@link Event}.
     * <p>Status code 200: The full event.</p>
     * <p>Status code 404: The event was not found or you do not have permission to read this event.</p>
     *
     * @param roomId          Required. The ID of the room the event is in.
     * @param eventId         Required. The event ID to get.
     * @param uriInfo         Request Information.
     * @param httpHeaders     Http headers.
     * @param asyncResponse   Asynchronous response.
     * @param securityContext Security context.
     */
    @Operation(
        summary = "Get a single event based on roomId/eventId. You must have permission to retrieve this event e.g. by "
            + "being a member in the room for this event.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The full event.",
                content = @Content(
                    schema = @Schema(
                        implementation = Event.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "404",
                description = "The event was not found or you do not have permission to read this event.",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponse.class
                    )
                )
            )
        },
        security = {
            @SecurityRequirement(
                name = "accessToken"
            )
        },
        tags = {
            "Room participation"
        }
    )
    @GET
    @Secured
    @Path("/{roomId}/event/{eventId}")
    void roomEvent(
        @Parameter(
            description = "The ID of the room the event is in.",
            required = true
        ) @PathParam("roomId") String roomId,
        @Parameter(
            description = "The event ID to get.",
            required = true
        ) @PathParam("eventId") String eventId,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );

    /**
     * Looks up the contents of a state event in a room. If the user is joined to the room then the state is taken from the current
     * state of the room. If the user has left the room then the state is taken from the state of the room when they left.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <br>
     * Return: {@link EventContent}.
     * <p>Status code 200: The content of the state event.</p>
     * <p>Status code 403: You aren't a member of the room and weren't previously a member of the room.</p>
     * <p>Status code 404: The room has no state with the given type or key.</p>
     *
     * @param roomId          Required. The room to look up the state in.
     * @param eventType       Required. The type of state to look up.
     * @param stateKey        Required. The key of the state to look up.
     * @param uriInfo         Request Information.
     * @param httpHeaders     Http headers.
     * @param asyncResponse   Asynchronous response.
     * @param securityContext Security context.
     */
    @Operation(
        summary = "Looks up the contents of a state event in a room. If the user is joined to the room then the state is "
            + "taken from the current state of the room. If the user has left the room then the state is taken from the state of the "
            + "room when they left.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The content of the state event.",
                content = @Content(
                    schema = @Schema(
                        implementation = EventContent.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "403",
                description = "You aren't a member of the room and weren't previously a member of the room.",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponse.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "404",
                description = "The room has no state with the given type or key.",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponse.class
                    )
                )
            )
        },
        security = {
            @SecurityRequirement(
                name = "accessToken"
            )
        },
        tags = {
            "Room participation"
        }
    )
    @GET
    @Secured
    @Path("/{roomId}/state/{eventType}/{stateKey}")
    void roomEventWithTypeAndState(
        @Parameter(
            description = "The room to look up the state in.",
            required = true
        ) @PathParam("roomId") String roomId,
        @Parameter(
            description = "The type of state to look up.",
            required = true
        ) @PathParam("eventType") String eventType,
        @Parameter(
            description = "The key of the state to look up.",
            required = true
        ) @PathParam("stateKey") String stateKey,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );

    /**
     * Get the state events for the current state of a room.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <br>
     * Return: {@link List} of the {@link Event}s.
     * <p>Status code 200: The current state of the room.</p>
     * <p>Status code 403: You aren't a member of the room and weren't previously a member of the room.</p>
     *
     * @param roomId          Required. The room to look up the state for.
     * @param uriInfo         Request Information.
     * @param httpHeaders     Http headers.
     * @param asyncResponse   Asynchronous response.
     * @param securityContext Security context.
     */
    @Operation(
        summary = "Get the state events for the current state of a room.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The current state of the room.",
                content = @Content(
                    array = @ArraySchema(
                        schema = @Schema(
                            implementation = Event.class
                        )
                    )
                )
            ),
            @ApiResponse(
                responseCode = "403",
                description = "You aren't a member of the room and weren't previously a member of the room.",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponse.class
                    )
                )
            )
        },
        security = {
            @SecurityRequirement(
                name = "accessToken"
            )
        },
        tags = {
            "Room participation"
        }
    )
    @GET
    @Secured
    @Path("/{roomId}/state")
    void roomState(
        @Parameter(
            description = "The room to look up the state for.",
            required = true
        ) @PathParam("roomId") String roomId,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );

    /**
     * Get the list of members for this room.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <br>
     * Return: {@link MembersResponse}.
     * <p>Status code 200: A list of members of the room. If you are joined to the room then this will be the current
     * members of the room. If you have left the room then this will be the members of the room when you left.</p>
     * <p>Status code 403: You aren't a member of the room and weren't previously a member of the room.</p>
     *
     * @param roomId          Required. The room to get the member events for.
     * @param uriInfo         Request Information.
     * @param httpHeaders     Http headers.
     * @param asyncResponse   Asynchronous response.
     * @param securityContext Security context.
     */
    @Operation(
        summary = "Get the list of members for this room.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "A list of members of the room. If you are joined to the room then this will be the current "
                    + "members of the room. If you have left the room then this will be the members of the room when you left.",
                content = @Content(
                    schema = @Schema(
                        implementation = MembersResponse.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "403",
                description = "You aren't a member of the room and weren't previously a member of the room.",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponse.class
                    )
                )
            )
        },
        security = {
            @SecurityRequirement(
                name = "accessToken"
            )
        },
        tags = {
            "Room participation"
        }
    )
    @GET
    @Secured
    @Path("/{roomId}/members")
    void members(
        @Parameter(
            description = "The room to get the member events for.",
            required = true
        ) @PathParam("roomId") String roomId,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );

    /**
     * This API returns a map of MXIDs to member info objects for members of the room. The current user must be in the room for
     * it to work, unless it is an Application Service in which case any of the AS's users must be in the room. This API
     * is primarily for Application Services and should be faster to respond than /members as it can be implemented more
     * efficiently on the server.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <br>
     * Return: {@link JoinedMembersResponse}.
     * <p>Status code 200: A map of MXID to room member objects.</p>
     * <p>Status code 403: You aren't a member of the room.</p>
     *
     * @param roomId          Required. The room to get the members of.
     * @param uriInfo         Request Information.
     * @param httpHeaders     Http headers.
     * @param asyncResponse   Asynchronous response.
     * @param securityContext Security context.
     */
    @Operation(
        summary = "This API returns a map of MXIDs to member info objects for members of the room.",
        description = "The current user must be in the room for it to work, unless it is an Application Service in which case any of "
            + "the AS's users must be in the room. This API is primarily for Application Services and should be faster to respond "
            + "than/members as it can be implemented more efficiently on the server.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "A map of MXID to room member objects.",
                content = @Content(
                    schema = @Schema(
                        implementation = JoinedMembersResponse.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "403",
                description = "You aren't a member of the room.",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponse.class
                    )
                )
            )
        },
        security = {
            @SecurityRequirement(
                name = "accessToken"
            )
        },
        tags = {
            "Room participation"
        }
    )
    @GET
    @Secured
    @Path("/{roomId}/joined_members")
    void joinedMembers(
        @Parameter(
            description = "The room to get the members of.",
            required = true
        ) @PathParam("roomId") String roomId,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );

    /**
     * This API returns a list of message and state events for a room. It uses pagination query parameters to paginate history in the room.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <br>
     * Return: {@link Page} of the {@link Event}s.
     * <p>Status code 200: A list of messages with a new token to request more.</p>
     * <p>Status code 403: You aren't a member of the room.</p>
     *
     * @param roomId          Required. The room to get events from.
     * @param from            Required. The token to start returning events from. This token can be obtained from a prev_batch token
     *                        returned for each room by the sync API, or from a start or end token returned by a previous request to
     *                        this endpoint.
     * @param to              The token to stop returning events at. This token can be obtained from a prev_batch token returned for
     *                        each room by the sync endpoint, or from a start or end token returned by a previous request to this endpoint.
     * @param dir             Required. The direction to return events from. One of: ["b", "f"]
     * @param limit           The maximum number of events to return. Default: 10.
     * @param filter          A JSON RoomEventFilter to filter returned events with.
     * @param uriInfo         Request Information.
     * @param httpHeaders     Http headers.
     * @param asyncResponse   Asynchronous response.
     * @param securityContext Security context.
     */
    @Operation(
        summary = "This API returns a list of message and state events for a room.",
        description = "It uses pagination query parameters to paginate history in the room.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "A list of messages with a new token to request more.",
                content = @Content(
                    schema = @Schema(
                        implementation = Page.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "403",
                description = "You aren't a member of the room.",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponse.class
                    )
                )
            )
        },
        security = {
            @SecurityRequirement(
                name = "accessToken"
            )
        },
        tags = {
            "Room participation"
        }
    )
    @GET
    @Secured
    @Path("/{roomId}/messages")
    void messages(
        @Parameter(
            description = "The room to get events from.",
            required = true
        ) @PathParam("roomId") String roomId,
        @Parameter(
            description = "The token to start returning events from. This token can be obtained from a prev_batch token "
                + "returned for each room by the sync API, or from a start or end token returned by a previous request to "
                + "this endpoint.",
            required = true
        ) @QueryParam("from") String from,
        @Parameter(
            description = "The token to stop returning events at. This token can be obtained from a prev_batch token returned for "
                + "each room by the sync endpoint, or from a start or end token returned by a previous "
                + "request to this endpoint."
        ) @QueryParam("to") String to,
        @Parameter(
            description = "The direction to return events from.",
            required = true,
            schema = @Schema(
                allowableValues = {
                    "b",
                    "f"
                }
            )
        ) @QueryParam("dir") String dir,
        @Parameter(
            description = "The maximum number of events to return.",
            schema = @Schema(
                defaultValue = "10"
            )
        ) @QueryParam("limit") Integer limit,
        @Parameter(
            description = "A JSON RoomEventFilter to filter returned events with."
        ) @QueryParam("filter") String filter,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );

    /**
     * State events can be sent using this endpoint. These events will be overwritten if (room id), (event type) and (state key) all match.
     * <br>
     * Requests to this endpoint cannot use transaction IDs like other PUT paths because they cannot be differentiated from the state_key.
     * Furthermore, POST is unsupported on state paths.
     * <br>
     * The body of the request should be the content object of the event; the fields in this object will vary depending on the
     * type of event. See Room Events for the m. event specification.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <br>
     * Return: {@link SendEventResponse}.
     * <p>Status code 200: An ID for the sent event.</p>
     * <p>Status code 403: The sender doesn't have permission to send the event into the room.</p>
     *
     * @param roomId          Required. The room to set the state in.
     * @param eventType       Required. The type of event to send.
     * @param stateKey        Required. The state_key for the state to send. Defaults to the empty string.
     * @param event           event.
     * @param uriInfo         Request Information.
     * @param httpHeaders     Http headers.
     * @param asyncResponse   Asynchronous response.
     * @param securityContext Security context.
     */
    @Operation(
        summary = "State events can be sent using this endpoint.",
        description = "These events will be overwritten if (room id), (event type) and (state key) all match. Requests to this "
            + "endpoint cannot use transaction IDs like other PUT paths because they cannot be differentiated from the state_key. "
            + "Furthermore, POST is unsupported on state paths. The body of the request should be the content object of the event; "
            + "the fields in this object will vary depending on the type of event.See Room Events for the m.event specification.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "An ID for the sent event.",
                content = @Content(
                    schema = @Schema(
                        implementation = SendEventResponse.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "403",
                description = "The sender doesn't have permission to send the event into the room.",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponse.class
                    )
                )
            )
        },
        security = {
            @SecurityRequirement(
                name = "accessToken"
            )
        },
        tags = {
            "Room participation"
        }
    )
    @PUT
    @Secured
    @Path("/{roomId}/state/{eventType}/{stateKey}")
    void sendEventWithTypeAndState(
        @Parameter(
            description = "The room to set the state in.",
            required = true
        ) @PathParam("roomId") String roomId,
        @Parameter(
            description = "The type of event to send.",
            required = true
        ) @PathParam("eventType") String eventType,
        @Parameter(
            description = "The state_key for the state to send. Defaults to the empty string.",
            required = true
        ) @PathParam("stateKey") String stateKey,
        @RequestBody(
            description = "event"
        ) EventContent event,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );

    /**
     * State events can be sent using this endpoint. This endpoint is equivalent to calling /rooms/{roomId}/state/{eventType}/{stateKey}
     * with an empty stateKey. Previous state events with matching (roomId) and (eventType), and empty (stateKey), will be overwritten.
     * <br>
     * Requests to this endpoint cannot use transaction IDs like other PUT paths because they cannot be differentiated from the state_key.
     * Furthermore, POST is unsupported on state paths.
     * <br>
     * The body of the request should be the content object of the event; the fields in this object will vary depending on the type
     * of event. See Room Events for the m. event specification.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <br>
     * Return: {@link SendEventResponse}.
     * <p>Status code 200: An ID for the sent event.</p>
     * <p>Status code 403: The sender doesn't have permission to send the event into the room.</p>
     *
     * @param roomId          Required. The room to set the state in.
     * @param eventType       Required. The type of event to send.
     * @param event           event.
     * @param uriInfo         Request Information.
     * @param httpHeaders     Http headers.
     * @param asyncResponse   Asynchronous response.
     * @param securityContext Security context.
     */
    @Operation(
        summary = "State events can be sent using this endpoint.",
        description = "This endpoint is equivalent to calling /rooms/{roomId}/state/{eventType}/{stateKey} with an empty stateKey. "
            + "Previous state events with matching (roomId) and (eventType), and empty (stateKey), will be overwritten. "
            + "Requests to this endpoint cannot use transaction IDs like other PUT paths because they cannot be differentiated "
            + "from the state_key. Furthermore, POST is unsupported on state paths. The body of the request should be the content "
            + "object of the event; the fields in this object will vary depending on the type of event. See Room Events for the "
            + "m.event specification.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "An ID for the sent event.",
                content = @Content(
                    schema = @Schema(
                        implementation = SendEventResponse.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "403",
                description = "The sender doesn't have permission to send the event into the room.",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponse.class
                    )
                )
            )
        },
        security = {
            @SecurityRequirement(
                name = "accessToken"
            )
        },
        tags = {
            "Room participation"
        }
    )
    @PUT
    @Secured
    @Path("/{roomId}/state/{eventType}")
    void sendEventWithType(
        @Parameter(
            description = "The room to set the state in.",
            required = true
        ) @PathParam("roomId") String roomId,
        @Parameter(
            description = "The type of event to send.",
            required = true
        ) @PathParam("eventType") String eventType,
        @RequestBody(
            description = "Event"
        ) EventContent event,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );

    /**
     * This endpoint is used to send a message event to a room. Message events allow access to historical events and pagination,
     * making them suited for "once-off" activity in a room.
     * <br>
     * The body of the request should be the content object of the event; the fields in this object will vary depending on the
     * type of event. See Room Events for the m. event specification.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <br>
     * Return: {@link SendEventResponse}.
     * <p>Status code 200: An ID for the sent event.</p>
     *
     * @param roomId          Required. The room to send the event to.
     * @param eventType       Required. The type of event to send.
     * @param txnId           Required. The transaction ID for this event. Clients should generate an ID unique across requests with the
     *                        same access token; it will be used by the server to ensure idempotency of requests.
     * @param event           event.
     * @param uriInfo         Request Information.
     * @param httpHeaders     Http headers.
     * @param asyncResponse   Asynchronous response.
     * @param securityContext Security context.
     */
    @Operation(
        summary = "This endpoint is used to send a message event to a room.",
        description = "Message events allow access to historical events and pagination, making them suited for \"once-off\" activity"
            + " in a room. The body of the request should be the content object of the event; the fields in this object will vary"
            + " depending on the type of event. See Room Events for the m. event specification.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "An ID for the sent event.",
                content = @Content(
                    schema = @Schema(
                        implementation = SendEventResponse.class
                    )
                )
            )
        },
        security = {
            @SecurityRequirement(
                name = "accessToken"
            )
        },
        tags = {
            "Room participation"
        }
    )
    @PUT
    @Secured
    @Path("/{roomId}/send/{eventType}/{txnId}")
    void sendEvent(
        @Parameter(
            description = "The room to send the event to.",
            required = true
        ) @PathParam("roomId") String roomId,
        @Parameter(
            description = "The type of event to send.",
            required = true
        ) @PathParam("eventType") String eventType,
        @Parameter(
            description = "The transaction ID for this event. Clients should generate an ID unique across requests with "
                + "the same access token; it will be used by the server to ensure idempotency of requests.",
            required = true
        ) @PathParam("txnId") String txnId,
        @RequestBody(
            description = "Event"
        ) EventContent event,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );

    /**
     * Strips all information out of an event which isn't critical to the integrity of the server-side representation of the room.
     * <br>
     * This cannot be undone.
     * <br>
     * Users may redact their own events, and any user with a power level greater than or equal to the redact power level of the
     * room may redact events there.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <br>
     * Return: {@link SendEventResponse}.
     * <p>Status code 200: An ID for the redaction event.</p>
     *
     * @param roomId          Required. The room from which to redact the event.
     * @param eventId         Required. The ID of the event to redact.
     * @param txnId           Required. The transaction ID for this event. Clients should generate a unique ID; it will be used by the
     *                        server to ensure idempotency of requests.
     * @param event           The reason for the event being redacted.
     * @param uriInfo         Request Information.
     * @param httpHeaders     Http headers.
     * @param asyncResponse   Asynchronous response.
     * @param securityContext Security context.
     */
    @Operation(
        summary = "Strips all information out of an event which isn't critical to the integrity of the server-side "
            + "representation of the room.",
        description = "This cannot be undone. Users may redact their own events, and any user with "
            + "a power level greater than or equal to the redact power level of the room may redact events there.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "An ID for the redaction event.",
                content = @Content(
                    schema = @Schema(
                        implementation = SendEventResponse.class
                    )
                )
            )
        },
        security = {
            @SecurityRequirement(
                name = "accessToken"
            )
        },
        tags = {
            "Room participation"
        }
    )
    @PUT
    @Secured
    @Path("/{roomId}/redact/{eventId}/{txnId}")
    void redact(
        @Parameter(
            description = "The room from which to redact the event.",
            required = true
        ) @PathParam("roomId") String roomId,
        @Parameter(
            description = "The ID of the event to redact.",
            required = true
        ) @PathParam("eventId") String eventId,
        @Parameter(
            description = "The transaction ID for this event. Clients should generate a unique ID; it will be used by the server "
                + "to ensure idempotency of requests.",
            required = true
        ) @PathParam("txnId") String txnId,
        @RequestBody(
            description = "The reason for the event being redacted."
        ) RedactRequest event,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );
}
