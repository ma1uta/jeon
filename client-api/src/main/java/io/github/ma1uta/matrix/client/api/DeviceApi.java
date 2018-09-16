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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

/**
 * Clients that implement this module should offer the user a list of registered devices, as well as the means to update their
 * display names. Clients should also allow users to delete disused devices.
 */
@Api(
    value = "Device",
    description = "Clients that implement this module should offer the user a list of registered devices, as well "
        + "as the means to update their display names. Clients should also allow users to delete disused devices."
)
@Path("/_matrix/client/r0")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface DeviceApi {

    /**
     * Gets information about all devices for the current user.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <br>
     * Return: {@link DevicesResponse}.
     * <p>Status code 200: A list of all registered devices for this user.</p>
     *
     * @param servletRequest  Servlet request.
     * @param asyncResponse   Asynchronous response.
     * @param securityContext Security context.
     */
    @ApiOperation(
        value = "Gets information about all devices for the current user.",
        response = DevicesResponse.class
    )
    @ApiResponses( {
        @ApiResponse(code = 200, message = "A list of all registered devices for this user.")
    })
    @GET
    @Secured
    @Path("/devices")
    void devices(
        @Context HttpServletRequest servletRequest,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );

    /**
     * Gets information on a single device, by device id.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <br>
     * Return: {@link Device}.
     * <p>Status code 200: Device information.</p>
     * <p>Status code 404: The current user has no device with the given ID.</p>
     *
     * @param deviceId        Required. The device to retrieve.
     * @param servletRequest  Servlet request.
     * @param asyncResponse   Asynchronous response.
     * @param securityContext Security context.
     */
    @ApiOperation(
        value = "Gets information on a single device, by device id.",
        response = Device.class
    )
    @ApiResponses( {
        @ApiResponse(code = 200, message = "Device information."),
        @ApiResponse(code = 404, message = "The current user has no device with the given ID.")
    })
    @GET
    @Secured
    @Path("/devices/{deviceId}")
    void device(
        @ApiParam(
            value = "The device to retrieve",
            required = true
        ) @PathParam("deviceId") String deviceId,

        @Context HttpServletRequest servletRequest,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );

    /**
     * Updates the metadata on the given device.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <br>
     * Return: {@link EmptyResponse}.
     * <p>Status code 200: The device was successfully updated.</p>
     * <p>Status code 404: The current user has no device with the given ID.</p>
     *
     * @param deviceId            Required. The device to update.
     * @param deviceUpdateRequest The new display name for this device. If not given, the display name is unchanged.
     * @param servletRequest      Servlet request.
     * @param asyncResponse       Asynchronous response.
     * @param securityContext     Security context.
     */
    @ApiOperation(
        value = "Updates the metadata on the given device.",
        response = EmptyResponse.class
    )
    @ApiResponses( {
        @ApiResponse(code = 200, message = "The device was successfully updated."),
        @ApiResponse(code = 404, message = "The current user has no device with the given ID.")
    })
    @PUT
    @Secured
    @Path("/devices/{deviceId}")
    void updateDevice(
        @ApiParam(
            value = "The device to update.",
            required = true
        ) @PathParam("deviceId") String deviceId,
        @ApiParam(
            value = "The new display name for this device. If not given, the display name is unchanged."
        ) DeviceUpdateRequest deviceUpdateRequest,

        @Context HttpServletRequest servletRequest,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );

    /**
     * This API endpoint uses the User-Interactive Authentication API.
     * <br>
     * Deletes the given device, and invalidates any access token associated with it.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <br>
     * Return: {@link EmptyResponse}.
     * <p>Status code 200: The device was successfully removed, or had been removed previously.</p>
     * <p>Status code 401: The homeserver requires additional authentication information.</p>
     *
     * @param deviceId            Required. The device to delete.
     * @param deviceDeleteRequest Additional authentication information for the user-interactive authentication API.
     * @param servletRequest      Servlet request.
     * @param asyncResponse       Asynchronous response.
     * @param securityContext     Security context.
     */
    @ApiOperation(
        value = "Deletes the given device, and invalidates any access token associated with it.",
        notes = "This API endpoint uses the User-Interactive Authentication API.",
        response = EmptyResponse.class
    )
    @ApiResponses( {
        @ApiResponse(code = 200, message = "The device was successfully removed, or had been removed previously."),
        @ApiResponse(code = 401, message = "The homeserver requires additional authentication information.")
    })
    @DELETE
    @Secured
    @Path("/devices/{deviceId}")
    void deleteDevice(
        @ApiParam(
            value = "The device to delete",
            required = true
        ) @PathParam("deviceId") String deviceId,
        @ApiParam(
            value = "Additional authentication information for the user-interactive authentication API."
        ) DeviceDeleteRequest deviceDeleteRequest,

        @Context HttpServletRequest servletRequest,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );

    /**
     * This API endpoint uses the User-Interactive Authentication API.
     * <br>
     * Deletes the given devices, and invalidates any access token associated with them.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <br>
     * Return: {@link EmptyResponse}.
     * <p>Status code 200: The devices were successfully removed, or had been removed previously.</p>
     * <p>Status code 401: The homeserver requires additional authentication information.</p>
     *
     * @param devicesDeleteRequest JSON body request.
     * @param servletRequest       Servlet request.
     * @param asyncResponse        Asynchronous response.
     * @param securityContext      Security context.
     */
    @ApiOperation(
        value = "Deletes the given devices, and invalidates any access token associated with them.",
        notes = "This API endpoint uses the User-Interactive Authentication API.",
        response = EmptyResponse.class
    )
    @ApiResponses( {
        @ApiResponse(code = 200, message = "The devices were successfully removed, or had been removed previously."),
        @ApiResponse(code = 401, message = "The homeserver requires additional authentication information.")
    })
    @POST
    @Secured
    @Path("/delete_devices")
    void deleteDevices(
        @ApiParam("JSON body request") DevicesDeleteRequest devicesDeleteRequest,

        @Context HttpServletRequest servletRequest,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );
}
