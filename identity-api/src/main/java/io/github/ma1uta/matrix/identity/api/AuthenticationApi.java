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

package io.github.ma1uta.matrix.identity.api;

import io.github.ma1uta.matrix.common.EmptyResponse;
import io.github.ma1uta.matrix.common.ErrorResponse;
import io.github.ma1uta.matrix.identity.model.authentication.OpenIdInfo;
import io.github.ma1uta.matrix.identity.model.authentication.OpenIdRequest;
import io.github.ma1uta.matrix.identity.model.authentication.Token;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

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
 * Most v2 endpoints in the Identity Service API require authentication in order to ensure that the requesting user has accepted
 * all relevant policies and is otherwise permitted to make the request. The v1 API (currently deprecated) does not require
 * this authentication, however using v1 is strongly discouraged as it will be removed in a future release.
 * <br>
 * Identity Servers use a scheme similar to the Client-Server API's concept of access tokens to authenticate users.
 * The access tokens provided by an Identity Server cannot be used to authenticate Client-Server API requests.
 * <br>
 * An access token is provided to an endpoint in one of two ways:
 * <ul>
 *     <li>Via a query string parameter, access_token=TheTokenHere.</li>
 *     <li>Via a request header, Authorization: Bearer TheTokenHere.</li>
 * </ul>
 * <br>
 * Clients are encouraged to the use the Authorization header where possible to prevent the access token being leaked in access/HTTP logs.
 * The query string should only be used in cases where the Authorization header is inaccessible for the client.
 * <br>
 * When credentials are required but missing or invalid, the HTTP call will return with a status of 401 and the error code M_UNAUTHORIZED.
 */
@Path("/_matrix/identity/v2/account")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface AuthenticationApi {

    /**
     * Exchanges an OpenID token from the homeserver for an access token to access the identity server.
     * The request body is the same as the values returned by /openid/request_token in the Client-Server API.
     * <br>
     * Return: {@link Token}.
     * <p>Status code 200: A token which can be used to authenticate future requests to the identity server.</p>
     *
     * @param request     JSON body request.
     * @param uriInfo     Request information.
     * @param httpHeaders Http headers.
     * @param response    Asynchronous response.
     */
    @Operation(
        summary = "Exchanges an OpenID token from the homeserver for an access token to access the identity server.",
        description = "The request body is the same as the values returned by /openid/request_token in the Client-Server API.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "A token which can be used to authenticate future requests to the identity server.",
                content = @Content(
                    schema = @Schema(
                        implementation = Token.class
                    )
                )
            )
        }
    )
    @POST
    @Path("/register")
    void register(
        @RequestBody(
            description = "JSON body request"
        ) OpenIdRequest request,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse response
    );

    /**
     * Gets information about what user owns the access token used in the request.
     * <br>
     * <b>Requires auth</b>:Yes.
     * <br>
     * Return: {@link OpenIdInfo}.
     * <p>Status code 200: The token holder's information.</p>
     * <p>Status code 403: The user must do something in order to use this endpoint. One example is an M_TERMS_NOT_SIGNED error
     * where the user must agree to more terms.</p>
     *
     * @param uriInfo         Request information.
     * @param httpHeaders     Http headers.
     * @param response        Asynchronous response.
     * @param securityContext Security context.
     */
    @Operation(
        summary = "Gets information about what user owns the access token used in the request.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The token holder's information.",
                content = @Content(
                    schema = @Schema(
                        implementation = OpenIdInfo.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "403",
                description = "The user must do something in order to use this endpoint. One example is an M_TERMS_NOT_SIGNED "
                    + "error where the user must agree to more terms.",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponse.class
                    )
                )
            )
        }
    )
    @GET
    @Path("")
    void getInfo(
        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse response,
        @Context SecurityContext securityContext
    );

    /**
     * Logs out the access token, preventing it from being used to authenticate future requests to the server.
     * <br>
     * <b>Requires auth</b>:Yes.
     * <br>
     * Return: {@link EmptyResponse}.
     * <p>Status code 200: The token was successfully logged out.</p>
     * <p>Status code 401: The token is not registered or is otherwise unknown to the server.</p>
     * <p>Status code 403: The user must do something in order to use this endpoint. One example is an M_TERMS_NOT_SIGNED error
     * where the user must agree to more terms.</p>
     *
     * @param uriInfo         Request information.
     * @param httpHeaders     Http headers.
     * @param response        Asynchronous response.
     * @param securityContext Security context.
     */
    @Operation(
        summary = "Logs out the access token, preventing it from being used to authenticate future requests to the server.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The token was successfully logged out.",
                content = @Content(
                    schema = @Schema(
                        implementation = EmptyResponse.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "401",
                description = "The token is not registered or is otherwise unknown to the server.",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponse.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "403",
                description = "The user must do something in order to use this endpoint. One example is an M_TERMS_NOT_SIGNED error "
                    + "where the user must agree to more terms.",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponse.class
                    )
                )
            )
        }
    )
    @POST
    @Path("")
    void logout(
        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse response,
        @Context SecurityContext securityContext
    );
}
