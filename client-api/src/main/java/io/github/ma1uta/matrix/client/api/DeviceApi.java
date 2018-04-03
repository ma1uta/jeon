package io.github.ma1uta.matrix.client.api;

import io.github.ma1uta.matrix.EmptyResponse;
import io.github.ma1uta.matrix.client.model.device.Device;
import io.github.ma1uta.matrix.client.model.device.DeviceDeleteRequest;
import io.github.ma1uta.matrix.client.model.device.DeviceUpdateRequest;
import io.github.ma1uta.matrix.client.model.device.DevicesResponse;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/_matrix/client/r0")
@JsonRest
public interface DeviceApi {

    @GET
    @Path("/devices")
    DevicesResponse devices();

    @GET
    @Path("/devices/{deviceId}")
    Device device(@PathParam("deviceId") String deviceId);

    @PUT
    @Path("/devices/{deviceId}")
    EmptyResponse update(@PathParam("deviceId") String deviceId, DeviceUpdateRequest deviceUpdateRequest);

    @DELETE
    @Path("/devices/{deviceId}")
    EmptyResponse delete(@PathParam("deviceId") String deviceId, DeviceDeleteRequest deviceDeleteRequest);
}
