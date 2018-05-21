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

package io.github.ma1uta.matrix.client.api;

import io.github.ma1uta.matrix.Event;
import io.github.ma1uta.matrix.Page;
import io.github.ma1uta.matrix.Secured;
import io.github.ma1uta.matrix.client.model.event.JoinedMembersResponse;
import io.github.ma1uta.matrix.client.model.event.MembersResponse;
import io.github.ma1uta.matrix.client.model.event.RedactRequest;
import io.github.ma1uta.matrix.client.model.event.SendEventResponse;

import java.util.List;
import java.util.Map;
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
import javax.ws.rs.core.SecurityContext;

/**
 * There are several APIs provided to GET events for a room.
 * <p/>
 * <a href="https://matrix.org/docs/spec/client_server/r0.3.0.html#id172">Specification.</a>
 */
@Path("/_matrix/client/r0/rooms/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface EventApi {

    /**
     * Get a single event based on roomId/eventId. You must have permission to retrieve this event e.g. by being a member in the
     * room for this event.
     * <p/>
     * Requires auth: Yes.
     *
     * @param roomId          Required. The ID of the room the event is in.
     * @param eventId         Required. The event ID to get.
     * @param servletRequest  servlet request.
     * @param servletResponse servlet response.
     * @param securityContext security context.
     * @return Status code 200: The full event.
     *     Status code 404: The event was not found or you do not have permission to read this event.
     */
    @GET
    @Secured
    @Path("/{roomId}/event/{eventId}")
    Event singleEvent(@PathParam("roomId") String roomId, @PathParam("eventId") String eventId, @Context HttpServletRequest servletRequest,
                      @Context HttpServletResponse servletResponse, @Context SecurityContext securityContext);

    /**
     * Looks up the contents of a state event in a room. If the user is joined to the room then the state is taken from the current
     * state of the room. If the user has left the room then the state is taken from the state of the room when they left.
     * <p/>
     * Requires auth: Yes.
     *
     * @param roomId          Required. The room to look up the state in.
     * @param eventType       Required. The type of state to look up.
     * @param stateKey        Required. The key of the state to look up.
     * @param servletRequest  servlet request.
     * @param servletResponse servlet response.
     * @param securityContext security context.
     * @return Status code 200: The content of the state event.
     *     Status code 403: You aren't a member of the room and weren't previously a member of the room.
     *     Status code 404: The room has no state with the given type or key.
     */
    @GET
    @Secured
    @Path("/{roomId}/state/{eventType}/{stateKey}")
    Map<String, Object> eventsForRoomWithTypeAndState(@PathParam("roomId") String roomId, @PathParam("eventType") String eventType,
                                                      @PathParam("stateKey") String stateKey, @Context HttpServletRequest servletRequest,
                                                      @Context HttpServletResponse servletResponse,
                                                      @Context SecurityContext securityContext);

    /**
     * Looks up the contents of a state event in a room. If the user is joined to the room then the state is taken from the
     * current state of the room. If the user has left the room then the state is taken from the state of the room when they left.
     * <p/>
     * This looks up the state event with the empty state key.
     * <p/>
     * Requires auth: Yes.
     *
     * @param roomId          Required. The room to look up the state in.
     * @param eventType       Required. The type of state to look up.
     * @param servletRequest  servlet request.
     * @param servletResponse servlet response.
     * @param securityContext security context.
     * @return Status code 200: The content of the state event.
     *     Status code 403: You aren't a member of the room and weren't previously a member of the room.
     *     Status code 404: The room has no state with the given type or key.
     */
    @GET
    @Secured
    @Path("/{roomId}/state/{eventType}")
    Map<String, Object> eventsForRoomWithType(@PathParam("roomId") String roomId, @PathParam("eventType") String eventType,
                                              @Context HttpServletRequest servletRequest, @Context HttpServletResponse servletResponse,
                                              @Context SecurityContext securityContext);

    /**
     * Get the state events for the current state of a room.
     * <p/>
     * Requires auth: Yes.
     *
     * @param roomId          Required. The room to look up the state for.
     * @param servletRequest  servlet request.
     * @param servletResponse servlet response.
     * @param securityContext security context.
     * @return Status code 200: The current state of the room
     *     Status code 403: You aren't a member of the room and weren't previously a member of the room.
     */
    @GET
    @Secured
    @Path("/{roomId}/state")
    List<Event> eventsForRoom(@PathParam("roomId") String roomId, @Context HttpServletRequest servletRequest,
                              @Context HttpServletResponse servletResponse, @Context SecurityContext securityContext);

    /**
     * Get the list of members for this room.
     *
     * @param roomId          Required. The room to get the member events for.
     * @param servletRequest  servlet request.
     * @param servletResponse servlet response.
     * @return Status code 200: A list of members of the room. If you are joined to the room then this will be the current
     *     members of the room. If you have left the room then this will be the members of the room when you left.
     *     Status code 403: You aren't a member of the room and weren't previously a member of the room.
     */
    @GET
    @Path("/{roomId}/members")
    MembersResponse members(@PathParam("roomId") String roomId, @Context HttpServletRequest servletRequest,
                            @Context HttpServletResponse servletResponse);

    /**
     * This API returns a map of MXIDs to member info objects for members of the room. The current user must be in the room for
     * it to work, unless it is an Application Service in which case any of the AS's users must be in the room. This API
     * is primarily for Application Services and should be faster to respond than /members as it can be implemented more
     * efficiently on the server.
     * <p/>
     * Requires auth: Yes.
     *
     * @param roomId          Required. The room to get the members of.
     * @param servletRequest  servlet request.
     * @param servletResponse servlet response.
     * @param securityContext security context.
     * @return Status code 200: A map of MXID to room member objects.
     *     Status code 403: You aren't a member of the room.
     */
    @GET
    @Secured
    @Path("/{roomId}/joined_members")
    JoinedMembersResponse joinedMembers(@PathParam("roomId") String roomId, @Context HttpServletRequest servletRequest,
                                        @Context HttpServletResponse servletResponse, @Context SecurityContext securityContext);

    /**
     * This API returns a list of message and state events for a room. It uses pagination query parameters to paginate history in the room.
     * <p/>
     * Requires auth: Yes.
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
     * @param servletRequest  servlet request.
     * @param servletResponse servlet response.
     * @param securityContext security context.
     * @return Status code 200: A list of messages with a new token to request more.
     *     Status code 403: You aren't a member of the room.
     */
    @GET
    @Secured
    @Path("/{roomId}/messages")
    Page<Event> messages(@PathParam("roomId") String roomId, @QueryParam("from") String from, @QueryParam("to") String to,
                         @QueryParam("dir") String dir, @QueryParam("limit") Integer limit, @QueryParam("filter") String filter,
                         @Context HttpServletRequest servletRequest, @Context HttpServletResponse servletResponse,
                         @Context SecurityContext securityContext);

    /**
     * State events can be sent using this endpoint. These events will be overwritten if (room id), (event type) and (state key) all match.
     * <p/>
     * Requests to this endpoint cannot use transaction IDs like other PUT paths because they cannot be differentiated from the state_key.
     * Furthermore, POST is unsupported on state paths.
     * <p/>
     * The body of the request should be the content object of the event; the fields in this object will vary depending on the
     * type of event. See Room Events for the m. event specification.
     * <p/>
     * Requires auth: Yes.
     *
     * @param roomId          Required. The room to set the state in.
     * @param eventType       Required. The type of event to send.
     * @param stateKey        Required. The state_key for the state to send. Defaults to the empty string.
     * @param event           event.
     * @param servletRequest  servlet request.
     * @param servletResponse servlet response.
     * @param securityContext security context.
     * @return Status code 200: An ID for the sent event.
     */
    @PUT
    @Secured
    @Path("/{roomId}/state/{eventType}/{stateKey}")
    SendEventResponse sendEventWithTypeAndState(@PathParam("roomId") String roomId, @PathParam("eventType") String eventType,
                                                @PathParam("stateKey") String stateKey, Map<String, Object> event,
                                                @Context HttpServletRequest servletRequest, @Context HttpServletResponse servletResponse,
                                                @Context SecurityContext securityContext);

    /**
     * State events can be sent using this endpoint. This endpoint is equivalent to calling /rooms/{roomId}/state/{eventType}/{stateKey}
     * with an empty stateKey. Previous state events with matching (roomId) and (eventType), and empty (stateKey), will be overwritten.
     * <p/>
     * Requests to this endpoint cannot use transaction IDs like other PUT paths because they cannot be differentiated from the state_key.
     * Furthermore, POST is unsupported on state paths.
     * <p/>
     * The body of the request should be the content object of the event; the fields in this object will vary depending on the type
     * of event. See Room Events for the m. event specification.
     * <p/>
     * Requires auth: Yes.
     *
     * @param roomId          Required. The room to set the state in
     * @param eventType       Required. The type of event to send.
     * @param event           event.
     * @param servletRequest  servlet request.
     * @param servletResponse servlet response.
     * @param securityContext security context.
     * @return Status code 200: An ID for the sent event.
     */
    @PUT
    @Secured
    @Path("/{roomId}/state/{eventType}")
    SendEventResponse sendEventWithType(@PathParam("roomId") String roomId, @PathParam("eventType") String eventType,
                                        Map<String, Object> event, @Context HttpServletRequest servletRequest,
                                        @Context HttpServletResponse servletResponse, @Context SecurityContext securityContext);

    /**
     * This endpoint is used to send a message event to a room. Message events allow access to historical events and pagination,
     * making them suited for "once-off" activity in a room.
     * <p/>
     * The body of the request should be the content object of the event; the fields in this object will vary depending on the
     * type of event. See Room Events for the m. event specification.
     * <p/>
     * Requires auth: Yes.
     *
     * @param roomId          Required. The room to send the event to.
     * @param eventType       Required. The type of event to send.
     * @param txnId           Required. The transaction ID for this event. Clients should generate an ID unique across requests with the
     *                        same access token; it will be used by the server to ensure idempotency of requests.
     * @param event           event.
     * @param servletRequest  servlet request.
     * @param servletResponse servlet response.
     * @param securityContext security context.
     * @return Status code 200: An ID for the sent event.
     */
    @PUT
    @Secured
    @Path("/{roomId}/send/{eventType}/{txnId}")
    SendEventResponse sendEvent(@PathParam("roomId") String roomId, @PathParam("eventType") String eventType,
                                @PathParam("txnId") String txnId, Map<String, Object> event, @Context HttpServletRequest servletRequest,
                                @Context HttpServletResponse servletResponse, @Context SecurityContext securityContext);

    /**
     * Strips all information out of an event which isn't critical to the integrity of the server-side representation of the room.
     * <p/>
     * This cannot be undone.
     * <p/>
     * Users may redact their own events, and any user with a power level greater than or equal to the redact power level of the
     * room may redact events there.
     * <p/>
     * Requires auth: Yes.
     *
     * @param roomId          Required. The room from which to redact the event.
     * @param eventId         Required. The ID of the event to redact.
     * @param txnId           Required. The transaction ID for this event. Clients should generate a unique ID; it will be used by the
     *                        server to ensure idempotency of requests.
     * @param event           The reason for the event being redacted.
     * @param servletRequest  servlet request.
     * @param servletResponse servlet response.
     * @param securityContext security context.
     * @return Status code 200: An ID for the redaction event.
     */
    @PUT
    @Secured
    @Path("/{roomId}/redact/{eventId}/{txnId}")
    SendEventResponse redact(@PathParam("roomId") String roomId, @PathParam("eventId") String eventId,
                             @PathParam("txnId") String txnId, RedactRequest event, @Context HttpServletRequest servletRequest,
                             @Context HttpServletResponse servletResponse, @Context SecurityContext securityContext);

}
