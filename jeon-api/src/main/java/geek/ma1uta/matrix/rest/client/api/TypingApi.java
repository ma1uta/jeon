package geek.ma1uta.matrix.rest.client.api;

import geek.ma1uta.matrix.rest.client.model.EmptyResponse;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/_matrix/client/r0/rooms")
public interface TypingApi {

    @PUT
    @Path("/{roomId}/typing/{userId}")
    EmptyResponse typing(@PathParam("roomId") String roomId, @PathParam("userId") String userId);
}
