package geek.ma1uta.matrix.client.api;

import geek.ma1uta.matrix.client.model.version.VersionsResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Gets the versions of the specification supported by the server.
 * <p/>
 * Values will take the form rX.Y.Z.
 * <p/>
 * Only the latest Z value will be reported for each supported X.Y value.
 * i.e. if the server implements r0.0.0, r0.0.1, and r1.2.0, it will report r0.0.1 and r1.2.0.
 *
 * @author ma1uta
 */
@Path("/_matrix/client/versions")
public interface VersionApi {

    /**
     * Gets the versions of the specification supported by the server.
     *
     * @return Status code 200: The versions supported by the server.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    VersionsResponse versions();
}
