package geek.ma1uta.matrix.rest.client.api;

import geek.ma1uta.matrix.rest.client.model.SyncRequest;
import geek.ma1uta.matrix.rest.client.model.SyncResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/_matrix/client/r0")
@JsonRest
public interface SyncApi {

    @GET
    @Path("/sync")
    SyncResponse sync(SyncRequest syncRequest);
}
