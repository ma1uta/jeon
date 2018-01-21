package geek.ma1uta.matrix.client.api;

import geek.ma1uta.matrix.client.model.EmptyResponse;
import geek.ma1uta.matrix.client.model.auth.LoginRequest;
import geek.ma1uta.matrix.client.model.auth.LoginResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/_matrix/client/r0")
public interface AuthApi extends ClientApi {

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    LoginResponse login(LoginRequest loginRequest);

    @POST
    @Path("/logout")
    EmptyResponse logout();
}
