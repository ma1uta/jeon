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

import static io.github.ma1uta.matrix.client.api.ProfileApi.PATH;

import io.github.ma1uta.matrix.EmptyResponse;
import io.github.ma1uta.matrix.RateLimit;
import io.github.ma1uta.matrix.Secured;
import io.github.ma1uta.matrix.client.model.profile.AvatarUrl;
import io.github.ma1uta.matrix.client.model.profile.DisplayName;
import io.github.ma1uta.matrix.client.model.profile.Profile;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

/**
 * Profiles.
 * <p/>
 * <a href="https://matrix.org/docs/spec/client_server/r0.3.0.html#id213">Specification.</a>
 */
@Api(value = PATH, description = "Profiles.")
@Path(PATH)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface ProfileApi {

    /**
     * Profile api url.
     */
    String PATH = "/_matrix/client/r0/profile";

    /**
     * This API sets the given user's display name. You must have permission to set this user's display name, e.g. you need to
     * have their access_token.
     * <p/>
     * <b>Rate-limited</b>: Yes.
     * <p/>
     * <b>Requires auth</b>: Yes.
     *
     * @param userId          Required. The user whose display name to set.
     * @param displayName     JSON body request.
     * @param servletRequest  servlet request.
     * @param servletResponse servlet response.
     * @param securityContext security context.
     * @return Status code 200: The display name was set.
     *     Status code 429: This request was rate-limited.
     */
    @ApiOperation(value = "his API sets the given user's display name. You must have permission to set this user's display "
        + "name, e.g. you need to have their access_token.", response = EmptyResponse.class)
    @ApiResponses( {
        @ApiResponse(code = 200, message = "The display name was set."),
        @ApiResponse(code = 429, message = "This request was rate-limited.")
    })
    @PUT
    @RateLimit
    @Secured
    @Path("/{userId}/displayname")
    EmptyResponse setDisplayName(
        @ApiParam(value = "The user whose display name to set.", required = true) @PathParam("userId") String userId,
        @ApiParam("JSON body request.") DisplayName displayName,
        @Context HttpServletRequest servletRequest, @Context HttpServletResponse servletResponse, @Context SecurityContext securityContext);

    /**
     * Get the user's display name. This API may be used to fetch the user's own displayname or to query the name of other users; either
     * locally or on remote homeservers.
     *
     * @param userId          Required. The user whose display name to get.
     * @param servletRequest  servlet request.
     * @param servletResponse servlet response.
     * @return Status code 200: The display name for this user.
     *     Status code 404: There is no display name for this user or this user does not exist.
     */
    @ApiOperation(value = "Get the user's display name. This API may be used to fetch the user's own displayname or to "
        + "query the name of other users; either locally or on remote homeservers.", response = DisplayName.class)
    @ApiResponses( {
        @ApiResponse(code = 200, message = "The display name for this user."),
        @ApiResponse(code = 404, message = "There is no display name for this user or this user does not exist.")
    })
    @GET
    @Path("/{userId}/displayname")
    DisplayName showDisplayName(
        @ApiParam(value = "The user whose display name to get.", required = true) @PathParam("userId") String userId,
        @Context HttpServletRequest servletRequest, @Context HttpServletResponse servletResponse);

    /**
     * This API sets the given user's avatar URL. You must have permission to set this user's avatar URL, e.g. you need to have
     * their access_token.
     * <p/>
     * <b>Rate-limited</b>: Yes.
     * <p/>
     * <b>Requires auth</b>: Yes.
     *
     * @param userId          Required. The user whose avatar URL to set.
     * @param avatarUrl       JSON body request.
     * @param servletRequest  servlet request.
     * @param servletResponse servlet response.
     * @param securityContext security context.
     * @return Status code 200: The avatar URL was set.
     *     Status code 429: This request was rate-limited.
     */
    @ApiOperation(value = "This API sets the given user's avatar URL. You must have permission to set this user's avatar "
        + "URL, e.g. you need to have their access_token.", response = EmptyResponse.class)
    @ApiResponses( {
        @ApiResponse(code = 200, message = "The avatar URL was set."),
        @ApiResponse(code = 429, message = "This request was rate-limited.")
    })
    @PUT
    @RateLimit
    @Secured
    @Path("/{userId}/avatar_url")
    EmptyResponse setAvatarUrl(
        @ApiParam(value = "The user whose avatar URL to set.", required = true) @PathParam("userId") String userId,
        @ApiParam("JSON body request") AvatarUrl avatarUrl,
        @Context HttpServletRequest servletRequest, @Context HttpServletResponse servletResponse, @Context SecurityContext securityContext);

    /**
     * Get the user's avatar URL. This API may be used to fetch the user's own avatar URL or to query the URL of other users;
     * either locally or on remote homeservers.
     *
     * @param userId          Required. The user whose avatar URL to get.
     * @param servletRequest  servlet request.
     * @param servletResponse servlet response.
     * @return Status code 200: The avatar URL for this user.
     *     Status code 404: There is no avatar URL for this user or this user does not exist.
     */
    @ApiOperation(value = "Get the user's avatar URL. This API may be used to fetch the user's own avatar URL or to query "
        + "the URL of other users;  either locally or on remote homeservers.", response = AvatarUrl.class)
    @ApiResponses( {
        @ApiResponse(code = 200, message = "The avatar URL for this user."),
        @ApiResponse(code = 404, message = "There is no avatar URL for this user or this user does not exist.")
    })
    @GET
    @Path("/{userId}/avatar_url")
    AvatarUrl showAvatarUrl(
        @ApiParam(value = "The user whose avatar URL to get.", required = true) @PathParam("userId") String userId,
        @Context HttpServletRequest servletRequest, @Context HttpServletResponse servletResponse);

    /**
     * Get the combined profile information for this user. This API may be used to fetch the user's own profile information or
     * other users; either locally or on remote homeservers. This API may return keys which are not limited to displayname or avatar_url.
     *
     * @param userId          Required. The user whose profile information to get.
     * @param servletRequest  servlet request.
     * @param servletResponse servlet response.
     * @return Status code 200: The avatar URL for this user.
     *     Status code 404: There is no profile information for this user or this user does not exist.
     */
    @ApiOperation(value = "Get the combined profile information for this user. This API may be used to fetch the user's own "
        + "profile information or other users; either locally or on remote homeservers. This API may return keys which are "
        + "not limited to displayname or avatar_url.", response = Profile.class)
    @ApiResponses({
        @ApiResponse(code = 200, message = "The avatar URL for this user."),
        @ApiResponse(code = 404, message = "There is no profile information for this user or this user does not exist.")
    })
    @GET
    @Path("/{userId}")
    Profile profile(
        @ApiParam(value = "The user whose profile information to get.", required = true) @PathParam("userId") String userId,
        @Context HttpServletRequest servletRequest, @Context HttpServletResponse servletResponse);
}
