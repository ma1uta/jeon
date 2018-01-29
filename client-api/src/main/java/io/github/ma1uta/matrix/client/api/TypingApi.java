package io.github.ma1uta.matrix.client.api;

import io.github.ma1uta.matrix.client.model.EmptyResponse;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/_matrix/client/r0/rooms")
@JsonRest
public interface TypingApi {

    @PUT
    @Path("/{roomId}/typing/{userId}")
    EmptyResponse typing(@PathParam("roomId") String roomId, @PathParam("userId") String userId);
}
