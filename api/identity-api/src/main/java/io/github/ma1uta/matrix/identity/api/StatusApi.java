package io.github.ma1uta.matrix.identity.api;

import io.github.ma1uta.matrix.EmptyResponse;

import javax.ws.rs.Path;

/**
 * Checks that an Identity server is available at this API endpopint.
 *
 * @author ma1uta
 */
@Path("/_matrix/identity")
public interface StatusApi extends IdentityApi {

    /**
     * To discover that an Identity server is available at a specific URL, this endpoint can be queried and will return an empty object.
     * <p/>
     * This is primarly used for auto-discovery and health check purposes by entities acting as a client for the Identity server.
     *
     * @return Status code {@code 200} if an Identity server is ready to serve requests.
     */
    @Path("/api/v1")
    EmptyResponse v1Status();
}
