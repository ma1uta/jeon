package geek.ma1uta.matrix.rest.client.api;

import geek.ma1uta.matrix.rest.client.model.EmptyResponse;
import geek.ma1uta.matrix.rest.client.model.room.CreateRoomRequest;
import geek.ma1uta.matrix.rest.client.model.room.InviteRequest;
import geek.ma1uta.matrix.rest.client.model.room.JoinRequest;
import geek.ma1uta.matrix.rest.client.model.room.JoinedRoomsResponse;
import geek.ma1uta.matrix.rest.client.model.room.KickRequest;
import geek.ma1uta.matrix.rest.client.model.room.PublicRoomsRequest;
import geek.ma1uta.matrix.rest.client.model.room.PublicRoomsResponse;
import geek.ma1uta.matrix.rest.client.model.room.RoomId;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

@Path("/_matrix/client/r0")
public interface RoomApi {

    @POST
    @Path("/createRoom")
    RoomId create(CreateRoomRequest createRoomRequest);

    @PUT
    @Path("/directory/room/{roomAlias}")
    EmptyResponse newAlias(@PathParam("roomAlias") String roomAlias, RoomId roomId);

    @GET
    @Path("/directory/room/{roomAlias}")
    RoomId resolve(@PathParam("roomAlias") String roomAlias);

    @DELETE
    @Path("/directory/room/{roomAlias}")
    EmptyResponse delete(@PathParam("roomAlias") String roomAlias);

    @GET
    @Path("/joined_rooms")
    JoinedRoomsResponse joinedRooms();

    @POST
    @Path("/rooms/{roomId}/invite")
    EmptyResponse invite(@PathParam("roomId") String roomId, InviteRequest inviteRequest);

    @POST
    @Path("/rooms/{roomId}/join")
    RoomId join(@PathParam("roomId") String roomId, JoinRequest joinRequest);

    @POST
    @Path("/join/{roomIdOrAlias}")
    RoomId joinByIdOrAlias(@PathParam("roomIdOrAlias") String roomIdOrAlias, JoinRequest joinRequest);

    @POST
    @Path("/rooms/{roomId}/leave")
    EmptyResponse leave(@PathParam("roomId") String roomId);

    @POST
    @Path("/rooms/{roomId}/forget")
    EmptyResponse forget(@PathParam("roomId") String roomId);

    @POST
    @Path("/rooms/{roomId}/kick")
    EmptyResponse kick(@PathParam("roomId") String roomId, KickRequest kickRequest);

    @POST
    @Path("/rooms/{roomId}/ban")
    EmptyResponse ban(@PathParam("roomId") String roomId, KickRequest banRequest);

    @POST
    @Path("/rooms/{roomId}/unban")
    EmptyResponse unban(String roomId, KickRequest unbanRequest);

    @GET
    @Path("/publicRooms")
    PublicRoomsResponse showPublicRooms(@QueryParam("limit") Long limit, @QueryParam("since") String since,
                                        @QueryParam("server") String server);

    @POST
    @Path("/publicRooms")
    PublicRoomsResponse searchPublicRooms(@QueryParam("server") String server, PublicRoomsRequest publicRoomsRequest);
}
