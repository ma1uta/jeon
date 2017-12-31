package geek.ma1uta.matrix.rest.client.api;

import geek.ma1uta.matrix.rest.client.model.Event;
import geek.ma1uta.matrix.rest.client.model.JoinedMembersResponse;
import geek.ma1uta.matrix.rest.client.model.MembersResponse;
import geek.ma1uta.matrix.rest.client.model.Page;
import geek.ma1uta.matrix.rest.client.model.RedactRequest;
import geek.ma1uta.matrix.rest.client.model.SendEventResponse;
import geek.ma1uta.matrix.rest.client.model.StateEvent;

import java.util.List;
import java.util.Map;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/_matrix/client/r0/rooms/")
@JsonRest
public interface RoomEventApi {

    @GET
    @Path("/{roomId}/state/{eventType}/{stateKey}")
    Map<String, Object> eventsForRoomWithTypeAndState(@PathParam("roomId") String roomId, @PathParam("eventType") String eventType,
                                                      @PathParam("stateKey") String stateKey);

    @GET
    @Path("/{roomId}/state/{eventType}")
    Map<String, Object> eventsForRoomWithType(@PathParam("roomId") String roomId, @PathParam("eventType") String eventType);

    @GET
    @Path("/{roomId}/state")
    List<StateEvent> eventsForRoom(@PathParam("roomId") String roomId);

    @GET
    @Path("/{roomId}/members")
    MembersResponse members(@PathParam("roomId") String roomId);

    @GET
    @Path("/{roomId}/joined_members")
    JoinedMembersResponse joinedMembers(@PathParam("roomId") String roomId);

    @GET
    @Path("/{roomId}/messages")
    Page<Event> messages(@PathParam("roomId") String roomId);

    @PUT
    @Path("/{roomId}/state/{eventType}/{stateKey}")
    SendEventResponse sendEventWithTypeAndState(@PathParam("roomId") String roomId, @PathParam("eventType") String eventType,
                                                @PathParam("stateKey") String stateKey, Map<String, Object> event);

    @PUT
    @Path("/{roomId}/state/{eventType}")
    SendEventResponse sendEventWithType(@PathParam("roomId") String roomId, @PathParam("eventType") String eventType,
                                        Map<String, Object> event);

    @PUT
    @Path("/{roomId}/send/{eventType}/{txnId}")
    SendEventResponse sendEvent(@PathParam("roomId") String roomId, @PathParam("eventType") String eventType,
                                @PathParam("txnId") String txnId, Map<String, Object> event);

    @PUT
    @Path("/{roomId}/redact/{eventType}/{txnId}")
    SendEventResponse redact(@PathParam("roomId") String roomId, @PathParam("eventType") String eventType,
                             @PathParam("txnId") String txnId, RedactRequest event);

}
