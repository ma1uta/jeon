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

package io.github.ma1uta.matrix.server.api;

import io.github.ma1uta.matrix.server.model.serverdiscovery.ServerDiscoveryResponse;
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
 * Server discovery.
 */
@Path("/.well-known/matrix/server")
@Produces(MediaType.APPLICATION_JSON)
public interface ServerDiscoveryApi {

    /**
     * Gets information about the delegated server for server-server communication between Matrix homeservers.
     * Servers should follow 30x redirects, carefully avoiding redirect loops, and use normal X.509 certificate validation.
     * <br>
     * Return: {@link ServerDiscoveryResponse}
     * <p>Status code 200: The delegated server information. The Content-Type for this response SHOULD be application/json,
     * however servers parsing the response should assume that the body is JSON regardless of type. Failures parsing the JSON or invalid
     * data provided in the resulting parsed JSON must result in server discovery failure (no attempts should be made to continue finding
     * an IP address/port number to connect to).</p>
     *
     * @param uriInfo       Request Information.
     * @param httpHeaders   Http headers.
     * @param asyncResponse Asynchronous response.
     */
    @Operation(
        summary = "Gets discovery information about the domain.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Server discovery information.",
                content = @Content(
                    schema = @Schema(
                        implementation = ServerDiscoveryResponse.class
                    )
                )
            )
        }
    )
    @GET
    @Path("")
    void serverDiscovery(
        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse
    );
}
