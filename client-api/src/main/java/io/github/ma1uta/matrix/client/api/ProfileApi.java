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
import io.github.ma1uta.matrix.client.model.profile.AvatarUrl;
import io.github.ma1uta.matrix.client.model.profile.DisplayName;
import io.github.ma1uta.matrix.client.model.profile.Profile;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import javax.ws.rs.Consumes;
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
 * Profiles.
 */
@Path("/_matrix/client/r0/profile")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface ProfileApi {

    /**
     * This API sets the given user's display name. You must have permission to set this user's display name, e.g. you need to
     * have their access_token.
     * <br>
     * <b>Rate-limited</b>: Yes.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <br>
     * Return: {@link EmptyResponse}.
     * <p>Status code 200: The display name was set.</p>
     * <p>Status code 429: This request was rate-limited.</p>
     *
     * @param userId          Required. The user whose display name to set.
     * @param displayName     JSON body request.
     * @param uriInfo         Request Information.
     * @param httpHeaders     Http headers.
     * @param asyncResponse   Asynchronous response.
     * @param securityContext Security context.
     */
    @Operation(
        summary = "his API sets the given user's display name. You must have permission to set this user's display "
            + "name, e.g. you need to have their access_token.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The display name was set.",
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
            "User data"
        }
    )
    @PUT
    @RateLimit
    @Secured
    @Path("/{userId}/displayname")
    void setDisplayName(
        @Parameter(
            description = "The user whose display name to set.",
            required = true
        ) @PathParam("userId") String userId,
        @RequestBody(
            description = "JSON body request."
        ) DisplayName displayName,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );

    /**
     * Get the user's display name. This API may be used to fetch the user's own displayname or to query the name of other users; either
     * locally or on remote homeservers.
     * <br>
     * Return: {@link DisplayName}.
     * <p>Status code 200: The display name for this user.</p>
     * <p>Status code 404: There is no display name for this user or this user does not exist.</p>
     *
     * @param userId        Required. The user whose display name to get.
     * @param uriInfo       Request Information.
     * @param httpHeaders   Http headers.
     * @param asyncResponse Asynchronous response.
     */
    @Operation(
        summary = "Get the user's display name. This API may be used to fetch the user's own displayname or to "
            + "query the name of other users; either locally or on remote homeservers.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The display name for this user.",
                content = @Content(
                    schema = @Schema(
                        implementation = DisplayName.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "404",
                description = "There is no display name for this user or this user does not exist.",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponse.class
                    )
                )
            )
        },
        tags = {
            "User data"
        }
    )
    @GET
    @Path("/{userId}/displayname")
    void showDisplayName(
        @Parameter(
            description = "The user whose display name to get.",
            required = true
        ) @PathParam("userId") String userId,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse
    );

    /**
     * This API sets the given user's avatar URL. You must have permission to set this user's avatar URL, e.g. you need to have
     * their access_token.
     * <br>
     * <b>Rate-limited</b>: Yes.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <br>
     * Return: {@link EmptyResponse}.
     * <p>Status code 200: The avatar URL was set.</p>
     * <p>Status code 429: This request was rate-limited.</p>
     *
     * @param userId          Required. The user whose avatar URL to set.
     * @param avatarUrl       JSON body request.
     * @param uriInfo         Request Information.
     * @param httpHeaders     Http headers.
     * @param asyncResponse   Asynchronous response.
     * @param securityContext Security context.
     */
    @Operation(
        summary = "This API sets the given user's avatar URL. You must have permission to set this user's avatar "
            + "URL, e.g. you need to have their access_token.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The avatar URL was set.",
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
            "User data"
        }
    )
    @PUT
    @RateLimit
    @Secured
    @Path("/{userId}/avatar_url")
    void setAvatarUrl(
        @Parameter(
            description = "The user whose avatar URL to set.",
            required = true
        ) @PathParam("userId") String userId,
        @RequestBody(
            description = "JSON body request"
        ) AvatarUrl avatarUrl,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );

    /**
     * Get the user's avatar URL. This API may be used to fetch the user's own avatar URL or to query the URL of other users;
     * either locally or on remote homeservers.
     * <br>
     * Return: {@link AvatarUrl}.
     * <p>Status code 200: The avatar URL for this user.</p>
     * <p>Status code 404: There is no avatar URL for this user or this user does not exist.</p>
     *
     * @param userId        Required. The user whose avatar URL to get.
     * @param uriInfo       Request Information.
     * @param httpHeaders   Http headers.
     * @param asyncResponse Asynchronous response.
     */
    @Operation(
        summary = "Get the user's avatar URL. This API may be used to fetch the user's own avatar URL or to query "
            + "the URL of other users;  either locally or on remote homeservers.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The avatar URL for this user.",
                content = @Content(
                    schema = @Schema(
                        implementation = AvatarUrl.class
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
        tags = {
            "User data"
        }
    )
    @GET
    @Path("/{userId}/avatar_url")
    void showAvatarUrl(
        @Parameter(
            description = "The user whose avatar URL to get.",
            required = true
        ) @PathParam("userId") String userId,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse
    );

    /**
     * Get the combined profile information for this user. This API may be used to fetch the user's own profile information or
     * other users; either locally or on remote homeservers. This API may return keys which are not limited to displayname or avatar_url.
     * <br>
     * Return: {@link Profile}.
     * <p>Status code 200: The avatar URL for this user.</p>
     * <p>Status code 404: There is no profile information for this user or this user does not exist.</p>
     *
     * @param userId        Required. The user whose profile information to get.
     * @param uriInfo       Request Information.
     * @param httpHeaders   Http headers.
     * @param asyncResponse Asynchronous response.
     */
    @Operation(
        summary = "Get the combined profile information for this user. This API may be used to fetch the user's own "
            + "profile information or other users; either locally or on remote homeservers. This API may return keys which are "
            + "not limited to displayname or avatar_url.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The avatar URL for this user.",
                content = @Content(
                    schema = @Schema(
                        implementation = Profile.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "404",
                description = "There is no profile information for this user or this user does not exist.",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponse.class
                    )
                )
            )
        },
        tags = {
            "User data"
        }
    )
    @GET
    @Path("/{userId}")
    void profile(
        @Parameter(
            description = "The user whose profile information to get.",
            required = true
        ) @PathParam("userId") String userId,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse
    );
}
