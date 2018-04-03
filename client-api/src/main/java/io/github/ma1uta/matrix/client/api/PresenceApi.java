package io.github.ma1uta.matrix.client.api;

import io.github.ma1uta.matrix.EmptyResponse;
import io.github.ma1uta.matrix.client.model.Event;
import io.github.ma1uta.matrix.client.model.presence.PresenceList;
import io.github.ma1uta.matrix.client.model.presence.PresenceStatus;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/_matrix/client/r0/presence")
@JsonRest
public interface PresenceApi {

    @PUT
    @Path("/{userId}/status")
    EmptyResponse setPresenceStatus(@PathParam("userId") String userId, PresenceStatus presenceStatus);

    @GET
    @Path("/{userId}/status")
    PresenceStatus getPresenceStatus(@PathParam("userId") String userId);

    @POST
    @Path("/list/{userId}")
    EmptyResponse setPresenceList(@PathParam("userId") String userId, PresenceList presenceList);

    @GET
    @Path("/list/{userId}")
    List<Event> getPresenceList(@PathParam("userId") String userId);


}
