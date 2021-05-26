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

import io.github.ma1uta.matrix.common.EmptyResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 * Checks that an Identity server is available at this API endpoint.
 *
 * @deprecated in favor of v2.
 */
@Path("/_matrix/identity/api/v1")
@Produces(MediaType.APPLICATION_JSON)
@Deprecated
public interface StatusApi {

    /**
     * To discover that an Identity server is available at a specific URL, this endpoint can be queried and will return an empty object.
     * <br>
     * This is primarly used for auto-discovery and health check purposes by entities acting as a client for the Identity server.
     * <br>
     * Return: {@link EmptyResponse}.
     * <p>Status code 200: An identity server is ready to serve requests.</p>
     *
     * @param uriInfo       Request information.
     * @param httpHeaders   Http headers.
     * @param asyncResponse Asynchronous response.
     */
    @Operation(
        summary = "To discover that an Identity server is available at a specific URL, this endpoint can be queried and will return an"
            + " empty object.",
        description = "This is primarly used for auto-discovery and health check purposes by entities acting as a client for"
            + " the Identity server.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "An identity server is ready to serve requests.",
                content = @Content(
                    schema = @Schema(
                        implementation = EmptyResponse.class
                    )
                )
            )

        }
    )
    @GET
    @Path("")
    @Deprecated
    void v1Status(
        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse
    );
}
