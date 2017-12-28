package geek.ma1uta.matrix.api;

import geek.ma1uta.matrix.model.rest.VersionsResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/_matrix/client/versions")
@JsonRest
public interface Versions {

    @GET
    VersionsResponse versions();
}
