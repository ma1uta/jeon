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
import io.github.ma1uta.matrix.Secured;
import io.github.ma1uta.matrix.client.model.deprecatedsync.DeprecatedInitialSyncResponse;
import io.github.ma1uta.matrix.client.model.deprecatedsync.DeprecatedRoomInfo;
import io.github.ma1uta.matrix.event.Event;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
 * Warning: This API is deprecated and will be removed from a future release.
 */
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
     * @param uriInfo         Request Information.
     * @param httpHeaders     Http headers.
     * @param asyncResponse   Asynchronous response.
     * @param securityContext Security context.
     * @deprecated in favor of
     * {@link SyncApi#sync(String, String, Boolean, String, Long, UriInfo, HttpHeaders, AsyncResponse, SecurityContext)}.
     */
    @Operation(
        summary = "This returns the full state for this user, with an optional limit on the number of messages per room to return.",
        description = "This endpoint was deprecated in r0 of this specification. Clients should instead call the /sync API with no since"
            + " parameter. See the migration guide.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The user's current state.",
                content = @Content(
                    schema = @Schema(
                        implementation = DeprecatedInitialSyncResponse.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "404",
                description = "There is no avatar URL for this user or this user does not exist.",
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
        },
        deprecated = true
    )
    @Deprecated
    @GET
    @Secured
    @Path("/initialSync")
    void initialSync(
        @QueryParam("limit") Long limit,
        @QueryParam("archived") Boolean archived,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
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
     * @param uriInfo         Request Information.
     * @param httpHeaders     Http headers.
     * @param asyncResponse   Asynchronous response.
     * @param securityContext Security context.
     * @deprecated in favor of {@link EventApi#roomEvent(String, String, UriInfo, HttpHeaders, AsyncResponse, SecurityContext)} or
     * {@link EventContextApi#context(String, String, Integer, UriInfo, HttpHeaders, AsyncResponse, SecurityContext)}.
     */
    @Operation(
        summary = "Get a single event based on event_id.",
        description = "You must have permission to retrieve this event e.g. by being a member in the room for this event."
            + " This endpoint was deprecated in r0 of this specification. Clients should instead call the /rooms/{roomId}/event/{eventId}"
            + " API or the /rooms/{roomId}/context/{eventId} API.",
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
        },
        deprecated = true
    )
    @Deprecated
    @GET
    @Secured
    @Path("/events/{eventId}")
    void event(
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
     * @param uriInfo         Request Information.
     * @param httpHeaders     Http headers.
     * @param asyncResponse   Asynchronous response.
     * @param securityContext Security context.
     * @deprecated in favor of
     * {@link SyncApi#sync(String, String, Boolean, String, Long, UriInfo, HttpHeaders, AsyncResponse, SecurityContext)}.
     */
    @Operation(
        summary = "Get a copy of the current state and the most recent messages in a room.",
        description = "This endpoint was deprecated in r0 of this specification. There is no direct replacement; the relevant information"
            + " is returned by the /sync API. See the migration guide.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The current state of the room.",
                content = @Content(
                    schema = @Schema(
                        implementation = DeprecatedRoomInfo.class
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
        },
        deprecated = true
    )
    @Deprecated
    @GET
    @Secured
    @Path("/rooms/{roomId}/initialSync")
    void roomInitialSync(
        @Parameter(
            description = "The room to get the data.",
            required = true
        ) @PathParam("roomId") String roomId,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );
}
