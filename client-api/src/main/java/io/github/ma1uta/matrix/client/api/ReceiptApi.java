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
import io.github.ma1uta.matrix.RateLimit;
import io.github.ma1uta.matrix.RateLimitedErrorResponse;
import io.github.ma1uta.matrix.Secured;
import io.github.ma1uta.matrix.client.model.receipt.ReadMarkersRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import javax.ws.rs.POST;
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
 * This module adds in support for receipts. These receipts are a form of acknowledgement of an event. This module defines a single
 * acknowledgement: m.read which indicates that the user has read up to a given event.
 * <br>
 * Sending a receipt for each event can result in sending large amounts of traffic to a homeserver. To prevent this from becoming
 * a problem, receipts are implemented using "up to" markers. This marker indicates that the acknowledgement applies to all events
 * "up to and including" the event specified. For example, marking an event as "read" would indicate that the user had read all
 * events up to the referenced event.
 */
@Path("/_matrix/client/r0/rooms")
@Produces(MediaType.APPLICATION_JSON)
public interface ReceiptApi {

    /**
     * ReceiptContent types.
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
     * <br>
     * Return: {@link EmptyResponse}.
     * <p>Status code 200: The receipt was sent.</p>
     * <p>Status code 429: This request was rate-limited.</p>
     *
     * @param roomId          Required. The room in which to send the event.
     * @param receiptType     Required. The type of receipt to send. One of: ["m.read"]
     * @param eventId         Required. The event ID to acknowledge up to.
     * @param uriInfo         Request Information.
     * @param httpHeaders     Http headers.
     * @param asyncResponse   Asynchronous response.
     * @param securityContext Security context.
     */
    @Operation(
        summary = "This API updates the marker for the given receipt type to the event ID specified.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The receipt was sent.",
                content = @Content(
                    schema = @Schema(
                        implementation = EmptyResponse.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "429",
                description = "This request was rate-limited.",
                content = @Content(
                    schema = @Schema(
                        implementation = RateLimitedErrorResponse.class
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
            "Room participation"
        }
    )
    @POST
    @RateLimit
    @Secured
    @Path("/{roomId}/receipt/{receiptType}/{eventId}")
    void receipt(
        @Parameter(
            description = "The room in which to send the event.",
            required = true
        ) @PathParam("roomId") String roomId,
        @Parameter(
            description = "The type of receipt to send.",
            required = true,
            schema = @Schema(
                allowableValues = {
                    "m.read"
                }
            )
        ) @PathParam("receiptType") String receiptType,
        @Parameter(
            description = "The event ID to acknowledge up to.",
            required = true
        ) @PathParam("eventId") String eventId,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );

    /**
     * Sets the position of the read marker for a given room, and optionally the read receipt's location.
     * <br>
     * <b>Rate-limited</b>: Yes.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <br>
     * Return: {@link EmptyResponse}.
     * <p>Status code 200: The read marker, and read receipt if provided, have been updated.</p>
     * <p>Status code 429: This request was rate-limited.</p>
     *
     * @param roomId          Required. The room ID to set the read marker in for the user.
     * @param request         JSON body request.
     * @param uriInfo         Request Information.
     * @param httpHeaders     Http headers.
     * @param asyncResponse   Asynchronous response.
     * @param securityContext Security context.
     */
    @Operation(
        summary = "Sets the position of the read marker for a given room, and optionally the read receipt's location.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The read marker, and read receipt if provided, have been updated.",
                content = @Content(
                    schema = @Schema(
                        implementation = EmptyResponse.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "429",
                description = "This request was rate-limited.",
                content = @Content(
                    schema = @Schema(
                        implementation = RateLimitedErrorResponse.class
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
            "Read Markers"
        }
    )
    @POST
    @Secured
    @RateLimit
    @Path("/{roomId}/read_markers")
    void readMarkers(
        @Parameter(
            description = "The room ID to set the read marker in for the user.",
            required = true
        ) @PathParam("roomId") String roomId,
        @RequestBody(
            description = "JSON body request."
        ) ReadMarkersRequest request,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );
}
