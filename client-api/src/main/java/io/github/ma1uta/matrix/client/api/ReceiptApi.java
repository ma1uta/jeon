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
import io.github.ma1uta.matrix.RateLimit;
import io.github.ma1uta.matrix.Secured;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

/**
 * This module adds in support for receipts. These receipts are a form of acknowledgement of an event. This module defines a single
 * acknowledgement: m.read which indicates that the user has read up to a given event.
 * <br>
 * Sending a receipt for each event can result in sending large amounts of traffic to a homeserver. To prevent this from becoming
 * a problem, receipts are implemented using "up to" markers. This marker indicates that the acknowledgement applies to all events
 * "up to and including" the event specified. For example, marking an event as "read" would indicate that the user had read all
 * events up to the referenced event.
 */
@Api(
    value = "Receipt",
    description = "This module adds in support for receipts. These receipts are a form of acknowledgement of an event. "
        + "This module defines a single acknowledgement: m.read which indicates that the user has read up to a given event."
)
@Path("/_matrix/client/r0/rooms")
@Produces(MediaType.APPLICATION_JSON)
public interface ReceiptApi {

    /**
     * Receipt types.
     */
    class Receipt {

        protected Receipt() {
        }

        /**
         * Read receipt type.
         */
        public static final String READ = "m.read";
    }

    /**
     * This API updates the marker for the given receipt type to the event ID specified.
     * <br>
     * <b>Rate-limited</b>: Yes.
     * <br>
     * <b>Requires auth</b>: Yes.
     *
     * @param roomId          Required. The room in which to send the event.
     * @param receiptType     Required. The type of receipt to send. One of: ["m.read"]
     * @param eventId         Required. The event ID to acknowledge up to.
     * @param servletRequest  Servlet request.
     * @param servletResponse Servlet response.
     * @param securityContext Security context.
     * @return <p>Status code 200: The receipt was sent.</p>
     * <p>Status code 429: This request was rate-limited.</p>
     */
    @ApiOperation(
        value = "This API updates the marker for the given receipt type to the event ID specified."
    )
    @ApiResponses( {
        @ApiResponse(code = 200, message = "The receipt was sent."),
        @ApiResponse(code = 429, message = "This request was rate-limited.")
    })
    @POST
    @RateLimit
    @Secured
    @Path("/{roomId}/receipt/{receiptType}/{eventId}")
    EmptyResponse receipt(
        @ApiParam(
            value = "The room in which to send the event.",
            required = true
        ) @PathParam("roomId") String roomId,
        @ApiParam(
            value = "The type of receipt to send.",
            required = true,
            allowableValues = "m.read"
        ) @PathParam("receiptType") String receiptType,
        @ApiParam(
            value = "The event ID to acknowledge up to.",
            required = true
        ) @PathParam("eventId") String eventId,

        @Context HttpServletRequest servletRequest,
        @Context HttpServletResponse servletResponse,
        @Context SecurityContext securityContext
    );
}
