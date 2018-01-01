package geek.ma1uta.matrix.rest.client.api;

import geek.ma1uta.matrix.rest.client.model.EmptyResponse;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/_matrix/client/r0/rooms")
@JsonRest
public interface ReceiptApi {

    @POST
    @Path("/{roomId}/receipt/{receiptType}/{eventId}")
    EmptyResponse receipt(@PathParam("roomId") String roomId, @PathParam("receiptType") String receiptType,
                          @PathParam("eventId") String eventId);
}
