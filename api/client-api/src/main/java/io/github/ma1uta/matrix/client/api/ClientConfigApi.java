package io.github.ma1uta.matrix.client.api;

import io.github.ma1uta.matrix.client.model.EmptyResponse;

import java.util.Map;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/_matrix/client/r0/user")
public interface ClientConfigApi {

    @PUT
    @Path("/{userId}/account_data/{type}")
    EmptyResponse addConfig(@PathParam("userId") String userId, @PathParam("type") String type, Map<String, String> accountData);

    @PUT
    @Path("/{userId}/rooms/{roomId}/account_data/{type}")
    EmptyResponse addConfig(@PathParam("userId") String userId, @PathParam("type") String type, @PathParam("roomId") String roomId,
                            Map<String, String> accountData);
}
