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
import io.github.ma1uta.matrix.Secured;
import io.github.ma1uta.matrix.client.model.filter.FilterData;
import io.github.ma1uta.matrix.client.model.filter.FilterResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
 * Filters can be created on the server and can be passed as as a parameter to APIs which return events. These filters alter the
 * data returned from those APIs. Not all APIs accept filters.
 */
@Path("/_matrix/client/r0/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface FilterApi {

    /**
     * Uploads a new filter definition to the homeserver. Returns a filter ID that may be used in future requests to restrict which
     * events are returned to the client.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <br>
     * Return: {@link FilterResponse}.
     * <p>Status code 200: The filter was created.</p>
     *
     * @param userId          Required. The id of the user uploading the filter. The access token must be authorized to make requests for
     *                        this user id.
     * @param filterData      JSON body parameters.
     * @param uriInfo         Request Information.
     * @param httpHeaders     Http headers.
     * @param asyncResponse   Asynchronous response.
     * @param securityContext Security context.
     */
    @Operation(
        summary = "Uploads a new filter definition to the homeserver.",
        description = "Returns a filter ID that may be used in future requests to restrict which events are returned to the client.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The filter was created.",
                content = @Content(
                    schema = @Schema(
                        implementation = FilterResponse.class
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
    @Secured
    @Path("/{userId}/filter")
    default void uploadFilter(
        @Parameter(
            description = "The id of the user uploading the filter. The access token must be authorized to make requests for this user id.",
            required = true
        ) @PathParam("userId") String userId,
        @RequestBody(
            description = "JSON body parameters"
        ) FilterData filterData,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    ) {

    }

    /**
     * Download a filter.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <br>
     * Return: {@link FilterData}.
     * <p>Status code 200: "The filter defintion".</p>
     * <p>Status code 404: Unknown filter.</p>
     *
     * @param userId          Required. The user ID to download a filter for.
     * @param filterId        Required. The filter ID to download.
     * @param uriInfo         Request Information.
     * @param httpHeaders     Http headers.
     * @param asyncResponse   Asynchronous response.
     * @param securityContext Security context.
     */
    @Operation(
        summary = "Download a filter.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The filter definition.",
                content = @Content(
                    schema = @Schema(
                        implementation = FilterData.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Unknown filter.",
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
            "Room participation"
        }
    )
    @GET
    @Secured
    @Path("/{userId}/filter/{filterId}")
    void getFilter(
        @Parameter(
            description = "The user ID to download a filter for.",
            required = true
        ) @PathParam("userId") String userId,
        @Parameter(
            description = "The filter ID to download.",
            required = true
        ) @PathParam("filterId") String filterId,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );
}
