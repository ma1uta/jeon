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
import io.github.ma1uta.matrix.Secured;
import io.github.ma1uta.matrix.client.model.deprecatedsync.DeprecatedInitialSyncResponse;
import io.github.ma1uta.matrix.client.model.deprecatedsync.DeprecatedRoomInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

/**
 * Warning: This API is deprecated and will be removed from a future release.
 */
@Api(
    value = "DeprecatedSync",
    description = "Warning: This API is deprecated and will be removed from a future release.."
)
@Deprecated
@Path("/_matrix/client/r0")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface DeprecatedSyncApi {

    /**
     * This returns the full state for this user, with an optional limit on the number of messages per room to return.
     * <br>
     * This endpoint was deprecated in r0 of this specification. Clients should instead call the /sync API with no since parameter.
     * See the migration guide.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <br>
     * Return: {@link DeprecatedInitialSyncResponse}.
     * <p>Status code 200: The user's current state.</p>
     * <p>Status code 404: There is no avatar URL for this user or this user does not exist.</p>
     *
     * @param limit           The maximum number of messages to return for each room.
     * @param archived        Whether to include rooms that the user has left. If false then only rooms that the user has been
     *                        invited to or has joined are included. If set to true then rooms that the user has left are included
     *                        as well. By default this is false.
     * @param servletRequest  Servlet request.
     * @param asyncResponse   Asynchronous response.
     * @param securityContext Security context.
     * @deprecated in favor of
     * {@link SyncApi#sync(String, String, Boolean, String, Long, HttpServletRequest, AsyncResponse, SecurityContext)}.
     */
    @ApiOperation(
        value = "This returns the full state for this user, with an optional limit on the number of messages per room to return.",
        notes = "This endpoint was deprecated in r0 of this specification. Clients should instead call the /sync API with no since"
            + " parameter. See the migration guide.",
        response = DeprecatedInitialSyncResponse.class,
        authorizations = @Authorization("Authorization"
        )
    )
    @ApiResponses( {
        @ApiResponse(code = 200, message = "The user's current state."),
        @ApiResponse(code = 404, message = "There is no avatar URL for this user or this user does not exist.")
    })
    @Deprecated
    @GET
    @Secured
    @Path("/initialSync")
    void initialSync(
        @QueryParam("limit") Long limit,
        @QueryParam("archived") Boolean archived,

        @Context HttpServletRequest servletRequest,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );

    /**
     * Get a single event based on event_id. You must have permission to retrieve this event e.g. by being a member in the room
     * for this event.
     * <br>
     * This endpoint was deprecated in r0 of this specification. Clients should instead call the /rooms/{roomId}/event/{eventId}
     * API or the /rooms/{roomId}/context/{eventId} API.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <br>
     * Return: {@link Event}.
     * <p>Status code 200: The full event.</p>
     * <p>Status code 404: The event was not found or you do not have permission to read this event.</p>
     *
     * @param eventId         Required. The event ID to get.
     * @param servletRequest  Servlet request.
     * @param asyncResponse   Asynchronous response.
     * @param securityContext Security context.
     * @deprecated in favor of {@link EventApi#roomEvent(String, String, HttpServletRequest, AsyncResponse, SecurityContext)} or
     * {@link EventContextApi#context(String, String, Integer, HttpServletRequest, AsyncResponse, SecurityContext)}.
     */
    @ApiOperation(
        value = "Get a single event based on event_id.",
        notes = "You must have permission to retrieve this event e.g. by being a member in the room for this event."
            + " This endpoint was deprecated in r0 of this specification. Clients should instead call the /rooms/{roomId}/event/{eventId}"
            + " API or the /rooms/{roomId}/context/{eventId} API.",
        response = Event.class,
        authorizations = @Authorization("Authorization")
    )
    @ApiResponses( {
        @ApiResponse(code = 200, message = "The full event."),
        @ApiResponse(code = 404, message = "The event was not found or you do not have permission to read this event.")
    })
    @Deprecated
    @GET
    @Secured
    @Path("/events/{eventId}")
    void event(
        @ApiParam(
            value = "The event ID to get.",
            required = true
        ) @PathParam("eventId") String eventId,

        @Context HttpServletRequest servletRequest,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );

    /**
     * Get a copy of the current state and the most recent messages in a room.
     * <br>
     * This endpoint was deprecated in r0 of this specification. There is no direct replacement; the relevant information is returned
     * by the /sync API. See the migration guide.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <br>
     * Return: {@link DeprecatedRoomInfo}.
     * <p>Status code 200: The current state of the room.</p>
     * <p>Status code 403: You aren't a member of the room and weren't previously a member of the room.</p>
     *
     * @param roomId          Required. The room to get the data.
     * @param servletRequest  Servlet request.
     * @param asyncResponse   Asynchronous response.
     * @param securityContext Security context.
     * @deprecated in favor of
     * {@link SyncApi#sync(String, String, Boolean, String, Long, HttpServletRequest, AsyncResponse, SecurityContext)}.
     */
    @ApiOperation(
        value = "Get a copy of the current state and the most recent messages in a room.",
        notes = "This endpoint was deprecated in r0 of this specification. There is no direct replacement; the relevant information"
            + " is returned by the /sync API. See the migration guide.",
        response = DeprecatedRoomInfo.class,
        authorizations = @Authorization("Authorization")
    )
    @ApiResponses( {
        @ApiResponse(code = 200, message = "The current state of the room."),
        @ApiResponse(code = 403, message = "You aren't a member of the room and weren't previously a member of the room.")
    })
    @Deprecated
    @GET
    @Secured
    @Path("/rooms/{roomId}/initialSync")
    void roomInitialSync(
        @ApiParam(
            value = "The room to get the data.",
            required = true
        ) @PathParam("roomId") String roomId,

        @Context HttpServletRequest servletRequest,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );
}
