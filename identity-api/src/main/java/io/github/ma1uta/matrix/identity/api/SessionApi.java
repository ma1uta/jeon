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

import io.github.ma1uta.matrix.EmptyResponse;
import io.github.ma1uta.matrix.ErrorResponse;
import io.github.ma1uta.matrix.identity.model.associations.ValidationResponse;
import io.github.ma1uta.matrix.identity.model.session.EmailRequestToken;
import io.github.ma1uta.matrix.identity.model.session.PhoneRequestToken;
import io.github.ma1uta.matrix.identity.model.session.SubmitToken;
import io.github.ma1uta.matrix.thirdpid.SessionResponse;
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
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

/**
 * The flow for creating an association is session-based.
 */
@Path("/_matrix/identity/v2")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface SessionApi {

    /**
     * Create a session for validating an email address.
     * <br>
     * The identity server will send an email containing a token. If that token is presented to the identity server in the future,
     * it indicates that that user was able to read the email for that email address, and so we validate ownership of the email address.
     * <br>
     * Note that homeservers offer APIs that proxy this API, adding additional behaviour on top, for example,
     * /register/email/requestToken is designed specifically for use when registering an account and therefore will inform the user
     * if the email address given is already registered on the server.
     * <br>
     * <b>Requires auth</b>:Yes.
     * <br>
     * Return: {@link SessionResponse}.
     * <p> Status code 200: Session created.</p>
     * <p>Status code 400: An error ocurred. Some possible errors are:</p>
     * <ul>
     * <li>M_INVALID_EMAIL: The email address provided was invalid.</li>
     * <li>M_EMAIL_SEND_ERROR: The validation email could not be sent.</li>
     * </ul>
     *
     * @param request         JSON body request.
     * @param uriInfo         Request Information.
     * @param httpHeaders     Http headers.
     * @param asyncResponse   Asynchronous response.
     * @param securityContext Security context.
     */
    @Operation(
        summary = "Create a session for validating an email address.",
        description = "The identity server will send an email containing a token. If that token is presented to the identity server in"
            + " the future, it indicates that that user was able to read the email for that email address, and so we validate ownership"
            + " of the email address.\nNote that homeservers offer APIs that proxy this API, adding additional behaviour on top,"
            + " for example,/register/email/requestToken is designed specifically for use when registering an account and therefore will"
            + " inform the user if the email address given is already registered on the server.",
        responses = {
            @ApiResponse(
                content = @Content(
                    schema = @Schema(
                        implementation = SessionResponse.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "200",
                description = "Session created.",
                content = @Content(
                    schema = @Schema(
                        implementation = SessionResponse.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "400",
                description = "An error ocurred.",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponse.class
                    )
                )
            )
        }
    )
    @POST
    @Path("/validate/email/requestToken")
    void createEmailSession(
        @RequestBody(
            description = "JSON body request."
        ) EmailRequestToken request,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );

    /**
     * Validate ownership of an email address.
     * <br>
     * If the three parameters are consistent with a set generated by a requestToken call, ownership of the email address is
     * considered to have been validated. This does not publish any information publicly, or associate the email address with any
     * Matrix user ID. Specifically, calls to /lookup will not show a binding.
     * <br>
     * The identity server is free to match the token case-insensitively, or carry out other mapping operations such as unicode
     * normalisation. Whether to do so is an implementation detail for the identity server. Clients must always pass on the token
     * without modification.
     * <br>
     * <b>Requires auth</b>:Yes.
     * <br>
     * Return: {@link ValidationResponse}.
     * <p>Status code 200: The success of the validation.</p>
     *
     * @param request         JSON body request.
     * @param uriInfo         Request Information.
     * @param httpHeaders     Http headers.
     * @param asyncResponse   Asynchronous response.
     * @param securityContext Security context.
     */
    @Operation(
        summary = "Validate ownership of an email address.",
        description = "If the three parameters are consistent with a set generated by a requestToken call, ownership of the email address"
            + " is considered to have been validated. This does not publish any information publicly, or associate the email address with"
            + " any Matrix user ID. Specifically, calls to /lookup will not show a binding.\nThe identity server is free to match the token"
            + " case-insensitively, or carry out other mapping operations such as unicode normalisation. Whether to do so is"
            + " an implementation detail for the identity server. Clients must always pass on the token without modification.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The success of the validation.",
                content = @Content(
                    schema = @Schema(
                        implementation = ValidationResponse.class
                    )
                )
            )
        }
    )
    @POST
    @Path("/validate/email/submitToken")
    void postValidateEmail(
        @RequestBody(
            description = "JSON body request.",
            required = true
        ) SubmitToken request,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );

    /**
     * Validate ownership of an email address.
     * <br>
     * If the three parameters are consistent with a set generated by a requestToken call, ownership of the email address is
     * considered to have been validated. This does not publish any information publicly, or associate the email address with
     * any Matrix user ID. Specifically, calls to /lookup will not show a binding.
     * <br>
     * Note that, in contrast with the POST version, this endpoint will be used by end-users, and so the response should be human-readable.
     * <br>
     * <b>Requires auth</b>:Yes.
     * <br>
     * Return: {@link EmptyResponse}.
     * <p>Status code 200: Email address is validated.</p>
     * <p>Status code 3xx: Email address is validated, and the next_link parameter was provided to the requestToken call.
     * The user must be redirected to the URL provided by the next_link parameter.</p>
     * <p>Status code 4xx: Validation failed.</p>
     *
     * @param sid             Required. The session ID, generated by the requestToken call.
     * @param clientSecret    Required. The client secret that was supplied to the requestToken call.
     * @param token           Required. The token generated by the requestToken call and emailed to the user.
     * @param uriInfo         Request Information.
     * @param httpHeaders     Http headers.
     * @param asyncResponse   Asynchronous response.
     * @param securityContext Security Context.
     */
    @Operation(
        summary = "Validate ownership of an email address.",
        description = "If the three parameters are consistent with a set generated by a requestToken call, ownership of the email address"
            + " is considered to have been validated. This does not publish any information publicly, or associate the email address with"
            + " any Matrix user ID. Specifically, calls to /lookup will not show a binding.\nThe identity server is free to match the token"
            + " case-insensitively, or carry out other mapping operations such as unicode normalisation. Whether to do so is"
            + " an implementation detail for the identity server. Clients must always pass on the token without modification.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Email address is validated.",
                content = @Content(
                    schema = @Schema(
                        implementation = EmptyResponse.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "302",
                description = "Email address is validated, and the next_link parameter was provided to the requestToken call."
                    + " The user must be redirected to the URL provided by the next_link parameter.",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponse.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Validation failed.",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponse.class
                    )
                )
            )
        }
    )
    @GET
    @Path("/validate/email/submitToken")
    void getValidateEmail(
        @Parameter(
            description = "The session ID, generated by the requestToken call.",
            required = true
        ) @QueryParam("sid") String sid,
        @Parameter(
            description = "The client secret that was supplied to the requestToken call.",
            required = true
        ) @QueryParam("client_secret") String clientSecret,
        @Parameter(
            description = "The token generated by the requestToken call and emailed to the user.",
            required = true
        ) @QueryParam("token") String token,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );

    /**
     * Create a session for validating a phone number.
     * <br>
     * The identity server will send an SMS message containing a token. If that token is presented to the identity server in the future,
     * it indicates that that user was able to read the SMS for that phone number, and so we validate ownership of the phone number.
     * <br>
     * Note that homeservers offer APIs that proxy this API, adding additional behaviour on top, for example, /register/msisdn/requestToken
     * is designed specifically for use when registering an account and therefore will inform the user if the phone number given is already
     * registered on the server.
     * <br>
     * <b>Requires auth</b>:Yes.
     * <br>
     * Return: {@link SessionResponse}.
     * <p>Status code 200: Session created.</p>
     * <p>Status code 400: An error ocurred. Some possible errors are:</p>
     * <ul>
     * <li>M_INVALID_ADDRESS: The phone number provided was invalid.</li>
     * <li>M_SEND_ERROR: The validation SMS could not be sent.</li>
     * <li>M_DESTINATION_REJECTED: The identity server cannot deliver an SMS to the provided country or region.</li>
     * </ul>
     *
     * @param request         JSON body request.
     * @param uriInfo         Request Information.
     * @param httpHeaders     Http headers.
     * @param asyncResponse   Asynchronous response.
     * @param securityContext Security context.
     */
    @Operation(
        summary = "Create a session for validating a phone number.",
        description = "The identity server will send an SMS message containing a token. If that token is presented to the identity server"
            + " in the future, it indicates that that user was able to read the SMS for that phone number, and so we validate ownership"
            + " of the phone number.\nNote that homeservers offer APIs that proxy this API, adding additional behaviour on top,"
            + " for example, /register/msisdn/requestToken is designed specifically for use when registering an account and therefore will"
            + " inform the user if the phone number given is already registered on the server.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Session created.",
                content = @Content(
                    schema = @Schema(
                        implementation = SessionResponse.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "400",
                description = "An error occured.",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponse.class
                    )
                )
            )
        }
    )
    @POST
    @Path("/validate/msisdn/requestToken")
    void createPhoneSession(
        @RequestBody(
            description = "JSON body request",
            required = true
        ) PhoneRequestToken request,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );

    /**
     * Validate ownership of a phone number.
     * <br>
     * If the three parameters are consistent with a set generated by a requestToken call, ownership of the email address is
     * considered to have been validated. This does not publish any information publicly, or associate the email address with any
     * Matrix user ID. Specifically, calls to /lookup will not show a binding.
     * <br>
     * The identity server is free to match the token case-insensitively, or carry out other mapping operations such as unicode
     * normalisation. Whether to do so is an implementation detail for the identity server. Clients must always pass on the token
     * without modification.
     * <br>
     * <b>Requires auth</b>:Yes.
     * <br>
     * Return: {@link ValidationResponse}.
     * <p>Status code 200: The success of the validation.</p>
     *
     * @param request         JSON body request.
     * @param uriInfo         Request Information.
     * @param httpHeaders     Http headers.
     * @param asyncResponse   Asynchronous response.
     * @param securityContext Security context.
     */
    @Operation(
        summary = "Validate ownership of a phone number.",
        description = "If the three parameters are consistent with a set generated by a requestToken call, ownership of the email address"
            + " is considered to have been validated. This does not publish any information publicly, or associate the email address with"
            + " any Matrix user ID. Specifically, calls to /lookup will not show a binding.\nThe identity server is free to match the token"
            + " case-insensitively, or carry out other mapping operations such as unicode normalisation. Whether to do so is"
            + " an implementation detail for the identity server. Clients must always pass on the token without modification.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The success of the validation.",
                content = @Content(
                    schema = @Schema(
                        implementation = ValidationResponse.class
                    )
                )
            )
        }
    )
    @POST
    @Path("/validate/msisdn/submitToken")
    void postValidatePhone(
        @RequestBody(
            description = "JSON body request.",
            required = true
        ) SubmitToken request,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );

    /**
     * Validate ownership of a phone number.
     * <br>
     * If the three parameters are consistent with a set generated by a requestToken call, ownership of the email address is
     * considered to have been validated. This does not publish any information publicly, or associate the email address with
     * any Matrix user ID. Specifically, calls to /lookup will not show a binding.
     * <br>
     * Note that, in contrast with the POST version, this endpoint will be used by end-users, and so the response should be human-readable.
     * <br>
     * <b>Requires auth</b>:Yes.
     * <br>
     * Return: {@link EmptyResponse}.
     * <p>Status code 200: Phone number is validated.</p>
     * <p>Status code 3xx: Phone number is validated, and the next_link parameter was provided to the requestToken call.
     * The user must be redirected to the URL provided by the next_link parameter.</p>
     * <p>Status code 4xx: Validation failed.</p>
     *
     * @param sid             Required. The session ID, generated by the requestToken call.
     * @param clientSecret    Required. The client secret that was supplied to the requestToken call.
     * @param token           Required. The token generated by the requestToken call and sent to the user.
     * @param uriInfo         Request Information.
     * @param httpHeaders     Http headers.
     * @param asyncResponse   Asynchronous response.
     * @param securityContext Security context.
     */
    @Operation(
        summary = "Validate ownership of an email address.",
        description = "If the three parameters are consistent with a set generated by a requestToken call, ownership of the email address"
            + " is considered to have been validated. This does not publish any information publicly, or associate the email address with"
            + " any Matrix user ID. Specifically, calls to /lookup will not show a binding.\nThe identity server is free to match the token"
            + " case-insensitively, or carry out other mapping operations such as unicode normalisation. Whether to do so is"
            + " an implementation detail for the identity server. Clients must always pass on the token without modification.\n"
            + " Note: for backwards compatibility with previous drafts of this specification, the parameters may also be specified"
            + "as application/x-form-www-urlencoded data. However, this usage is deprecated.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Phone number is validated.",
                content = @Content(
                    schema = @Schema(
                        implementation = EmptyResponse.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "302",
                description = "Email address is validated, and the next_link parameter was provided to the requestToken call."
                    + " The user must be redirected to the URL provided by the next_link parameter.",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponse.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Validation failed.",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponse.class
                    )
                )
            )

        }
    )
    @GET
    @Path("/validate/msisdn/submitToken")
    void getValidatePhone(
        @Parameter(
            description = "The session ID, generated by the requestToken call.",
            required = true
        ) @QueryParam("sid") String sid,
        @Parameter(
            description = "The client secret that was supplied to the requestToken call.",
            required = true
        ) @QueryParam("client_secret") String clientSecret,
        @Parameter(
            description = "The token generated by the requestToken call and sent to the user.",
            required = true
        ) @QueryParam("token") String token,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );
}
