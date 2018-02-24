package io.github.ma1uta.matrix.client.api;

import io.github.ma1uta.matrix.client.model.EmptyResponse;
import io.github.ma1uta.matrix.client.model.sendtodevice.SendToDeviceRequest;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/_matrix/client/r0/sendToDevice")
@JsonRest
public interface SendToDeviceApi {

    @PUT
    @Path("/{eventType}/{txnId}")
    EmptyResponse send(@PathParam("eventType") String eventType, @PathParam("txnId") String txnId, SendToDeviceRequest sendToDeviceRequest);
}
