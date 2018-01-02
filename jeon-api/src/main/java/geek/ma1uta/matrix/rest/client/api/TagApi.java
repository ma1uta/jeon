package geek.ma1uta.matrix.rest.client.api;

import geek.ma1uta.matrix.rest.client.model.EmptyResponse;
import geek.ma1uta.matrix.rest.client.model.tag.Tags;

import java.util.Map;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/_matrix/client/r0/user")
public interface TagApi {

    @GET
    @Path("/{userId}/rooms/{roomId}/tags")
    Tags showTags(@PathParam("userId") String userId, @PathParam("roomId") String roomId);

    @PUT
    @Path("/{userId}/rooms/{roomId}/tags/{tag}")
    EmptyResponse addTag(@PathParam("userId") String userId, @PathParam("roomId") String roomId, @PathParam("tag") String tag,
                         Map<String, String> tagData);

    @DELETE
    @Path("/{userId}/rooms/{roomId}/tags/{tag}")
    EmptyResponse deleteTag(@PathParam("userId") String userId, @PathParam("roomId") String roomId, @PathParam("tag") String tag);
}
