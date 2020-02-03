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
import io.github.ma1uta.matrix.Secured;
import io.github.ma1uta.matrix.client.model.tag.Tags;
import io.github.ma1uta.matrix.event.nested.TagInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

/**
 * Users can add tags to rooms. Tags are short strings used to label rooms, e.g. "work", "family". A room may have multiple tags.
 * Tags are only visible to the user that set them but are shared across all their devices.
 */
@Path("/_matrix/client/r0/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface TagApi {

    /**
     * TagInfo special names.
     */
    class Tag {

        protected Tag() {
        }

        /**
         * Favourite.
         */
        public static final String FAVOURITE = "m.favourite";

        /**
         * Low priority.
         */
        public static final String LOWPRIOORITY = "m.lowpriority";
    }

    /**
     * List the tags set by a user on a room.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <br>
     * Return: {@link Tags}.
     * <p>Status code 200: The list of tags for the user for the room.</p>
     *
     * @param userId          Required. The id of the user to get tags for. The access token must be authorized to make requests for this
     *                        user id.
     * @param roomId          Required. The id of the room to get tags for.
     * @param uriInfo         Request Information.
     * @param httpHeaders     Http headers.
     * @param asyncResponse   Asynchronous response.
     * @param securityContext Security context.
     */
    @Operation(
        summary = "List the tags set by a user on a room.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The list of tags for the user for the room.",
                content = @Content(
                    schema = @Schema(
                        implementation = Tags.class
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
            "User data"
        }
    )
    @GET
    @Secured
    @Path("/{userId}/rooms/{roomId}/tags")
    void showTags(
        @Parameter(
            description = "The id of the user to get tags for. The access token must be authorized to make requests for this user id.",
            required = true
        ) @PathParam("userId") String userId,
        @Parameter(
            description = "The id of the room to get tags for.",
            required = true
        ) @PathParam("roomId") String roomId,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );

    /**
     * Add a tag to the room.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <br>
     * Return: {@link EmptyResponse}.
     * <p>Status code 200: The tag was successfully added.</p>
     *
     * @param userId          Required. The id of the user to add a tag for. The access token must be authorized to make requests for this
     *                        user id.
     * @param roomId          Required. The id of the room to add a tag to.
     * @param tag             Required. The tag to add.
     * @param tagData         tag data.
     * @param uriInfo         Request Information.
     * @param httpHeaders     Http headers.
     * @param asyncResponse   Asynchronous response.
     * @param securityContext Security context.
     */
    @Operation(
        summary = "Add a tag to the room.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The tag was successfully added.",
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
            "User data"
        }
    )
    @PUT
    @Secured
    @Path("/{userId}/rooms/{roomId}/tags/{tag}")
    void addTag(
        @Parameter(
            description = "The id of the user to add a tag for. The access token must be authorized to make requests for this user id.",
            required = true
        ) @PathParam("userId") String userId,
        @Parameter(
            description = "The id of the room to add a tag to.",
            required = true
        ) @PathParam("roomId") String roomId,
        @Parameter(
            description = "The tag to add.",
            required = true
        ) @PathParam("tag") String tag,
        @RequestBody(
            description = "TagInfo data."
        ) TagInfo tagData,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );

    /**
     * Remove a tag from the room.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <br>
     * Return: {@link EmptyResponse}.
     * <p>Status code 200: The tag was successfully removed.</p>
     *
     * @param userId          Required. The id of the user to remove a tag for. The access token must be authorized to make requests
     *                        for this user id.
     * @param roomId          Required. The id of the room to remove a tag from.
     * @param tag             Required. The tag to remove.
     * @param uriInfo         Request Information.
     * @param httpHeaders     Http headers.
     * @param asyncResponse   Asynchronous response.
     * @param securityContext Security context.
     */
    @Operation(
        summary = "Remove a tag from the room.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The tag was successfully removed.",
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
            "User data"
        }
    )
    @DELETE
    @Secured
    @Path("/{userId}/rooms/{roomId}/tags/{tag}")
    void deleteTag(
        @Parameter(
            description = "The id of the user to remove a tag for. The access token must be authorized to make requests for this user id.",
            required = true
        ) @PathParam("userId") String userId,
        @Parameter(
            description = "The id of the room to remove a tag from.",
            required = true
        ) @PathParam("roomId") String roomId,
        @Parameter(
            description = "The tag to remove.",
            required = true
        ) @PathParam("tag") String tag,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );
}
