package geek.ma1uta.matrix.rest.client.api;

import geek.ma1uta.matrix.rest.client.model.EmptyResponse;
import geek.ma1uta.matrix.rest.client.model.auth.LoginRequest;
import geek.ma1uta.matrix.rest.client.model.auth.LoginResponse;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/_matrix/client/r0")
@JsonRest
public interface AuthApi {

    @POST
    @Path("/login")
    LoginResponse login(LoginRequest loginRequest);

    @POST
    @Path("/logout")
    EmptyResponse logout();
}
