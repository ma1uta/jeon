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
import io.github.ma1uta.matrix.client.model.auth.LoginRequest;
import io.github.ma1uta.matrix.client.model.auth.LoginResponse;
import io.github.ma1uta.matrix.client.model.auth.SupportedLoginResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

/**
 * A client can obtain access tokens using the /login API.
 * <br>
 * Note that this endpoint does not currently use the user-interactive authentication API.
 */
@Path("/_matrix/client/r0")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface AuthApi {

    /**
     * Authentication types.
     */
    class AuthType {

        protected AuthType() {
            // singleton.
        }

        /**
         * The client submits a username and secret password, both sent in plain-text.
         */
        public static final String PASSWORD = "m.login.password";

        /**
         * The user completes a Google ReCaptcha 2.0 challenge.
         */
        public static final String RECAPTCHA = "m.login.recaptcha";

        /**
         * Authentication is supported via OAuth2 URLs. This login consists of multiple requests.
         */
        public static final String OAUTH2 = "m.login.oauth2";

        /**
         * Authentication is supported by authorising with an external single sign-on provider.
         */
        public static final String SSO = "m.login.sso";

        /**
         * Authentication is supported by authorising an email address with an identity server.
         */
        public static final String EMAIL_IDENTITY = "m.login.email.identity";

        /**
         * Authentication is supported by authorising a msisdn with an identity server.
         */
        public static final String MSISDN_IDENTITY = "m.login.msisdn";

        /**
         * The client submits a login token.
         */
        public static final String TOKEN = "m.login.token";

        /**
         * Dummy authentication always succeeds and requires no extra parameters. Its purpose is to allow servers to not require any
         * form of User-Interactive Authentication to perform a request.
         */
        public static final String DUMMY = "m.login.dummy";

        /**
         * Bypassing registration flow for application service's users.
         */
        public static final String APPLICATION_SERVICE = "m.login.application_service";
    }

    /**
     * Identifier types.
     */
    class IdentifierType {

        protected IdentifierType() {
        }

        /**
         * Matrix user id (MXID).
         */
        public static final String USER = "m.id.user";

        /**
         * Third party user id.
         */
        public static final String THIRD_PARTY = "m.id.thirdparty";

        /**
         * User phone.
         */
        public static final String PHONE = "m.id.phone";
    }

    /**
     * Gets the homeserver's supported login types to authenticate users. Clients should pick one of these and supply it as the
     * type when logging in.
     * <br>
     * <b>Rate-limited</b>: Yes.
     * <br>
     * Return: {@link SupportedLoginResponse}.
     * <p>Status code 200: The login types the homeserver supports.</p>
     * <p>Status code 429: This request was rate-limited.</p>
     *
     * @param uriInfo       Request Information.
     * @param httpHeaders   Http headers.
     * @param asyncResponse Asynchronous response.
     */
    @Operation(
        summary = "Gets the homeserver's supported login types to authenticate users.",
        description = "Clients should pick one of these and supply it as the type when logging in.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The login types the homeserver supports.",
                content = @Content(
                    schema = @Schema(
                        implementation = SupportedLoginResponse.class
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
        tags = {
            "Session management"
        }
    )
    @GET
    @RateLimit
    @Path("/login")
    void supportedLoginTypes(
        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse
    );

    /**
     * Authenticates the user, and issues an access token they can use to authorize themself in subsequent requests.
     * <br>
     * If the client does not supply a device_id, the server must auto-generate one.
     * <br>
     * The returned access token must be associated with the device_id supplied by the client or generated by the server.
     * The server may invalidate any access token previously associated with that device.
     * <br>
     * <b>Rate-limited</b>: Yes.
     * <br>
     * Return: {@link LoginResponse}.
     * <p>Status code 200: The user has been authenticated.</p>
     * <p>Status code 400: Part of the request was invalid. For example, the login type may not be recognised.</p>
     * <p>Status code 401: The login attempt failed. For example, the password may have been incorrect.</p>
     * <p>Status code 429: This request was rate-limited.</p>
     *
     * @param loginRequest  JSON body request.
     * @param uriInfo       Request Information.
     * @param httpHeaders   Http headers.
     * @param asyncResponse Asynchronous response.
     */
    @Operation(
        summary = "Authenticates the user, and issues an access token they can use to authorize themself in subsequent requests",
        description = "If the client does not supply a device_id, the server must auto-generate one. "
            + "The returned access token must be associated with the device_id supplied by the client or generated by the server. "
            + "The server may invalidate any access token previously associated with that device",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The user has been authenticated",
                content = @Content(
                    schema = @Schema(
                        implementation = LoginResponse.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Part of the request was invalid. For example, the login type may not be recognised",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponse.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "401",
                description = "The login attempt failed. For example, the password may have been incorrect.",
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
        tags = {
            "Session management"
        }
    )
    @POST
    @RateLimit
    @Path("/login")
    void login(
        @RequestBody(
            description = "login request."
        ) LoginRequest loginRequest,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse
    );

    /**
     * Invalidates an existing access token, so that it can no longer be used for authorization.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <br>
     * The access token used in the request was successfully invalidated.
     * <br>
     * Return: {@link EmptyResponse}.
     * <p>Status code 200: The access token used in the request was succesfully invalidated.</p>
     *
     * @param uriInfo         Request Information.
     * @param httpHeaders     Http headers.
     * @param asyncResponse   Asynchronous response.
     * @param securityContext Security context.
     */
    @Operation(
        summary = "Invalidates an existing access token, so that it can no longer be used for authorization.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The access token used in the request was succesfully invalidated",
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
            "Session management"
        }
    )
    @POST
    @Secured
    @Path("/logout")
    void logout(
        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );

    /**
     * Invalidates all access tokens for a user, so that they can no longer be used for authorization. This includes the access token
     * that made this request.
     * <br>
     * This endpoint does not require UI authorization because UI authorization is designed to protect against attacks where the
     * someone gets hold of a single access token then takes over the account. This endpoint invalidates all access tokens for the
     * user, including the token used in the request, and therefore the attacker is unable to take over the account in this way.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <br>
     * Return: {@link EmptyResponse}.
     * <p>Status code 200: The user's access tokens were succesfully invalidated.</p>
     *
     * @param uriInfo         Request Information.
     * @param httpHeaders     Http headers.
     * @param asyncResponse   Asynchronous response.
     * @param securityContext Security context.
     */
    @Operation(
        summary = "Invalidates all access tokens for a user, so that they can no longer be used for authorization. "
            + "This includes the access token that made this request",
        description = "This endpoint does not require UI authorization because UI authorization is designed to protect against attacks"
            + " where the someone gets hold of a single access token then takes over the account. This endpoint invalidates all access"
            + " tokens for the user, including the token used in the request, and therefore the attacker is unable to take over"
            + " the account in this way.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The user's access tokens were succesfully invalidated",
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
            "Session management"
        }
    )
    @POST
    @Secured
    @Path("/logout/all")
    void logoutAll(
        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );
}
