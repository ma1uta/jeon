package geek.ma1uta.matrix.rest.client.api;

import geek.ma1uta.matrix.rest.client.model.version.VersionsResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/_matrix/client/versions")
@JsonRest
public interface VersionApi {

    @GET
    VersionsResponse versions();
}
