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
import io.github.ma1uta.matrix.Secured;
import io.github.ma1uta.matrix.client.model.device.Device;
import io.github.ma1uta.matrix.client.model.device.DeviceDeleteRequest;
import io.github.ma1uta.matrix.client.model.device.DeviceUpdateRequest;
import io.github.ma1uta.matrix.client.model.device.DevicesDeleteRequest;
import io.github.ma1uta.matrix.client.model.device.DevicesResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

/**
 * Clients that implement this module should offer the user a list of registered devices, as well as the means to update their
 * display names. Clients should also allow users to delete disused devices.
 * <p/>
 * <a href="https://matrix.org/docs/spec/client_server/r0.3.0.html#id323">Specification.</a>
 */
@Path("/_matrix/client/r0")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface DeviceApi {

    /**
     * Gets information about all devices for the current user.
     * Requires auth: Yes.
     *
     * @param servletRequest  servlet request.
     * @param servletResponse servlet response.
     * @param securityContext security context.
     * @return A list of all registered devices for this user.
     */
    @GET
    @Secured
    @Path("/devices")
    DevicesResponse devices(@Context HttpServletRequest servletRequest, @Context HttpServletResponse servletResponse,
                            @Context SecurityContext securityContext);

    /**
     * Gets information on a single device, by device id.
     *
     * @param deviceId        Required. The device to retrieve.
     * @param servletRequest  servlet request.
     * @param servletResponse servlet response.
     * @param securityContext security context.
     * @return Status code 200: Device information.
     *     Status code 404: The current user has no device with the given ID.
     */
    @GET
    @Secured
    @Path("/devices/{deviceId}")
    Device device(@PathParam("deviceId") String deviceId, @Context HttpServletRequest servletRequest,
                  @Context HttpServletResponse servletResponse, @Context SecurityContext securityContext);

    /**
     * Updates the metadata on the given device.
     * Requires auth: Yes.
     *
     * @param deviceId            Required. The device to update.
     * @param deviceUpdateRequest The new display name for this device. If not given, the display name is unchanged.
     * @param servletRequest      servlet request.
     * @param servletResponse     servlet response.
     * @param securityContext     security context.
     * @return Status code 200: The device was successfully updated.
     *     Status code 404: The current user has no device with the given ID.
     */
    @PUT
    @Secured
    @Path("/devices/{deviceId}")
    EmptyResponse update(@PathParam("deviceId") String deviceId, DeviceUpdateRequest deviceUpdateRequest,
                         @Context HttpServletRequest servletRequest, @Context HttpServletResponse servletResponse,
                         @Context SecurityContext securityContext);

    /**
     * This API endpoint uses the User-Interactive Authentication API.
     * <p/>
     * Deletes the given device, and invalidates any access token associated with it.
     * <p/>
     * Requires auth: Yes.
     *
     * @param deviceId            Required. The device to delete.
     * @param deviceDeleteRequest Additional authentication information for the user-interactive authentication API.
     * @param servletRequest      servlet request.
     * @param servletResponse     servlet response.
     * @param securityContext     security context.
     * @return Status code 200: The device was successfully removed, or had been removed previously.
     *     Status code 401: The homeserver requires additional authentication information.
     */
    @DELETE
    @Secured
    @Path("/devices/{deviceId}")
    EmptyResponse delete(@PathParam("deviceId") String deviceId, DeviceDeleteRequest deviceDeleteRequest,
                         @Context HttpServletRequest servletRequest, @Context HttpServletResponse servletResponse,
                         @Context SecurityContext securityContext);

    /**
     * This API endpoint uses the User-Interactive Authentication API.
     * <p/>
     * Deletes the given devices, and invalidates any access token associated with them.
     * <p/>
     * Requires auth: Yes.
     *
     * @param devicesDeleteRequest JSON body request.
     * @param servletRequest servlet request.
     * @param servletResponse servlet response.
     * @param securityContext security context.
     * @return Status code 200: The devices were successfully removed, or had been removed previously.
     *     Status code 401: The homeserver requires additional authentication information.
     */
    @POST
    @Secured
    @Path("/delete_devices")
    EmptyResponse deleteDevices(DevicesDeleteRequest devicesDeleteRequest, @Context HttpServletRequest servletRequest,
                                @Context HttpServletResponse servletResponse, @Context SecurityContext securityContext);
}
