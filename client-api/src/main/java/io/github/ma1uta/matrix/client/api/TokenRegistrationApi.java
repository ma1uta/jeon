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

import io.github.ma1uta.matrix.client.model.auth.RefreshTokenRequest;
import io.github.ma1uta.matrix.client.model.auth.RefreshTokenResponse;
import io.github.ma1uta.matrix.client.model.auth.SupportedLoginResponse;
import io.github.ma1uta.matrix.client.model.auth.TokenValidationResponse;
import io.github.ma1uta.matrix.common.RateLimit;
import io.github.ma1uta.matrix.common.RateLimitedErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 * To determine if a token is valid before attempting to use it, the client can use the /validity API defined below.
 * The API doesn’t guarantee that a token will be valid when used, but does avoid cases where the user finds out late
 * in the registration process that their token has expired.
 */
@Path("/_matrix/client")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface TokenRegistrationApi {

    /**
     * Queries the server to determine if a given registration token is still valid at the time of request.
     * This is a point-in-time check where the token might still expire by the time it is used.
     * <br>
     * <b>Rate-limited</b>: Yes.
     * <br>
     * Return: {@link SupportedLoginResponse}.
     * <p>Status code 200: The login types the homeserver supports.</p>
     * <p>Status code 429: This request was rate-limited.</p>
     *
     * @param token         token to check.
     * @param uriInfo       Request Information.
     * @param httpHeaders   Http headers.
     * @param asyncResponse Asynchronous response.
     */
    @Operation(
        summary = "Queries the server to determine if a given registration token is still valid at the time of request.",
        description = "This is a point-in-time check where the token might still expire by the time it is used.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The check has a result..",
                content = @Content(
                    schema = @Schema(
                        implementation = TokenValidationResponse.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "403",
                description = "The homeserver does not permit registration and thus all tokens are considered invalid."
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
        tags = {
            "Session management"
        }
    )
    @GET
    @RateLimit
    @Path("/v1/register/m.login.registration_token/validity")
    void checkToken(
        @Parameter(
            description = "The token to check validity of.",
            required = true
        ) @QueryParam("token") String token,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse
    );

    /**
     * Refresh an access token. Clients should use the returned access token when making subsequent API calls, and store
     * the returned refresh token (if given) in order to refresh the new access token when necessary.
     * <br/>
     * After an access token has been refreshed, a server can choose to invalidate the old access token immediately,
     * or can choose not to, for example if the access token would expire soon anyways.
     * <br/>
     * Clients should not make any assumptions about the old access token still being valid, and should use the newly
     * provided access token instead.
     * <br/>
     * The old refresh token remains valid until the new access token or refresh token is used, at which point the old refresh
     * token is revoked.
     * <br/>
     * Note that this endpoint does not require authentication via an access token. Authentication is provided via the refresh token.
     * Application Service identity assertion is disabled for this endpoint.
     * <br>
     * <b>Rate-limited</b>: Yes.
     * <br>
     * Return: {@link RefreshTokenResponse}.
     * <p>Status code 200: A new access token and refresh token were generated.</p>
     * <p>Status code 401: The provided token was unknown, or has already been used.</p>
     * <p>Status code 429: This request was rate-limited.</p>
     *
     * @param request       Refresh token request.
     * @param uriInfo       Request Information.
     * @param httpHeaders   Http headers.
     * @param asyncResponse Asynchronous response.
     */
    @Operation(
        summary = "Refresh an access token.",
        description = "Clients should use the returned access token when making subsequent API calls,"
            + "and store the returned refresh token (if given) in order to refresh the new access token when necessary.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "A new access token and refresh token were generated.",
                content = @Content(
                    schema = @Schema(
                        implementation = RefreshTokenResponse.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "401",
                description = "The provided token was unknown, or has already been used."
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
        }
    )
    @POST
    @Path("/v3/refresh")
    void refreshToken(
        @RequestBody(
            description = "request to refresh token."
        ) RefreshTokenRequest request,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse
    );
}
