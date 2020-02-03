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

import io.github.ma1uta.matrix.ErrorResponse;
import io.github.ma1uta.matrix.RateLimit;
import io.github.ma1uta.matrix.RateLimitedErrorResponse;
import io.github.ma1uta.matrix.Secured;
import io.github.ma1uta.matrix.client.model.search.SearchRequest;
import io.github.ma1uta.matrix.client.model.search.SearchResponse;
import io.github.ma1uta.matrix.thirdpid.SessionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
 * The search API allows clients to perform full text search across events in all rooms that the user has been in, including those
 * that they have left. Only events that the user is allowed to see will be searched, e.g. it won't include events in rooms that
 * happened after you left.
 */
@Path("/_matrix/client/r0")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface SearchApi {

    /**
     * Performs a full text search across different categories.
     * <br>
     * <b>Rate-limited</b>: Yes.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <br>
     * Return: {@link SearchResponse}.
     * <p>Status code 200: Results of the search.</p>
     * <p>Status code 400: Part of the request was invalid.</p>
     * <p>Status code 429: This request was rate-limited.</p>
     *
     * @param nextBatch       The point to return events from. If given, this should be a next_batch result from a previous call
     *                        to this endpoint.
     * @param searchRequest   JSON body request.
     * @param uriInfo         Request Information.
     * @param httpHeaders     Http headers.
     * @param asyncResponse   Asynchronous response.
     * @param securityContext Security context.
     */
    @Operation(
        summary = "Performs a full text search across different categories.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Results of the search.",
                content = @Content(
                    schema = @Schema(
                        implementation = SessionResponse.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Part of the request was invalid.",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponse.class
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
            "Search"
        }
    )
    @POST
    @RateLimit
    @Secured
    @Path("/search")
    void search(
        @Parameter(
            description = "The point to return events from. If given, this should be a next_batch result from a previous call "
                + "to this endpoint."
        ) @QueryParam("next_batch") String nextBatch,
        @RequestBody(
            description = "JSON body request"
        ) SearchRequest searchRequest,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );
}
