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

import io.github.ma1uta.matrix.EmptyResponse;
import io.github.ma1uta.matrix.Event;
import io.github.ma1uta.matrix.RateLimit;
import io.github.ma1uta.matrix.Secured;
import io.github.ma1uta.matrix.client.model.presence.PresenceList;
import io.github.ma1uta.matrix.client.model.presence.PresenceStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

/**
 * Each user has the concept of presence information. This encodes:
 * <ul>
 * <li>Whether the user is currently online</li>
 * <li>How recently the user was last active (as seen by the server)</li>
 * <li>Whether a given client considers the user to be currently idle</li>
 * <li>Arbitrary information about the user's current status (e.g. "in a meeting").</li>
 * </ul>
 * <p/>
 * This information is collated from both per-device (online, idle, last_active) and per-user (status) data, aggregated
 * by the user's homeserver and transmitted as an m.presence event. This is one of the few events which are sent outside
 * the context of a room. Presence events are sent to all users who subscribe to this user's presence through a presence
 * list or by sharing membership of a room.
 * <p/>
 * A presence list is a list of user IDs whose presence the user wants to follow. To be added to this list, the user being
 * added must be invited by the list owner who must accept the invitation.
 * <p/>
 * User's presence state is represented by the presence key, which is an enum of one of the following:
 * <ul>
 * <li>online : The default state when the user is connected to an event stream.</li>
 * <li>unavailable : The user is not reachable at this time e.g. they are idle.</li>
 * <li>offline : The user is not connected to an event stream or is explicitly suppressing their profile information from being sent.</li>
 * </ul>
 * <p/>
 * <a href="https://matrix.org/docs/spec/client_server/r0.3.0.html#id295">Specification.</a>
 */
@Api(value = "Presence", description = "Each user has the concept of presence information.")
@Path("/_matrix/client/r0/presence")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface PresenceApi {

    /**
     * This API sets the given user's presence state. When setting the status, the activity time is updated to reflect
     * that activity; the client does not need to specify the last_active_ago field. You cannot set the presence state of
     * another user.
     * <p/>
     * <b>Rate-limited</b>: Yes.
     * <p/>
     * <b>Requires auth</b>: Yes.
     *
     * @param userId          Required. The user whose presence state to update.
     * @param presenceStatus  JSON body request.
     * @param servletRequest  servlet request.
     * @param servletResponse servlet response.
     * @param securityContext security context.
     * @return Status code 200: The new presence state was set.
     *     Status code 429: This request was rate-limited.
     */
    @ApiOperation(value = "This API sets the given user's presence state. When setting the status, the activity time is updated to "
        + "reflect that activity; the client does not need to specify the last_active_ago field. You cannot set the presence state of "
        + "another user.", response = EmptyResponse.class)
    @ApiResponses( {
        @ApiResponse(code = 200, message = "The new presence state was set."),
        @ApiResponse(code = 429, message = "This request was rate-limited.")
    })
    @PUT
    @RateLimit
    @Secured
    @Path("/{userId}/status")
    EmptyResponse setPresenceStatus(
        @ApiParam(value = "The user whose presence state to update.", required = true) @PathParam("userId") String userId,
        @ApiParam("JSON body request.") PresenceStatus presenceStatus,
        @Context HttpServletRequest servletRequest, @Context HttpServletResponse servletResponse, @Context SecurityContext securityContext);

    /**
     * Get the given user's presence state.
     *
     * @param userId          Required. The user whose presence state to get.
     * @param servletRequest  servlet request.
     * @param servletResponse servlet response.
     * @return Status code 200: The presence state for this user.
     *     Status code 404: There is no presence state for this user. This user may not exist or isn't exposing presence information to you.
     */
    @ApiOperation(value = "Get the given user's presence state.", response = PresenceStatus.class)
    @ApiResponses( {
        @ApiResponse(code = 200, message = "The presence state for this user."),
        @ApiResponse(code = 404, message = "There is no presence state for this user. This user may not exist or isn't "
            + "exposing presence information to you.")
    })
    @GET
    @Path("/{userId}/status")
    PresenceStatus getPresenceStatus(
        @ApiParam(value = "The user whose presence state to get.", required = true) @PathParam("userId") String userId,
        @Context HttpServletRequest servletRequest, @Context HttpServletResponse servletResponse);

    /**
     * Adds or removes users from this presence list.
     * <p/>
     * <b>Rate-limited</b>: Yes.
     * <p/>
     * <b>Requires auth</b>: Yes.
     *
     * @param userId          Required. The user whose presence list is being modified.
     * @param presenceList    JSON body request.
     * @param servletRequest  servlet request.
     * @param servletResponse servlet response.
     * @param securityContext security context.
     * @return Status code 200: The list was updated.
     *     Status code 429: This request was rate-limited.
     */
    @ApiOperation(value = "Adds or removes users from this presence list.", response = EmptyResponse.class)
    @ApiResponses( {
        @ApiResponse(code = 200, message = "The list was updated."),
        @ApiResponse(code = 429, message = "This request was rate-limited.")
    })
    @POST
    @RateLimit
    @Secured
    @Path("/list/{userId}")
    EmptyResponse setPresenceList(
        @ApiParam(value = "The user whose presence list is being modified.", required = true) @PathParam("userId") String userId,
        @ApiParam("JSON body request") PresenceList presenceList,
        @Context HttpServletRequest servletRequest, @Context HttpServletResponse servletResponse, @Context SecurityContext securityContext);

    /**
     * Retrieve a list of presence events for every user on this list.
     *
     * @param userId          Required. The user whose presence list should be retrieved.
     * @param servletRequest  servlet request.
     * @param servletResponse servlet response.
     * @return Status code 200: A list of presence events for this list.
     */
    @ApiOperation(value = "Retrieve a list of presence events for every user on this list.", response = List.class)
    @ApiResponses( {
        @ApiResponse(code = 200, message = "A list of presence events for this list.")
    })
    @GET
    @Path("/list/{userId}")
    List<Event> getPresenceList(
        @ApiParam(value = "The user whose presence list should be retrieved.", required = true) @PathParam("userId") String userId,
        @Context HttpServletRequest servletRequest, @Context HttpServletResponse servletResponse);
}
