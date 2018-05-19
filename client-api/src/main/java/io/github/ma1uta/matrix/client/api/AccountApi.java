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
import io.github.ma1uta.matrix.RateLimit;
import io.github.ma1uta.matrix.Secured;
import io.github.ma1uta.matrix.client.model.account.AvailableResponse;
import io.github.ma1uta.matrix.client.model.account.DeactivateRequest;
import io.github.ma1uta.matrix.client.model.account.PasswordRequest;
import io.github.ma1uta.matrix.client.model.account.RegisterRequest;
import io.github.ma1uta.matrix.client.model.account.RequestToken;
import io.github.ma1uta.matrix.client.model.account.ThreePidRequest;
import io.github.ma1uta.matrix.client.model.account.ThreePidResponse;
import io.github.ma1uta.matrix.client.model.account.WhoamiResponse;
import io.github.ma1uta.matrix.client.model.auth.LoginResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

/**
 * Account registration and management.
 * <p/>
 * <a href="https://matrix.org/docs/spec/client_server/r0.3.0.html#account-registration-and-management">Specification.</a>
 *
 * @author ma1uta
 * @version 0.0.1
 */
@Path("/_matrix/client/r0")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface AccountApi {

    /**
     * The kind of account to register.
     */
    class RegisterType {

        private RegisterType() {
        }

        /**
         * Guest.
         */
        public static final String GUEST = "guest";

        /**
         * User. Default.
         */
        public static final String USER = "user";
    }

    /**
     * Register for an account on this homeserver.
     * <p/>
     *
     * @param kind            The kind of account to register. Defaults to user. One of: ["guest", "user"].
     * @param registerRequest JSON body parameters.
     * @param servletRequest  servlet request.
     * @param servletResponse servlet response.
     * @return <p>Status code 200: The account has been registered.</p>
     * <p>Status code 400: Part of the request was invalid. This may include one of the following error codes:
     * <ul>
     * <li>M_USER_IN_USE : The desired user ID is already taken.</li>
     * <li>M_INVALID_USERNAME : The desired user ID is not a valid user name.</li>
     * <li>M_EXCLUSIVE : The desired user ID is in the exclusive namespace claimed by an application service.</li>
     * </ul>
     * </p>
     * <p>
     * These errors may be returned at any stage of the registration process, including after authentication
     * if the requested user ID was registered whilst the client was performing authentication.
     * </p>
     * <p>
     * Homeservers MUST perform the relevant checks and return these codes before performing User-Interactive Authentication,
     * although they may also return them after authentication is completed if, for example,
     * the requested user ID was registered whilst the client was performing authentication.
     * </p>
     * <p>Status code 401: The homeserver requires additional authentication information.</p>
     * <p>Status code 429: This request was rate-limited.</p>
     * @see <a href="https://matrix.org/docs/spec/client_server/r0.3.0.html#id147">Register for an account on this homeserver.</a>
     */
    @POST
    @RateLimit
    @Path("/register")
    LoginResponse register(@QueryParam("kind") String kind, RegisterRequest registerRequest, @Context HttpServletRequest servletRequest,
                           @Context HttpServletResponse servletResponse);

    /**
     * Proxies the identity server API validate/email/requestToken, but first checks that the given email address is not already
     * associated with an account on this Home Server. Note that, for consistency, this API takes JSON objects, though the
     * Identity Server API takes x-www-form-urlencoded parameters. See the Identity Server API for further information.
     *
     * @param requestToken    request.
     * @param servletRequest  servlet request.
     * @param servletResponse servlet response.
     * @return <p>Status code 200: An email has been sent to the specified address. Note that this may be an email containing the
     * validation token or it may be informing the user of an error.</p>
     * <p>Status code 400: Part of the request was invalid. This may include one of the following error codes:
     * <ul>
     * <li>M_THREEPID_IN_USE : The email address is already registered to an account on this server.
     * However, if the home server has the ability to send email, it is recommended that the server instead send an email to
     * the user with instructions on how to reset their password. This prevents malicious parties from being able to determine
     * if a given email address has an account on the Home Server in question.</li>
     * <li>M_SERVER_NOT_TRUSTED : The id_server parameter refers to an ID server that is not trusted by this Home Server.</li>
     * </ul>
     * </p>
     */
    @POST
    @Path("/register/email/requestToken")
    EmptyResponse requestToken(RequestToken requestToken, @Context HttpServletRequest servletRequest,
                               @Context HttpServletResponse servletResponse);

    /**
     * Changes the password for an account on this homeserver.
     * <p/>
     * This API endpoint uses the User-Interactive Authentication API.
     * <p/>
     * An access token should be submitted to this endpoint if the client has an active session.
     * <p/>
     * The homeserver may change the flows available depending on whether a valid access token is provided.
     *
     * @param passwordRequest password.
     * @param servletRequest  servlet request.
     * @param servletResponse servlet response.
     * @param securityContext security context.
     * @return <p>Status code 200: The password has been changed.</p>
     * <p>Status code 401: The homeserver requires additional authentication information.</p>
     * <p>Status code 429: This request was rate-limited.</p>
     */
    @POST
    @RateLimit
    @Secured
    @Path("/account/password")
    EmptyResponse password(PasswordRequest passwordRequest, @Context HttpServletRequest servletRequest,
                           @Context HttpServletResponse servletResponse, @Context SecurityContext securityContext);

    /**
     * Proxies the identity server API validate/email/requestToken, but first checks that the given email address is associated
     * with an account on this Home Server. This API should be used to request validation tokens when authenticating for
     * the account/password endpoint. This API's parameters and response are identical to that of the HS API
     * /register/email/requestToken except that M_THREEPID_NOT_FOUND may be returned if no account matching the given email
     * address could be found. The server may instead send an email to the given address prompting the user to create an account.
     * M_THREEPID_IN_USE may not be returned.
     *
     * @param servletRequest  servlet request.
     * @param servletResponse servlet response.
     * @return Status code 200: An email was sent to the given address.
     */
    @POST
    @Path("/account/password/email/requestToken")
    EmptyResponse passwordRequestToken(@Context HttpServletRequest servletRequest, @Context HttpServletResponse servletResponse);

    /**
     * Deactivate the user's account, removing all ability for the user to login again.
     * <p/>
     * This API endpoint uses the User-Interactive Authentication API.
     * <p/>
     * An access token should be submitted to this endpoint if the client has an active session.
     * <p/>
     * The homeserver may change the flows available depending on whether a valid access token is provided.
     *
     * @param deactivateRequest request.
     * @param servletRequest    servlet request.
     * @param servletResponse   servlet response.
     * @param securityContext   security context.
     * @return <p>Status code 200: The account has been deactivated.</p>
     * <p>Status code 401: The homeserver requires additional authentication information.</p>
     * <p>Status code 429: This request was rate-limited.</p>
     */
    @POST
    @RateLimit
    @Secured
    @Path("/account/deactivate")
    EmptyResponse deactivate(DeactivateRequest deactivateRequest, @Context HttpServletRequest servletRequest,
                             @Context HttpServletResponse servletResponse, @Context SecurityContext securityContext);

    /**
     * Checks to see if a username is available, and valid, for the server.
     * <p/>
     * The server should check to ensure that, at the time of the request, the username requested is available for use.
     * This includes verifying that an application service has not claimed the username and that the username fits the server's
     * desired requirements (for example, a server could dictate that it does not permit usernames with underscores).
     * <p/>
     * Matrix clients may wish to use this API prior to attempting registration, however the clients must also be aware
     * that using this API does not normally reserve the username. This can mean that the username becomes unavailable
     * between checking its availability and attempting to register it.
     * <p/>
     * Rate-limited: Yes.
     *
     * @param username        Required. The username to check the availability of.
     * @param servletRequest  servlet request.
     * @param servletResponse servlet response.
     * @return Status code 200: The username is available.
     *     Status code 400: Part of the request was invalid or the username is not available. This may include one of the following error
     *     codes:
     *     <ul>
     *     <li>M_USER_IN_USE : The desired username is already taken.</li>
     *     <li>M_INVALID_USERNAME : The desired username is not a valid user name.</li>
     *     <li>M_EXCLUSIVE : The desired username is in the exclusive namespace claimed by an application service.</li>
     *     </ul>
     *     Status code 429: This request was rate-limited.
     */
    @GET
    @RateLimit
    @Path("/register/available")
    AvailableResponse available(@QueryParam("username") String username, @Context HttpServletRequest servletRequest,
                                @Context HttpServletResponse servletResponse);

    /**
     * Gets a list of the third party identifiers that the homeserver has associated with the user's account.
     * <p/>
     * This is not the same as the list of third party identifiers bound to the user's Matrix ID in Identity Servers.
     * <p/>
     * Identifiers in this list may be used by the homeserver as, for example, identifiers that it will accept to reset the user's
     * account password.
     *
     * @param servletRequest  servlet request.
     * @param servletResponse servlet response.
     * @param securityContext security context.
     * @return Status code 200: The lookup was successful.
     */
    @GET
    @Secured
    @Path("/account/3pid")
    ThreePidResponse showThreePid(@Context HttpServletRequest servletRequest, @Context HttpServletResponse servletResponse,
                                  @Context SecurityContext securityContext);

    /**
     * Adds contact information to the user's account.
     *
     * @param threePidRequest new contact information.
     * @param servletRequest  servlet request.
     * @param servletResponse servlet response.
     * @param securityContext security context.
     * @return <p>Status code 200: The addition was successful.</p>
     * <p>Status code 403: The credentials could not be verified with the identity server.</p>
     */
    @POST
    @Secured
    @Path("/account/3pid")
    EmptyResponse updateThreePid(ThreePidRequest threePidRequest, @Context HttpServletRequest servletRequest,
                                 @Context HttpServletResponse servletResponse, @Context SecurityContext securityContext);

    /**
     * Proxies the identity server API validate/email/requestToken, but first checks that the given email address is not already
     * associated with an account on this Home Server. This API should be used to request validation tokens when adding an email
     * address to an account. This API's parameters and response is identical to that of the HS API /register/email/requestToken endpoint.
     *
     * @param servletRequest  servlet request.
     * @param servletResponse servlet response.
     * @return Status code 200: An email was sent to the given address.
     */
    @POST
    @Path("/account/3pid/email/requestToken")
    EmptyResponse threePidRequestToken(@Context HttpServletRequest servletRequest, @Context HttpServletResponse servletResponse);

    /**
     * Gets information about the owner of a given access token.
     * <p/>
     * Note that, as with the rest of the Client-Server API, Application Services may masquerade as users within their namespace
     * by giving a user_id query parameter. In this situation, the server should verify that the given user_id is registered by
     * the appservice, and return it in the response body.
     *
     * @param servletRequest  servlet request.
     * @param servletResponse servlet response.
     * @param securityContext security context.
     * @return Status code 200: The token belongs to a known user.
     */
    @GET
    @RateLimit
    @Secured
    @Path("/account/whoami")
    WhoamiResponse whoami(@Context HttpServletRequest servletRequest, @Context HttpServletResponse servletResponse,
                          @Context SecurityContext securityContext);
}
