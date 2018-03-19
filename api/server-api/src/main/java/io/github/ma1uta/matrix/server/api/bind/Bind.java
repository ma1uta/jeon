package io.github.ma1uta.matrix.server.api.bind;

import io.github.ma1uta.matrix.EmptyResponse;
import io.github.ma1uta.matrix.server.model.bind.OnBindRequest;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * 3pid federation api.
 */
@Path("/_matrix/federation/v1/3pid")
public interface Bind {

    /**
     * If the owner of that particular 3pid binds it with a Matrix user ID, the identity server will attempt to make an HTTP POST
     * to the Matrix user's homeserver.
     *
     * @param onBindRequest request.
     * @return empty.
     */
    @Path("/onbind")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    EmptyResponse onBind(OnBindRequest onBindRequest);
}
