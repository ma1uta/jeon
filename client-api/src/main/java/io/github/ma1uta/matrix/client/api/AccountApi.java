package io.github.ma1uta.matrix.client.api;

import io.github.ma1uta.matrix.EmptyResponse;
import io.github.ma1uta.matrix.client.model.account.DeactivateRequest;
import io.github.ma1uta.matrix.client.model.account.PasswordRequest;
import io.github.ma1uta.matrix.client.model.account.RegisterRequest;
import io.github.ma1uta.matrix.client.model.account.RequestToken;
import io.github.ma1uta.matrix.client.model.account.ThreePidRequest;
import io.github.ma1uta.matrix.client.model.account.ThreePidResponse;
import io.github.ma1uta.matrix.client.model.account.WhoamiResponse;
import io.github.ma1uta.matrix.client.model.auth.LoginResponse;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
 * Account registration and management.
 * <p/>
 * https://matrix.org/docs/spec/client_server/r0.3.0.html#id146
 *
 * @author ma1uta
 * @version 0.0.1
 */
@Path("/_matrix/client/r0")
@JsonRest
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
    @Path("/register")
    LoginResponse register(@QueryParam("kind") String kind, RegisterRequest registerRequest);

    @POST
    @Path("/register/email/requestToken")
    EmptyResponse requestToken(RequestToken requestToken);

    @POST
    @Path("/account/password")
    EmptyResponse password(PasswordRequest passwordRequest);

    @POST
    @Path("/account/password/email/requestToken")
    EmptyResponse passwordRequestToken();

    @POST
    @Path("/account/deactivate")
    EmptyResponse deactivate(DeactivateRequest deactivateRequest);

    @GET
    @Path("/account/3pid")
    ThreePidResponse showThreePid();

    @POST
    @Path("/account/3pid")
    EmptyResponse updateThreePid(ThreePidRequest threePidRequest);

    @POST
    @Path("/account/3pid/email/requestToken")
    EmptyResponse threePidRequestToken();

    @GET
    @Path("/account/whoami")
    WhoamiResponse whiami();
}
