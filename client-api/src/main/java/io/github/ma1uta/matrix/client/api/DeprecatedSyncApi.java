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
import io.github.ma1uta.matrix.client.model.deprecatedsync.DeprecatedInitialSyncResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

/**
 * Warning: This API is deprecated and will be removed from a future release.
 */
@Api(
    value = "Deprecated Sync API.",
    description = "Warning: This API is deprecated and will be removed from a future release.."
)
@Deprecated
@Path("/_matrix/client/r0")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface DeprecatedSyncApi {

    /**
     * This will listen for new events and return them to the caller. This will block until an event is received, or until the timeout
     * is reached.
     * <br>
     * This endpoint was deprecated in r0 of this specification. Clients should instead call the /sync API with a since parameter.
     * See the migration guide.
     * <br>
     * <b>Requires auth</b>: Yes.
     *
     * @param from            The token to stream from. This token is either from a previous request to this API or from the initial
     *                        sync API.
     * @param timeout         The maximum time in milliseconds to wait for an event.
     * @param servletRequest  Servlet request.
     * @param servletResponse Servlet response.
     * @param securityContext Security context.
     * @return <p>Status code 200: The events received, which may be none.</p>
     * <p>Status code 400: Bad pagination from parameter.</p>
     */
    @ApiOperation(
        value = "This will listen for new events and return them to the caller. This will block until an event is received, or"
            + " until the timeout is reached.",
        notes = "This endpoint was deprecated in r0 of this specification. Clients should instead call the /sync API with a since"
            + " parameter. See the migration guide."
    )
    @ApiResponses( {
        @ApiResponse(code = 200, message = "The events received, which may be none."),
        @ApiResponse(code = 400, message = "Bad pagination from parameter.")
    })
    @Deprecated
    @GET
    @Secured
    @Path("/events")
    Page<Event> events(
        @ApiParam(
            value = "The token to stream from. This token is either from a previous request to this API or from the initial sync API."
        ) @QueryParam("from") String from,
        @ApiParam(
            value = "The maximum time in milliseconds to wait for an event."
        ) @QueryParam("timeout") Long timeout,

        @Context HttpServletRequest servletRequest,
        @Context HttpServletResponse servletResponse,
        @Context SecurityContext securityContext
    );

    /**
     * This returns the full state for this user, with an optional limit on the number of messages per room to return.
     * <br>
     * This endpoint was deprecated in r0 of this specification. Clients should instead call the /sync API with no since parameter.
     * See the migration guide.
     * <br>
     * <b>Requires auth</b>: Yes.
     *
     * @param limit           The maximum number of messages to return for each room.
     * @param archived        Whether to include rooms that the user has left. If false then only rooms that the user has been
     *                        invited to or has joined are included. If set to true then rooms that the user has left are included
     *                        as well. By default this is false.
     * @param servletRequest  Servlet request.
     * @param servletResponse Servlet response.
     * @param securityContext Security context.
     * @return <p>Status code 200: The user's current state.</p>
     * <p>Status code 404: There is no avatar URL for this user or this user does not exist.</p>
     */
    @ApiOperation(
        value = "This returns the full state for this user, with an optional limit on the number of messages per room to return.",
        notes = "This endpoint was deprecated in r0 of this specification. Clients should instead call the /sync API with no since"
            + " parameter. See the migration guide."
    )
    @ApiResponses( {
        @ApiResponse(code = 200, message = "The user's current state."),
        @ApiResponse(code = 404, message = "There is no avatar URL for this user or this user does not exist.")
    })
    @Deprecated
    @GET
    @Secured
    @Path("/initialSync")
    DeprecatedInitialSyncResponse initialSync(
        @QueryParam("limit") Long limit,
        @QueryParam("archived") Boolean archived,

        @Context HttpServletRequest servletRequest,
        @Context HttpServletResponse servletResponse,
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
     *
     * @param eventId         Required. The event ID to get.
     * @param servletRequest  Servlet request.
     * @param servletResponse Servlet response.
     * @param securityContext Security context.
     * @return <p>Status code 200: The full event.</p>
     * <p>Status code 404: The event was not found or you do not have permission to read this event.</p>
     */
    @ApiOperation(
        value = "Get a single event based on event_id.",
        notes = "You must have permission to retrieve this event e.g. by being a member in the room for this event."
            + " This endpoint was deprecated in r0 of this specification. Clients should instead call the /rooms/{roomId}/event/{eventId}"
            + " API or the /rooms/{roomId}/context/{eventId} API."
    )
    @ApiResponses( {
        @ApiResponse(code = 200, message = "The full event."),
        @ApiResponse(code = 404, message = "The event was not found or you do not have permission to read this event.")
    })
    @Deprecated
    @GET
    @Secured
    @Path("/events/{eventId}")
    Event event(
        @ApiParam(
            value = "The event ID to get.",
            required = true
        ) @PathParam("eventId") String eventId,

        @Context HttpServletRequest servletRequest,
        @Context HttpServletResponse servletResponse,
        @Context SecurityContext securityContext
    );
}