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
