package geek.ma1uta.matrix.rest.client.api;

import geek.ma1uta.matrix.rest.client.model.EmptyResponse;
import geek.ma1uta.matrix.rest.client.model.account.DeactivateRequest;
import geek.ma1uta.matrix.rest.client.model.account.PasswordRequest;
import geek.ma1uta.matrix.rest.client.model.account.RegisterRequest;
import geek.ma1uta.matrix.rest.client.model.account.RequestToken;
import geek.ma1uta.matrix.rest.client.model.account.ThreePidRequest;
import geek.ma1uta.matrix.rest.client.model.account.ThreePidResponse;
import geek.ma1uta.matrix.rest.client.model.account.WhoamiResponse;
import geek.ma1uta.matrix.rest.client.model.auth.LoginResponse;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("/_matrix/client/r0")
@JsonRest
public interface AccountApi {

    interface RegiterType {
        String GUEST = "guest";

        String USER = "user";

    }

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
