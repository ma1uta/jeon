package io.github.ma1uta.matrix.client.api;

import io.github.ma1uta.matrix.client.model.EmptyResponse;

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
