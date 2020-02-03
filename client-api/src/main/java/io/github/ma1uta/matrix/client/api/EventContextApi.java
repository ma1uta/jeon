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

import io.github.ma1uta.matrix.Secured;
import io.github.ma1uta.matrix.client.model.eventcontext.EventContextResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

/**
 * This API returns a number of events that happened just before and after the specified event. This allows clients to get the context
 * surrounding an event.
 */
@Path("/_matrix/client/r0/rooms")
@Produces(MediaType.APPLICATION_JSON)
public interface EventContextApi {

    /**
     * This API returns a number of events that happened just before and after the specified event. This allows clients to get the
     * context surrounding an event.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <br>
     * Return: {@link EventContextResponse}.
     * <p>Status code 200: The events and state surrounding the requested event.</p>
     *
     * @param roomId          Required. The room to get events from.
     * @param eventId         Required. The event to get context around.
     * @param limit           The maximum number of events to return. Default: 10.
     * @param uriInfo         Request Information.
     * @param httpHeaders     Http headers.
     * @param asyncResponse   Asynchronous response.
     * @param securityContext Security context.
     */
    @Operation(
        summary = "This API returns a number of events that happened just before and after the specified event.",
        description = "This allows clients to get the context surrounding an event.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The events and state surrounding the requested event.",
                content = @Content(
                    schema = @Schema(
                        implementation = EventContextResponse.class
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
    @GET
    @Secured
    @Path("/{roomId}/context/{eventId}")
    void context(
        @Parameter(
            description = "The room to get events from.",
            required = true
        ) @PathParam("roomId") String roomId,
        @Parameter(
            description = "The event to get context around.",
            required = true
        ) @PathParam("eventId") String eventId,
        @Parameter(
            description = "The maximum number of events to return. Default: 10."
        ) @QueryParam("limit") Integer limit,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );
}
