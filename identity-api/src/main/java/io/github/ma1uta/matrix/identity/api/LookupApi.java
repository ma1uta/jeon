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

package io.github.ma1uta.matrix.identity.api;

import io.github.ma1uta.matrix.identity.model.lookup.BulkLookupRequest;
import io.github.ma1uta.matrix.identity.model.lookup.BulkLookupResponse;
import io.github.ma1uta.matrix.identity.model.lookup.LookupResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * Association lookup.
 */
@Api(
    value = "AssociationLookup"
)
@Path("/_matrix/identity/api/v1")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface LookupApi {

    /**
     * Look up the Matrix user ID for a 3pid.
     *
     * @param medium          Required. The medium type of the 3pid. See the 3PID Types Appendix.
     * @param address         Required. The address of the 3pid being looked up. See the 3PID Types Appendix.
     * @param servletRequest  Servlet request.
     * @param servletResponse Servlet response.
     * @return <p>Status code 200: The association for that 3pid, or the empty object if no association is known.</p>
     */
    @ApiOperation(
        value = "Look up the Matrix user ID for a 3pid."
    )
    @ApiResponses( {
        @ApiResponse(code = 200, message = "The association for that 3pid, or an empty object if no association is known.")
    })
    @GET
    @Path("/lookup")
    LookupResponse lookup(
        @ApiParam(
            name = "medium",
            value = "The medium type of the 3pid. See the 3PID Types Appendix.",
            required = true
        ) @QueryParam("medium") String medium,
        @ApiParam(
            name = "address",
            value = "The address of the 3pid being looked up. See the 3PID Types Appendix.",
            required = true
        ) @QueryParam("address") String address,

        @Context HttpServletRequest servletRequest,
        @Context HttpServletResponse servletResponse
    );

    /**
     * Lookup Matrix user IDs for a list of 3pids.
     *
     * @param request         Required. An array of arrays containing the 3PID Types with the medium in first position and the
     *                        address in second position.
     * @param servletRequest  Servlet request.
     * @param servletResponse Servlet response.
     * @return <p>Status code 200: A list of known 3PID mappings for the supplied 3PIDs.</p>
     */
    @ApiOperation(
        value = "Lookup Matrix user IDs for a list of 3pids."
    )
    @ApiResponses( {
        @ApiResponse(code = 200, message = "A list of known 3PID mappings for the supplied 3PIDs.")
    })
    @POST
    @Path("/bulk_lookup")
    BulkLookupResponse bulkLookup(
        @ApiParam(
            value = "An array of arrays containing the 3PID Types with the medium in first position and the address in second position.",
            required = true
        ) BulkLookupRequest request,

        @Context HttpServletRequest servletRequest,
        @Context HttpServletResponse servletResponse
    );
}
