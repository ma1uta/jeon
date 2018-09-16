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
import io.github.ma1uta.matrix.Secured;
import io.github.ma1uta.matrix.client.model.tag.Tags;
import io.github.ma1uta.matrix.events.nested.TagInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.servlet.http.HttpServletRequest;
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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

/**
 * Users can add tags to rooms. Tags are short strings used to label rooms, e.g. "work", "family". A room may have multiple tags.
 * Tags are only visible to the user that set them but are shared across all their devices.
 */
@Api(
    value = "Tag",
    description = "Users can add tags to rooms. Tags are short strings used to label rooms, e.g. \"work\", \"family\". "
        + "A room may have multiple tags. Tags are only visible to the user that set them but are shared across all their devices."
)
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
     * @param servletRequest  Servlet request.
     * @param asyncResponse   Asynchronous response.
     * @param securityContext Security context.
     */
    @ApiOperation(
        value = "List the tags set by a user on a room.",
        response = Tags.class
    )
    @ApiResponses( {
        @ApiResponse(code = 200, message = "The list of tags for the user for the room.")
    })
    @GET
    @Secured
    @Path("/{userId}/rooms/{roomId}/tags")
    void showTags(
        @ApiParam(
            value = "The id of the user to get tags for. The access token must be authorized to make requests for this user id.",
            required = true
        ) @PathParam("userId") String userId,
        @ApiParam(
            value = "The id of the room to get tags for.",
            required = true
        ) @PathParam("roomId") String roomId,

        @Context HttpServletRequest servletRequest,
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
     * @param servletRequest  Servlet request.
     * @param asyncResponse   Asynchronous response.
     * @param securityContext Security context.
     */
    @ApiOperation(
        value = "Add a tag to the room.",
        response = EmptyResponse.class
    )
    @ApiResponses( {
        @ApiResponse(code = 200, message = "The tag was successfully added.")
    })
    @PUT
    @Secured
    @Path("/{userId}/rooms/{roomId}/tags/{tag}")
    void addTag(
        @ApiParam(
            value = "The id of the user to add a tag for. The access token must be authorized to make requests for this user id.",
            required = true
        ) @PathParam("userId") String userId,
        @ApiParam(
            value = "The id of the room to add a tag to.",
            required = true
        ) @PathParam("roomId") String roomId,
        @ApiParam(
            value = "The tag to add.",
            required = true
        ) @PathParam("tag") String tag,
        @ApiParam(
            value = "TagInfo data."
        ) TagInfo tagData,

        @Context HttpServletRequest servletRequest,
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
     * @param servletRequest  Servlet request.
     * @param asyncResponse   Asynchronous response.
     * @param securityContext Security context.
     */
    @ApiOperation(
        value = "Remove a tag from the room.",
        response = EmptyResponse.class
    )
    @ApiResponses( {
        @ApiResponse(code = 200, message = "The tag was successfully removed.")
    })
    @DELETE
    @Secured
    @Path("/{userId}/rooms/{roomId}/tags/{tag}")
    void deleteTag(
        @ApiParam(
            value = "The id of the user to remove a tag for. The access token must be authorized to make requests for this user id.",
            required = true
        ) @PathParam("userId") String userId,
        @ApiParam(
            value = "The id of the room to remove a tag from.",
            required = true
        ) @PathParam("roomId") String roomId,
        @ApiParam(
            value = "The tag to remove.",
            required = true
        ) @PathParam("tag") String tag,

        @Context HttpServletRequest servletRequest,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );
}
