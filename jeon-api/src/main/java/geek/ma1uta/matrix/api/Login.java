package geek.ma1uta.matrix.api;

import geek.ma1uta.matrix.model.rest.EmptyResponse;
import geek.ma1uta.matrix.model.rest.LoginRequest;
import geek.ma1uta.matrix.model.rest.LoginResponse;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/_matrix/client/r0")
@JsonRest
public interface Login {

    @POST
    @Path("/login")
    LoginResponse login(LoginRequest loginRequest);

    @POST
    @Path("/logout")
    EmptyResponse logout();
}
