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

import io.github.ma1uta.matrix.client.model.version.VersionsResponse;
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
 * Gets the versions of the specification supported by the server.
 * <br>
 * Values will take the form rX.Y.Z.
 * <br>
 * Only the latest Z value will be reported for each supported X.Y value.
 * i.e. if the server implements r0.0.0, r0.0.1, and r1.2.0, it will report r0.0.1 and r1.2.0.
 */
@Path("/_matrix/client/versions")
@Produces(MediaType.APPLICATION_JSON)
public interface VersionApi {

    /**
     * Gets the versions of the specification supported by the server.
     * <br>
     * Return: {@link VersionsResponse}.
     * <p>Status code 200: The versions supported by the server.</p>
     *
     * @param uriInfo       Request Information.
     * @param httpHeaders   Http headers.
     * @param asyncResponse Asynchronous response.
     */
    @Operation(
        summary = "Gets the versions of the specification supported by the server.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The versions supported by the server.",
                content = @Content(
                    schema = @Schema(
                        implementation = VersionsResponse.class
                    )
                )
            )
        },
        tags = {
            "Server administration"
        }
    )
    @GET
    @Path("/")
    void versions(
        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse
    );
}
