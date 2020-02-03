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
import io.github.ma1uta.matrix.Secured;
import io.github.ma1uta.matrix.client.model.sendtodevice.SendToDeviceRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import javax.ws.rs.Consumes;
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
 * This module provides a means by which clients can exchange signalling messages without them being stored permanently as part of
 * a shared communication history. A message is delivered exactly once to each client device.
 * <br>
 * The primary motivation for this API is exchanging data that is meaningless or undesirable to persist in the room DAG - for example,
 * one-time authentication tokens or key data. It is not intended for conversational data, which should be sent using the normal
 * /rooms/&lt;room_id&gt;/send API for consistency throughout Matrix.
 */
@Path("/_matrix/client/r0/sendToDevice")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface SendToDeviceApi {

    /**
     * This endpoint is used to send send-to-device events to a set of client devices.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <br>
     * Return: {@link EmptyResponse}.
     * <p>Status code 200: The message was successfully sent.</p>
     *
     * @param eventType           Required. The type of event to send.
     * @param txnId               Required. The transaction ID for this event. Clients should generate an ID unique across requests with the
     *                            same access token; it will be used by the server to ensure idempotency of requests.
     * @param sendToDeviceRequest Request body.
     * @param uriInfo             Request Information.
     * @param httpHeaders         Http headers.
     * @param asyncResponse       Asynchronous response.
     * @param securityContext     Security context.
     */
    @Operation(
        summary = "This endpoint is used to send send-to-device events to a set of client devices.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The message was successfully sent.",
                content = @Content(
                    schema = @Schema(
                        implementation = EmptyResponse.class
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
            "Send-to Device messaging"
        }
    )
    @PUT
    @Secured
    @Path("/{eventType}/{txnId}")
    void send(
        @Parameter(
            description = "The type of event to send.",
            required = true
        ) @PathParam("eventType") String eventType,
        @Parameter(
            description = "The transaction ID for this event. Clients should generate an ID unique across requests with the "
                + "same access token; it will be used by the server to ensure idempotency of requests.",
            required = true
        ) @PathParam("txnId") String txnId,
        @RequestBody(
            description = "JSON resuest body"
        ) SendToDeviceRequest sendToDeviceRequest,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );
}
