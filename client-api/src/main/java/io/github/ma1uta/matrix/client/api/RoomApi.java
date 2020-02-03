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

import io.github.ma1uta.matrix.EmptyResponse;
import io.github.ma1uta.matrix.ErrorResponse;
import io.github.ma1uta.matrix.RateLimit;
import io.github.ma1uta.matrix.RateLimitedErrorResponse;
import io.github.ma1uta.matrix.Secured;
import io.github.ma1uta.matrix.client.model.room.CreateRoomRequest;
import io.github.ma1uta.matrix.client.model.room.InviteRequest;
import io.github.ma1uta.matrix.client.model.room.JoinRequest;
import io.github.ma1uta.matrix.client.model.room.JoinedRoomsResponse;
import io.github.ma1uta.matrix.client.model.room.KickRequest;
import io.github.ma1uta.matrix.client.model.room.NewVersion;
import io.github.ma1uta.matrix.client.model.room.PublicRoomsRequest;
import io.github.ma1uta.matrix.client.model.room.PublicRoomsResponse;
import io.github.ma1uta.matrix.client.model.room.ReplacementRoom;
import io.github.ma1uta.matrix.client.model.room.RoomId;
import io.github.ma1uta.matrix.client.model.room.RoomResolveResponse;
import io.github.ma1uta.matrix.client.model.room.RoomVisibility;
import io.github.ma1uta.matrix.client.model.room.UnbanRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

/**
 * Rooms API.
 */
@Path("/_matrix/client/r0")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface RoomApi {

    /**
     * Visibility.
     */
    class Visibility {

        protected Visibility() {
        }

        /**
         * Public.
         */
        public static final String PUBLIC = "public";

        /**
         * Private.
         */
        public static final String PRIVATE = "private";
    }

    /**
     * Presets.
     */
    class Preset {

        protected Preset() {
        }

        /**
         * Private.
         */
        public static final String PRIVATE_CHAT = "private_chat";

        /**
         * Public.
         */
        public static final String PUBLIC_CHAT = "public_chat";

        /**
         * Trusted.
         */
        public static final String TRUSTED_PRIVATE_CHAT = "trusted_private_chat";
    }

    /**
     * Create a new room with various configuration options.
     * <br>
     * The server MUST apply the normal state resolution rules when creating the new room, including checking power levels for each event.
     * It MUST apply the events implied by the request in the following order:
     * <ol>
     * <li>A default m.room.power_levels event, giving the room creator (and not other members) permission to send state events.</li>
     * <li>Events set by the presets.</li>
     * <li>Events listed in initial_state, in the order that they are listed.</li>
     * <li>Events implied by name and topic.</li>
     * <li>Invite events implied by invite and invite_3pid.</li>
     * </ol>
     * The available presets do the following with respect to room state:
     * <table summary="Presets">
     * <tr>
     * <th>Preset</th>
     * <th>join_rules</th>
     * <th>history_visibility</th>
     * <th>guest_access</th>
     * <th>Other</th>
     * </tr>
     * <tr>
     * <td>private_chat</td>
     * <td>invite</td>
     * <td>shared</td>
     * <td>can_join</td>
     * </tr>
     * <tr>
     * <td>trusted_private_chat</td>
     * <td>invite</td>
     * <td>shared</td>
     * <td>can_join</td>
     * <td>All invitees are given the same power level as the room creator.</td>
     * </tr>
     * <tr>
     * <td>public_chat</td>
     * <td>public</td>
     * <td>shared</td>
     * <td>forbidden</td>
     * </tr>
     * </table>
     * The server will create a m.room.create event in the room with the requesting user as the creator, alongside other keys
     * provided in the creation_content.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <br>
     * Return: {@link RoomId}.
     * <p>Status code 200: Information about the newly created room.</p>
     * <p>Status code 400: The request is invalid. A meaningful errcode and description error text will be returned.</p>
     * <p>Example reasons for rejection include:</p>
     * <ul>
     * <li>The request body is malformed (errcode set to M_BAD_JSON or M_NOT_JSON).</li>
     * <li>The room alias specified is already taken (errcode set to M_ROOM_IN_USE).</li>
     * <li>The initial state implied by the parameters to the request is invalid: for example, the user's power_level is set
     * below that necessary to set the room name (errcode set to M_INVALID_ROOM_STATE).</li>
     * </ul>
     *
     * @param createRoomRequest JSON body parameters.
     * @param uriInfo           Request Information.
     * @param httpHeaders       Http headers.
     * @param asyncResponse     Asynchronous response.
     * @param securityContext   Security context.
     */
    @Operation(
        summary = "Create a new room with various configuration options.",
        description = "The server MUST apply the normal state resolution rules when creating the new room, including checking power "
            + "levels for each event.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Information about the newly created room.",
                content = @Content(
                    schema = @Schema(
                        implementation = RoomId.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "400",
                description = "The request is invalid. A meaningful errcode and description error text will be returned.",
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
            "Room creation"
        }
    )
    @POST
    @Secured
    @Path("/createRoom")
    void create(
        @RequestBody(
            description = "JSON body request."
        ) CreateRoomRequest createRoomRequest,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );

    /**
     * Create a new mapping from room alias to room ID.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <br>
     * Return: {@link EmptyResponse}.
     * <p>Status code 200: The mapping was created.</p>
     * <p>Status code 409: A room alias with that name already exists.</p>
     *
     * @param roomAlias       Required. The room alias to set.
     * @param roomId          json body request.
     * @param uriInfo         Request Information.
     * @param httpHeaders     Http headers.
     * @param asyncResponse   Asynchronous response.
     * @param securityContext Security context.
     */
    @Operation(
        summary = "Create a new mapping from room alias to room ID.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The mapping was created.",
                content = @Content(
                    schema = @Schema(
                        implementation = EmptyResponse.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "409",
                description = "A room alias with that name already exists.",
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
            "Room directory"
        }
    )
    @PUT
    @Secured
    @Path("/directory/room/{roomAlias}")
    void createAlias(
        @Parameter(
            description = "The room alias to set",
            required = true
        ) @PathParam("roomAlias") String roomAlias,
        @RequestBody(
            description = "JSON body request"
        ) RoomId roomId,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );

    /**
     * Requests that the server resolve a room alias to a room ID.
     * <br>
     * The server will use the federation API to resolve the alias if the domain part of the alias does not correspond to the server's
     * own domain.
     * <br>
     * Return: {@link RoomResolveResponse}.
     * <p>Status code 200: The room ID and other information for this alias.</p>
     * <p>Status code 404: There is no mapped room ID for this room alias.</p>
     *
     * @param roomAlias     Required. The room alias.
     * @param uriInfo       Request Information.
     * @param httpHeaders   Http headers.
     * @param asyncResponse Asynchronous response.
     */
    @Operation(
        summary = "Requests that the server resolve a room alias to a room ID. The server will use the federation API to "
            + "resolve the alias if the domain part of the alias does not correspond to the server's own domain.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The room ID and other information for this alias.",
                content = @Content(
                    schema = @Schema(
                        implementation = RoomResolveResponse.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "404",
                description = "There is no mapped room ID for this room alias.",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponse.class
                    )
                )
            )
        },
        tags = {
            "Room directory"
        }
    )
    @GET
    @Path("/directory/room/{roomAlias}")
    void resolveAlias(
        @Parameter(
            description = "The room alias",
            required = true
        ) @PathParam("roomAlias") String roomAlias,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse
    );

    /**
     * Remove a mapping of room alias to room ID.
     * <br>
     * Servers may choose to implement additional access control checks here, for instance that room aliases can only be deleted
     * by their creator or a server administrator.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <br>
     * Return: {@link EmptyResponse}.
     * <p>Status code 200: The mapping was deleted.</p>
     *
     * @param roomAlias       Required. The room alias to remove.
     * @param uriInfo         Request Information.
     * @param httpHeaders     Http headers.
     * @param asyncResponse   Asynchronous response.
     * @param securityContext Security context.
     */
    @Operation(
        summary = "Remove a mapping of room alias to room ID.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The mapping was deleted.",
                content = @Content(
                    schema = @Schema(
                        implementation = EmptyResponse.class
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
            "Room directory"
        }
    )
    @DELETE
    @Secured
    @Path("/directory/room/{roomAlias}")
    void deleteAlias(
        @Parameter(
            description = "The room alias to remove.",
            required = true
        ) @PathParam("roomAlias") String roomAlias,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );

    /**
     * This API returns a list of the user's current rooms.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <br>
     * Return: {@link JoinedRoomsResponse}.
     * <p>Status code 200: A list of the rooms the user is in.</p>
     *
     * @param uriInfo         Request Information.
     * @param httpHeaders     Http headers.
     * @param asyncResponse   Asynchronous response.
     * @param securityContext Security context.
     */
    @Operation(
        summary = "This API returns a list of the user's current rooms.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "A list of the rooms the user is in.",
                content = @Content(
                    schema = @Schema(
                        implementation = JoinedRoomsResponse.class
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
            "Room membership"
        }
    )
    @GET
    @Secured
    @Path("/joined_rooms")
    void joinedRooms(
        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );

    /**
     * Note that there are two forms of this API, which are documented separately. This version of the API requires that the inviter
     * knows the Matrix identifier of the invitee. The other is documented in the third party invites section.
     * <br>
     * This API invites a user to participate in a particular room. They do not start participating in the room until they actually
     * join the room.
     * <br>
     * Only users currently in a particular room can invite other users to join that room.
     * <br>
     * If the user was invited to the room, the homeserver will append a m.room.member event to the room.
     * <br>
     * <b>Rate-limited</b>: Yes.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <br>
     * Return: {@link EmptyResponse}.
     * <p>Status code 200: The user has been invited to join the room.</p>
     * <p>Status code 403: You do not have permission to invite the user to the room. A meaningful errcode and description error text
     * will be returned. Example reasons for rejections are:</p>
     * <ul>
     * <li>The invitee has been banned from the room.</li>
     * <li>The invitee is already a member of the room.</li>
     * <li>The inviter is not currently in the room.</li>
     * <li>The inviter's power level is insufficient to invite users to the room.</li>
     * </ul>
     * <p>Status code 429: This request was rate-limited.</p>
     *
     * @param roomId          Required. The room identifier (not alias) to which to invite the user.
     * @param inviteRequest   JSON body request.
     * @param uriInfo         Request Information.
     * @param httpHeaders     Http headers.
     * @param asyncResponse   Asynchronous response.
     * @param securityContext Security context.
     */
    @Operation(
        summary = "This API invites a user to participate in a particular room. They do not start participating in the "
            + "room until they actually join the room.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The user has been invited to join the room.",
                content = @Content(
                    schema = @Schema(
                        implementation = EmptyResponse.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "403",
                description = "You do not have permission to invite the user to the room. A meaningful errcode and "
                    + "description error text will be returned. ",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponse.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "429",
                description = "This request was rate-limited.",
                content = @Content(
                    schema = @Schema(
                        implementation = RateLimitedErrorResponse.class
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
            "Room membership"
        }
    )
    @POST
    @RateLimit
    @Secured
    @Path("/rooms/{roomId}/invite")
    void invite(
        @Parameter(
            description = "The room identifier (not alias) to which to invite the user.",
            required = true
        ) @PathParam("roomId") String roomId,
        @RequestBody(
            description = "JSON body request"
        ) InviteRequest inviteRequest,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );

    /**
     * Note that this API requires a room ID, not alias. /join/{roomIdOrAlias} exists if you have a room alias.
     * <br>
     * This API starts a user participating in a particular room, if that user is allowed to participate in that room.
     * After this call, the client is allowed to see all current state events in the room, and all subsequent events associated
     * with the room until the user leaves the room.
     * <br>
     * After a user has joined a room, the room will appear as an entry in the response of the /initialSync and /sync APIs.
     * <br>
     * If a third_party_signed was supplied, the homeserver must verify that it matches a pending m.room.third_party_invite
     * event in the room, and perform key validity checking if required by the event.
     * <br>
     * <b>Rate-limited</b>: Yes.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <br>
     * Return: {@link RoomId}.
     * <p>Status code 200: The room has been joined. The joined room ID must be returned in the room_id field.</p>
     * <p>Status code 403: You do not have permission to join the room. A meaningful errcode and description error text will
     * be returned.</p>
     * <p>Example reasons for rejection are:</p>
     * <ul>
     * <li>The room is invite-only and the user was not invited.</li>
     * <li>The user has been banned from the room.</li>
     * </ul>
     * <p>Status code 429:This request was rate-limited.</p>
     *
     * @param roomId          Required. The room identifier (not alias) to join.
     * @param joinRequest     JSON body request.
     * @param uriInfo         Request Information.
     * @param httpHeaders     Http headers.
     * @param asyncResponse   Asynchronous response.
     * @param securityContext Security context.
     */
    @Operation(
        summary = "This API starts a user participating in a particular room, if that user is allowed to participate in "
            + "that room. After this call, the client is allowed to see all current state events in the room, and all subsequent "
            + "events associated with the room until the user leaves the room.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The room has been joined. The joined room ID must be returned in the room_id field.",
                content = @Content(
                    schema = @Schema(
                        implementation = RoomId.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "403",
                description = "You do not have permission to join the room. A meaningful errcode and description "
                    + "error text will be returned.",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponse.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "429",
                description = "This request was rate-limited.",
                content = @Content(
                    schema = @Schema(
                        implementation = RateLimitedErrorResponse.class
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
            "Room membership"
        }
    )
    @POST
    @RateLimit
    @Secured
    @Path("/rooms/{roomId}/join")
    void joinById(
        @Parameter(
            description = "The room identifier (not alias) to join.",
            required = true
        ) @PathParam("roomId") String roomId,
        @RequestBody(
            description = "JSON body request"
        ) JoinRequest joinRequest,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );

    /**
     * Note that this API takes either a room ID or alias, unlike /room/{roomId}/join.
     * <br>
     * This API starts a user participating in a particular room, if that user is allowed to participate in that room.
     * After this call, the client is allowed to see all current state events in the room, and all subsequent events associated
     * with the room until the user leaves the room.
     * <br>
     * After a user has joined a room, the room will appear as an entry in the response of the /initialSync and /sync APIs.
     * <br>
     * If a third_party_signed was supplied, the homeserver must verify that it matches a pending m.room.third_party_invite
     * event in the room, and perform key validity checking if required by the event.
     * <br>
     * <b>Rate-limited</b>: Yes.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <br>
     * Return: {@link RoomId}.
     * <p>Status code 200: The room has been joined. The joined room ID must be returned in the room_id field.</p>
     * <p>Status code 403: You do not have permission to join the room. A meaningful errcode and description error text will
     * be returned.</p>
     * <p>Example reasons for rejection are:</p>
     * <ul>
     * <li>The room is invite-only and the user was not invited.</li>
     * <li>The user has been banned from the room.</li>
     * </ul>
     * <p>Status code 429: This request was rate-limited.</p>
     *
     * @param roomIdOrAlias   Required. The room identifier or alias to join.
     * @param joinRequest     JSON body request.
     * @param serverName      The servers to attempt to join the room through. One of the servers must be participating in the room.
     * @param httpHeaders     Http headers.
     * @param uriInfo         Request Information.
     * @param asyncResponse   Asynchronous response.
     * @param securityContext Security context.
     */
    @Operation(
        summary = "This API starts a user participating in a particular room, if that user is allowed to participate in "
            + "that room. After this call, the client is allowed to see all current state events in the room, and all subsequent "
            + "events associated with the room until the user leaves the room.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The room has been joined. The joined room ID must be returned in the room_id field.",
                content = @Content(
                    schema = @Schema(
                        implementation = RoomId.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "403",
                description = "You do not have permission to join the room. A meaningful errcode and description "
                    + "error text will be returned.",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponse.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "429",
                description = "This request was rate-limited.",
                content = @Content(
                    schema = @Schema(
                        implementation = RateLimitedErrorResponse.class
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
            "Room membership"
        }
    )
    @POST
    @RateLimit
    @Secured
    @Path("/join/{roomIdOrAlias}")
    void joinByIdOrAlias(
        @Parameter(
            description = "The room identifier or alias to join.",
            required = true
        ) @PathParam("roomIdOrAlias") String roomIdOrAlias,
        @Parameter(
            name = "server_name",
            description = "The servers to attempt to join the room through. One of the servers must be participating in the room."
        ) @QueryParam("server_name") List<String> serverName,
        @RequestBody(
            description = "JSON body request."
        ) JoinRequest joinRequest,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );

    /**
     * This API stops a user participating in a particular room.
     * <br>
     * If the user was already in the room, they will no longer be able to see new events in the room.
     * If the room requires an invite to join, they will need to be re-invited before they can re-join.
     * <br>
     * If the user was invited to the room, but had not joined, this call serves to reject the invite.
     * <br>
     * The user will still be allowed to retrieve history from the room which they were previously allowed to see.
     * <br>
     * Rate-limited: Yes.
     * <br>
     * Requires auth: Yes.
     * <br>
     * Return: {@link EmptyResponse}.
     * <p>Status code 200: The room has been left.</p>
     * <p>Status code 429: This request was rate-limited.</p>
     *
     * @param roomId          Required. The room identifier to leave.
     * @param uriInfo         Request Information.
     * @param httpHeaders     Http headers.
     * @param asyncResponse   Asynchronous response.
     * @param securityContext Security context.
     */
    @Operation(
        summary = "This API stops a user participating in a particular room.",
        description = "If the user was already in the room, "
            + "they will no longer be able to see new events in the room. If the room requires an invite to join, they will need "
            + "to be re-invited before they can re-join. If the user was invited to the room, but had not joined, this call serves "
            + "to reject the invite.The user will still be allowed to retrieve history from the room which they were previously "
            + "allowed to see.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The room has been left.",
                content = @Content(
                    schema = @Schema(
                        implementation = EmptyResponse.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "429",
                description = "This request was rate-limited.",
                content = @Content(
                    schema = @Schema(
                        implementation = RateLimitedErrorResponse.class
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
            "Room membership"
        }
    )
    @POST
    @RateLimit
    @Secured
    @Path("/rooms/{roomId}/leave")
    void leave(
        @Parameter(
            description = "The room identifier to leave.",
            required = true
        ) @PathParam("roomId") String roomId,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );

    /**
     * This API stops a user remembering about a particular room.
     * <br>
     * In general, history is a first class citizen in Matrix. After this API is called, however, a user will no longer be
     * able to retrieve history for this room. If all users on a homeserver forget a room, the room is eligible for deletion
     * from that homeserver.
     * <br>
     * If the user is currently joined to the room, they must leave the room before calling this API.
     * <br>
     * <b>Rate-limited</b>: Yes.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <br>
     * Return: {@link EmptyResponse}.
     * <p>Status code 200: The room has been forgotten.</p>
     * <p>Status code 400: The user has not left the room.</p>
     * <p>Status code 429: This request was rate-limited.</p>
     *
     * @param roomId          Required. The room identifier to forget.
     * @param uriInfo         Request Information.
     * @param httpHeaders     Http headers.
     * @param asyncResponse   Asynchronous response.
     * @param securityContext Security context.
     */
    @Operation(
        summary = "This API stops a user remembering about a particular room. In general, history is a first class "
            + "citizen in Matrix. After this API is called, however, a user will no longer be able to retrieve history for this "
            + "room. If all users on a homeserver forget a room, the room is eligible for deletion from that homeserver.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The room has been forgotten.",
                content = @Content(
                    schema = @Schema(
                        implementation = EmptyResponse.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "400",
                description = "The user has not left the room.",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponse.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "429",
                description = "This request was rate-limited.",
                content = @Content(
                    schema = @Schema(
                        implementation = RateLimitedErrorResponse.class
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
            "Room membership"
        }
    )
    @POST
    @RateLimit
    @Secured
    @Path("/rooms/{roomId}/forget")
    void forget(
        @Parameter(
            description = "The room identifier to forget.",
            required = true
        ) @PathParam("roomId") String roomId,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );

    /**
     * Kick a user from the room.
     * <br>
     * The caller must have the required power level in order to perform this operation.
     * <br>
     * Kicking a user adjusts the target member's membership state to be ``leave`` with an
     * optional ``reason``. Like with other membership changes, a user can directly adjust
     * the target member's state by making a request to ``/rooms/&lt;room id&gt;/state/m.room.member/&lt;user id&gt;``.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <br>
     * Return: {@link EmptyResponse}.
     * <p>Status code 200: The user has been kicked from the room.</p>
     * <p>Status code 403: You do not have permission to kick the user from the room. A meaningful errcode and description error
     * text will be returned. Example reasons for rejections are:</p>
     * <ul>
     * <li>The kicker is not currently in the room.</li>
     * <li>The kickee is not currently in the room.</li>
     * <li>The kicker's power level is insufficient to kick users from the room.</li>
     * </ul>
     *
     * @param roomId          Required. The room identifier (not alias) from which the user should be kicked.
     * @param kickRequest     JSON body request.
     * @param uriInfo         Request Information.
     * @param httpHeaders     Http headers.
     * @param asyncResponse   Asynchronous response.
     * @param securityContext Security context.
     */
    @Operation(
        summary = "Kick a user from the room.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The user has been kicked from the room.",
                content = @Content(
                    schema = @Schema(
                        implementation = EmptyResponse.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "403",
                description = "You do not have permission to kick the user from the room. A meaningful errcode "
                    + "and description error text will be returned.",
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
            "Room membership"
        }
    )
    @POST
    @Secured
    @Path("/rooms/{roomId}/kick")
    void kick(
        @Parameter(
            description = "The room identifier (not alias) from which the user should be kicked.",
            required = true
        ) @PathParam("roomId") String roomId,
        @RequestBody(
            description = "JSON body request"
        ) KickRequest kickRequest,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );

    /**
     * Ban a user in the room. If the user is currently in the room, also kick them.
     * <br>
     * When a user is banned from a room, they may not join it or be invited to it until they are unbanned.
     * <br>
     * The caller must have the required power level in order to perform this operation.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <br>
     * Return: {@link EmptyResponse}.
     * <p>Status code 200: The user has been kicked and banned from the room.</p>
     * <p>Status code 403: You do not have permission to ban the user from the room. A meaningful errcode and description error
     * text will be returned. Example reasons for rejections are:</p>
     * <ul>
     * <li>The banner is not currently in the room.</li>
     * <li>The banner's power level is insufficient to ban users from the room.</li>
     * </ul>
     *
     * @param roomId          Required. The room identifier (not alias) from which the user should be banned.
     * @param banRequest      JSON body request.
     * @param uriInfo         Request Information.
     * @param httpHeaders     Http headers.
     * @param asyncResponse   Asynchronous response.
     * @param securityContext Security context.
     */
    @Operation(
        summary = "Ban a user in the room. If the user is currently in the room, also kick them.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The user has been kicked and banned from the room.",
                content = @Content(
                    schema = @Schema(
                        implementation = EmptyResponse.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "403",
                description = "You do not have permission to ban the user from the room. A meaningful errcode "
                    + "and description error text will be returned.",
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
            "Room membership"
        }
    )
    @POST
    @Secured
    @Path("/rooms/{roomId}/ban")
    void ban(
        @Parameter(
            description = "The room identifier (not alias) from which the user should be banned.",
            required = true
        ) @PathParam("roomId") String roomId,
        @RequestBody(
            description = "JSON body request."
        ) KickRequest banRequest,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );

    /**
     * Unban a user from the room. This allows them to be invited to the room, and join if they would otherwise be allowed to join
     * according to its join rules.
     * <br>
     * The caller must have the required power level in order to perform this operation.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <br>
     * Return: {@link EmptyResponse}.
     * <p>Status code 200: The user has been unbanned from the room.</p>
     * <p>Status code 403: You do not have permission to unban the user from the room. A meaningful errcode and description error
     * text will be returned. Example reasons for rejections are:</p>
     * <ul>
     * <li>The unbanner's power level is insufficient to unban users from the room.</li>
     * </ul>
     *
     * @param roomId          Required. The room identifier (not alias) from which the user should be unbanned.
     * @param unbanRequest    JSON body request.
     * @param uriInfo         Request Information.
     * @param httpHeaders     Http headers.
     * @param asyncResponse   Asynchronous response.
     * @param securityContext Security context.
     */
    @Operation(
        summary = "Unban a user from the room. This allows them to be invited to the room, and join if they would "
            + "otherwise be allowed to join according to its join rules.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The user has been unbanned from the room.",
                content = @Content(
                    schema = @Schema(
                        implementation = EmptyResponse.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "403",
                description = "You do not have permission to unban the user from the room. A meaningful errcode "
                    + "and description error text will be returned.",
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
            "Room membership"
        }
    )
    @POST
    @Secured
    @Path("/rooms/{roomId}/unban")
    void unban(
        @Parameter(
            description = "The room identifier (not alias) from which the user should be unbanned.",
            required = true
        ) @PathParam("roomId") String roomId,
        @RequestBody(
            description = "JSON body request."
        ) UnbanRequest unbanRequest,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );

    /**
     * Gets the visibility of a given room on the server's public room directory.
     * <br>
     * Return: {@link RoomVisibility}.
     * <p>Status code 200: The visibility of the room in the directory.</p>
     * <p>Status code 404: The room is not known to the server.</p>
     *
     * @param roomId        Required. The room ID.
     * @param uriInfo       Request Information.
     * @param httpHeaders   Http headers.
     * @param asyncResponse Asynchronous response.
     */
    @Operation(
        summary = "Gets the visibility of a given room on the server's public room directory.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The visibility of the room in the directory",
                content = @Content(
                    schema = @Schema(
                        implementation = RoomVisibility.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "404",
                description = "The room is not known to the server",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponse.class
                    )
                )
            )
        },
        tags = {
            "Room discovery"
        }
    )
    @GET
    @Path("/directory/list/room/{roomId}")
    void getVisibility(
        @Parameter(
            description = "The room ID.",
            required = true
        ) @PathParam("roomId") String roomId,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse
    );

    /**
     * Sets the visibility of a given room in the server's public room directory.
     * <br>
     * Servers may choose to implement additional access control checks here, for instance that room visibility can only be
     * changed by the room creator or a server administrator.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <br>
     * Return: {@link EmptyResponse}.
     * <p>Status code 200: The visibility was updated, or no change was needed.</p>
     * <p>Status code 404: The room is not known to the server.</p>
     *
     * @param roomId          Required. The room ID.
     * @param visibility      json body request.
     * @param uriInfo         Request Information.
     * @param httpHeaders     Http headers.
     * @param asyncResponse   Asynchronous response.
     * @param securityContext Security context.
     */
    @Operation(
        summary = "Sets the visibility of a given room in the server's public room directory.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The visibility was updated, or no change was needed.",
                content = @Content(
                    schema = @Schema(
                        implementation = EmptyResponse.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "404",
                description = "The room is not known to the server.",
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
            "Room discovery"
        }
    )
    @PUT
    @Secured
    @Path("/directory/list/room/{roomId}")
    void setVisibility(
        @Parameter(
            description = "The room ID.",
            required = true
        ) @PathParam("roomId") String roomId,
        @RequestBody(
            description = "JSON body request."
        ) RoomVisibility visibility,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );

    /**
     * Lists the public rooms on the server.
     * <br>
     * This API returns paginated responses. The rooms are ordered by the number of joined members, with the largest rooms first.
     * <br>
     * Return: {@link PublicRoomsResponse}.
     * <p>Status code 200: A list of the rooms on the server.</p>
     *
     * @param limit         Limit the number of results returned.
     * @param since         A pagination token from a previous request, allowing clients to get the next (or previous) batch of rooms.
     *                      The direction of pagination is specified solely by which token is supplied, rather than via an explicit flag.
     * @param server        The server to fetch the public room lists from. Defaults to the local server.
     * @param uriInfo       Request Information.
     * @param httpHeaders   Http headers.
     * @param asyncResponse Asynchronous response.
     */
    @Operation(
        summary = "Lists the public rooms on the server. This API returns paginated responses. The rooms are ordered "
            + "by the number of joined members, with the largest rooms first.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "A list of the rooms on the server.",
                content = @Content(
                    schema = @Schema(
                        implementation = PublicRoomsResponse.class
                    )
                )
            )
        },
        tags = {
            "Room discovery"
        }
    )
    @GET
    @Path("/publicRooms")
    void showPublicRooms(
        @Parameter(
            description = "Limit the number of results returned."
        ) @QueryParam("limit") Long limit,
        @Parameter(
            description = "A pagination token from a previous request, allowing clients to get the next (or previous) batch of rooms. "
                + "The direction of pagination is specified solely by which token is supplied, rather than via an explicit flag."
        ) @QueryParam("since") String since,
        @Parameter(
            description = "The server to fetch the public room lists from. Defaults to the local server."
        ) @QueryParam("server") String server,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse
    );

    /**
     * Lists the public rooms on the server, with optional filter.
     * <br>
     * This API returns paginated responses. The rooms are ordered by the number of joined members, with the largest rooms first.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <br>
     * Return: {@link PublicRoomsResponse}.
     * <p>Status code 200: A list of the rooms on the server.</p>
     *
     * @param server             The server to fetch the public room lists from. Defaults to the local server.
     * @param publicRoomsRequest JSON body request.
     * @param uriInfo            Request Information.
     * @param httpHeaders        Http headers.
     * @param asyncResponse      Asynchronous response.
     * @param securityContext    Security context.
     */
    @Operation(
        summary = "Lists the public rooms on the server, with optional filter. This API returns paginated responses. "
            + "The rooms are ordered by the number of joined members, with the largest rooms first.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "A list of the rooms on the server.",
                content = @Content(
                    schema = @Schema(
                        implementation = PublicRoomsResponse.class
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
            "Room discovery"
        }
    )
    @POST
    @Secured
    @Path("/publicRooms")
    void searchPublicRooms(
        @Parameter(
            description = "The server to fetch the public room lists from. Defaults to the local server."
        ) @QueryParam("server") String server,

        @RequestBody(
            description = "JSON body request."
        ) PublicRoomsRequest publicRoomsRequest,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );

    /**
     * Upgrades the given room to a particular room version, migrating as much data as possible over to the new room.
     * See the room_upgrades module for more information on what this entails.
     * <br>
     * <b>Required Auth</b>: Yes.
     * <br>
     * Return: {@link ReplacementRoom}
     * <p>Status code 200: The room was successfully upgraded.</p>
     * <p>Status code 400: The request was invalid. One way this can happen is if the room version requested is not supported by
     * the homeserver.</p>
     * <p>Status code 403: The user is not permitted to upgrade the room.</p>
     *
     * @param roomId          Required. The ID of the room to upgrade.
     * @param newVersion      Required. The new version for the room.
     * @param uriInfo         Request Information.
     * @param httpHeaders     Http headers.
     * @param asyncResponse   Asynchronous response.
     * @param securityContext Security context.
     */
    @Operation(
        summary = "Upgrades the given room to a particular room version, migrating as much data as possible over to the new room."
            + " See the room_upgrades module for more information on what this entails.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The room was successfully upgraded.",
                content = @Content(
                    schema = @Schema(
                        implementation = ReplacementRoom.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "400",
                description = "The request was invalid. One way this can happen is if the room version requested is not supported by"
                    + " the homeserver.",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponse.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "403",
                description = "The user is not permitted to upgrade the room.\n",
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
            "Room directory"
        }
    )
    @POST
    @Secured
    @Path("/rooms/{roomId}/upgrade")
    void upgrade(
        @Parameter(
            description = "The ID of the room to upgrade.",
            required = true
        ) @PathParam("roomId") String roomId,
        @RequestBody(
            description = "The new version for the room.",
            required = true
        ) NewVersion newVersion,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Context AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );
}
