/*
 * Copyright sablintolya@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.ma1uta.matrix.client.api;

import io.github.ma1uta.matrix.EmptyResponse;
import io.github.ma1uta.matrix.client.model.room.CreateRoomRequest;
import io.github.ma1uta.matrix.client.model.room.InviteRequest;
import io.github.ma1uta.matrix.client.model.room.JoinRequest;
import io.github.ma1uta.matrix.client.model.room.JoinedRoomsResponse;
import io.github.ma1uta.matrix.client.model.room.KickRequest;
import io.github.ma1uta.matrix.client.model.room.PublicRoomsRequest;
import io.github.ma1uta.matrix.client.model.room.PublicRoomsResponse;
import io.github.ma1uta.matrix.client.model.room.RoomId;

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
