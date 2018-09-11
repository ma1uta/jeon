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
import io.github.ma1uta.matrix.client.model.report.ReportRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

/**
 * Users may encounter content which they find inappropriate and should be able to report it to the server administrators or room
 * moderators for review. This module defines a way for users to report content.
 * <br>
 * Content is reported based upon a negative score, where -100 is "most offensive" and 0 is "inoffensive".
 */
@Api(
    value = "Report",
    description = "Users may encounter content which they find inappropriate and should be able to report it to "
        + "the server administrators or room moderators for review. This module defines a way for users to report content."
)
@Path("/_matrix/client/r0")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface ReportApi {

    /**
     * Reports an event as inappropriate to the server, which may then notify the appropriate people.
     * <br>
     * <b>Requires auth</b>: Yes.
     *
     * @param roomId          Required. The room in which the event being reported is located.
     * @param eventId         Required. The event to report.
     * @param reportRequest   JSON body request.
     * @param servletRequest  Servlet request.
     * @param servletResponse Servlet response.
     * @param securityContext Security context.
     * @return <p>Status code 200: The event has been reported successfully.</p>
     */
    @ApiOperation(
        value = "Reports an event as inappropriate to the server, which may then notify the appropriate people."
    )
    @ApiResponses( {
        @ApiResponse(code = 200, message = "The event has been reported successfully.")
    })
    @POST
    @Secured
    @Path("/rooms/{roomId}/report/{eventId}")
    EmptyResponse report(
        @ApiParam(
            value = "The room in which the event being reported is located.",
            required = true
        ) @PathParam("roomId") String roomId,
        @ApiParam(
            value = "The event to report.",
            required = true
        ) @PathParam("eventId") String eventId,
        @ApiParam(
            value = "JSON body request."
        ) ReportRequest reportRequest,

        @Context HttpServletRequest servletRequest,
        @Context HttpServletResponse servletResponse,
        @Context SecurityContext securityContext
    );
}
