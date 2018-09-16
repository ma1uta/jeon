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
import io.github.ma1uta.matrix.client.model.sendtodevice.SendToDeviceRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
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
 * This module provides a means by which clients can exchange signalling messages without them being stored permanently as part of
 * a shared communication history. A message is delivered exactly once to each client device.
 * <br>
 * The primary motivation for this API is exchanging data that is meaningless or undesirable to persist in the room DAG - for example,
 * one-time authentication tokens or key data. It is not intended for conversational data, which should be sent using the normal
 * /rooms/&lt;room_id&gt;/send API for consistency throughout Matrix.
 */
@Api(
    value = "SendToDevice",
    description = "This module provides a means by which clients can exchange signalling messages without them "
        + "being stored permanently as part of a shared communication history. A message is delivered exactly once to each client device."
)
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
     * @param servletRequest      Servlet requet.
     * @param asyncResponse       Asynchronous response.
     * @param securityContext     Security context.
     */
    @ApiOperation(
        value = "This endpoint is used to send send-to-device events to a set of client devices.",
        response = EmptyResponse.class
    )
    @ApiResponses( {
        @ApiResponse(code = 200, message = "The message was successfully sent.")
    })
    @PUT
    @Secured
    @Path("/{eventType}/{txnId}")
    void send(
        @ApiParam(
            value = "The type of event to send.",
            required = true
        ) @PathParam("eventType") String eventType,
        @ApiParam(
            value = "The transaction ID for this event. Clients should generate an ID unique across requests with the "
                + "same access token; it will be used by the server to ensure idempotency of requests.",
            required = true
        ) @PathParam("txnId") String txnId,
        @ApiParam(
            value = "JSON resuest body"
        ) SendToDeviceRequest sendToDeviceRequest,

        @Context HttpServletRequest servletRequest,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );
}
