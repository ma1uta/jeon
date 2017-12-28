package geek.ma1uta.matrix.api;

import geek.ma1uta.matrix.model.RegisterType;
import geek.ma1uta.matrix.model.rest.AuthenticationData;
import geek.ma1uta.matrix.model.rest.DeactivateRequest;
import geek.ma1uta.matrix.model.rest.EmptyResponse;
import geek.ma1uta.matrix.model.rest.LoginResponse;
import geek.ma1uta.matrix.model.rest.PasswordRequest;
import geek.ma1uta.matrix.model.rest.RegisterRequest;
import geek.ma1uta.matrix.model.rest.RequestToken;
import geek.ma1uta.matrix.model.rest.ThreePidRequest;
import geek.ma1uta.matrix.model.rest.ThreePidResponse;
import geek.ma1uta.matrix.model.rest.WhoamiResponse;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("/_matrix/client/r0")
public interface Account {

    @POST
    @Path("/register")
    LoginResponse register(@QueryParam("kind") RegisterType kind, RegisterRequest registerRequest);

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
