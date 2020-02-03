/*
 * Copyright Anatoliy Sablin tolya@sablin.xyz
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
import io.github.ma1uta.matrix.ErrorResponse;
import io.github.ma1uta.matrix.Secured;
import io.github.ma1uta.matrix.client.model.device.Device;
import io.github.ma1uta.matrix.client.model.device.DeviceDeleteRequest;
import io.github.ma1uta.matrix.client.model.device.DeviceUpdateRequest;
import io.github.ma1uta.matrix.client.model.device.DevicesDeleteRequest;
import io.github.ma1uta.matrix.client.model.device.DevicesResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

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
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

/**
 * Clients that implement this module should offer the user a list of registered devices, as well as the means to update their
 * display names. Clients should also allow users to delete disused devices.
 */
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
     * @param uriInfo         Request Information.
     * @param httpHeaders     Http headers.
     * @param asyncResponse   Asynchronous response.
     * @param securityContext Security context.
     */
    @Operation(
        summary = "Gets information about all devices for the current user.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "A list of all registered devices for this user.",
                content = @Content(
                    schema = @Schema(
                        implementation = DevicesResponse.class
                    )
                )
            )
        },
        security = {
            @SecurityRequirement(
                name = "accessToken"
            )
        },
        tags = {
            "Device management"
        }
    )
    @GET
    @Secured
    @Path("/devices")
    void devices(
        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
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
     * @param uriInfo         Request Information.
     * @param httpHeaders     Http headers.
     * @param asyncResponse   Asynchronous response.
     * @param securityContext Security context.
     */
    @Operation(
        summary = "Gets information on a single device, by device id.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Device information.",
                content = @Content(
                    schema = @Schema(
                        implementation = Device.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "404",
                description = "The current user has no device with the given ID.",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponse.class
                    )
                )
            )
        },
        security = {
            @SecurityRequirement(
                name = "accessToken"
            )
        },
        tags = {
            "Device management"
        }
    )
    @GET
    @Secured
    @Path("/devices/{deviceId}")
    void device(
        @Parameter(
            description = "The device to retrieve",
            required = true
        ) @PathParam("deviceId") String deviceId,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
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
     * @param uriInfo             Request Information.
     * @param httpHeaders         Http headers.
     * @param asyncResponse       Asynchronous response.
     * @param securityContext     Security context.
     */
    @Operation(
        summary = "Updates the metadata on the given device.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The device was successfully updated.",
                content = @Content(
                    schema = @Schema(
                        implementation = EmptyResponse.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "404",
                description = "The current user has no device with the given ID.",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponse.class
                    )
                )
            )
        },
        security = {
            @SecurityRequirement(
                name = "accessToken"
            )
        },
        tags = {
            "Device management"
        }
    )
    @PUT
    @Secured
    @Path("/devices/{deviceId}")
    void updateDevice(
        @Parameter(
            description = "The device to update.",
            required = true
        ) @PathParam("deviceId") String deviceId,
        @RequestBody(
            description = "The new display name for this device. If not given, the display name is unchanged."
        ) DeviceUpdateRequest deviceUpdateRequest,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
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
     * @param uriInfo             Request Information.
     * @param httpHeaders         Http headers.
     * @param asyncResponse       Asynchronous response.
     * @param securityContext     Security context.
     */
    @Operation(
        summary = "Deletes the given device, and invalidates any access token associated with it.",
        description = "This API endpoint uses the User-Interactive Authentication API.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The device was successfully removed, or had been removed previously.",
                content = @Content(
                    schema = @Schema(
                        implementation = EmptyResponse.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "401",
                description = "The homeserver requires additional authentication information.",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponse.class
                    )
                )
            )
        },
        security = {
            @SecurityRequirement(
                name = "accessToken"
            )
        },
        tags = {
            "Device management"
        }
    )
    @DELETE
    @Secured
    @Path("/devices/{deviceId}")
    void deleteDevice(
        @Parameter(
            description = "The device to delete",
            required = true
        ) @PathParam("deviceId") String deviceId,
        @RequestBody(
            description = "Additional authentication information for the user-interactive authentication API."
        ) DeviceDeleteRequest deviceDeleteRequest,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
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
     * @param uriInfo              Request Information.
     * @param httpHeaders          Http headers.
     * @param asyncResponse        Asynchronous response.
     * @param securityContext      Security context.
     */
    @Operation(
        summary = "Deletes the given devices, and invalidates any access token associated with them.",
        description = "This API endpoint uses the User-Interactive Authentication API.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The devices were successfully removed, or had been removed previously.",
                content = @Content(
                    schema = @Schema(
                        implementation = EmptyResponse.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "401",
                description = "The homeserver requires additional authentication information.",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponse.class
                    )
                )
            )
        },
        security = {
            @SecurityRequirement(
                name = "accessToken"
            )
        },
        tags = {
            "Device management"
        }
    )
    @POST
    @Secured
    @Path("/delete_devices")
    void deleteDevices(
        @RequestBody(
            description = "JSON body request"
        ) DevicesDeleteRequest devicesDeleteRequest,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );
}
