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

package io.github.ma1uta.matrix.identity.api.deprecated;

import io.github.ma1uta.matrix.identity.model.lookup.BulkLookupRequest;
import io.github.ma1uta.matrix.identity.model.lookup.BulkLookupResponse;
import io.github.ma1uta.matrix.identity.model.lookup.SingleLookupResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 * Association lookup.
 *
 * @deprecated in favor of v2.
 */
@Path("/_matrix/identity/api/v1")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Deprecated
public interface LookupApi {

    /**
     * Look up the Matrix user ID for a 3pid.
     * <br>
     * Return: {@link SingleLookupResponse}.
     * <p>Status code 200: The association for that 3pid, or the empty object if no association is known.</p>
     *
     * @param medium        Required. The medium type of the 3pid. See the 3PID Types Appendix.
     * @param address       Required. The address of the 3pid being looked up. See the 3PID Types Appendix.
     * @param uriInfo       Request information.
     * @param httpHeaders   Http headers.
     * @param asyncResponse Asynchronous response.
     */
    @Operation(
        summary = "Look up the Matrix user ID for a 3pid.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The association for that 3pid, or an empty object if no association is known.",
                content = @Content(
                    schema = @Schema(
                        implementation = SingleLookupResponse.class
                    )
                )
            )

        }
    )
    @GET
    @Path("/lookup")
    @Deprecated
    void lookup(
        @Parameter(
            name = "medium",
            description = "The medium type of the 3pid. See the 3PID Types Appendix.",
            required = true
        ) @QueryParam("medium") String medium,
        @Parameter(
            name = "address",
            description = "The address of the 3pid being looked up. See the 3PID Types Appendix.",
            required = true
        ) @QueryParam("address") String address,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse
    );

    /**
     * Lookup Matrix user IDs for a list of 3pids.
     * <br>
     * Return: {@link BulkLookupResponse}.
     * <p>Status code 200: A list of known 3PID mappings for the supplied 3PIDs.</p>
     *
     * @param request       Required. An array of arrays containing the 3PID Types with the medium in first position and the
     *                      address in second position.
     * @param uriInfo       Request information.
     * @param httpHeaders   Http headers.
     * @param asyncResponse Asynchronous response.
     */
    @Operation(
        summary = "Lookup Matrix user IDs for a list of 3pids.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "A list of known 3PID mappings for the supplied 3PIDs.",
                content = @Content(
                    schema = @Schema(
                        implementation = BulkLookupResponse.class
                    )
                )
            )

        }
    )
    @POST
    @Path("/bulk_lookup")
    @Deprecated
    void bulkLookup(
        @Parameter(
            description = "An array of arrays containing the 3PID Types with the medium in first position and the address"
                + " in second position.",
            required = true
        ) BulkLookupRequest request,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse
    );
}
