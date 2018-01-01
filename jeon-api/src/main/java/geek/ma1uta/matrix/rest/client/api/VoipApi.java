package geek.ma1uta.matrix.rest.client.api;

import geek.ma1uta.matrix.rest.client.model.voip.VoipResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/_matrix/client/r0/voip")
@JsonRest
public interface VoipApi {

    @GET
    @Path("/turnServer")
    VoipResponse turnServer();
}
