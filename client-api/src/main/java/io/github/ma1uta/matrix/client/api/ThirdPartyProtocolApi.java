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
import io.github.ma1uta.matrix.protocol.Protocol;
import io.github.ma1uta.matrix.protocol.ProtocolLocation;
import io.github.ma1uta.matrix.protocol.ProtocolUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.List;
import java.util.Map;
import javax.ws.rs.Consumes;
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
import javax.ws.rs.core.UriInfo;

/**
 * Application services can provide access to third party networks via bridging. This allows Matrix users to communicate with users
 * on other communication platforms, with messages ferried back and forth by the application service. A single application service
 * may bridge multiple third party networks, and many individual locations within those networks. A single third party network
 * location may be bridged to multiple Matrix rooms.
 */
@Path("/_matrix/client/r0/thirdparty")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface ThirdPartyProtocolApi {

    /**
     * Fetches the overall metadata about protocols supported by the homeserver. Includes both the available protocols and all
     * fields required for queries against each protocol.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <br>
     * Return: {@link Map} from the {@link String} to the {@link Protocol}.
     * <p>Status code 200: The protocols supported by the homeserver.</p>
     *
     * @param uriInfo       Request Information.
     * @param httpHeaders   Http headers.
     * @param asyncResponse Asynchronous response.
     */
    @Operation(
        summary = "Fetches the overall metadata about protocols supported by the homeserver."
            + "Includes both the available protocols and all fields required for queries against each protocol.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The protocols supported by the homeserver.",
                content = @Content(
                    schema = @Schema(
                        implementation = Map.class
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
            "Third party protocols"
        }
    )
    @GET
    @Secured
    @Path("/protocols")
    void protocols(
        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse
    );

    /**
     * Fetches the metadata from the homeserver about a particular third party protocol.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <br>
     * Return: {@link Protocol}.
     * <p>Status code 200: The protocol was found and metadata returned.</p>
     * <p>Status code 404: The protocol is unknown.</p>
     *
     * @param protocol      Required. The name of the protocol.
     * @param uriInfo       Request Information.
     * @param httpHeaders   Http headers.
     * @param asyncResponse Asynchronous response.
     */
    @Operation(
        summary = "Fetches the metadata from the homeserver about a particular third party protocol.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The protocol was found and metadata returned.",
                content = @Content(
                    schema = @Schema(
                        implementation = Protocol.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "404",
                description = "The protocol is unknown.",
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
            "Third party protocols"
        }
    )
    @GET
    @Secured
    @Path("/protocol/{protocol}")
    void protocol(
        @Parameter(
            description = "the name of the protocol",
            required = true
        ) @PathParam("protocol") String protocol,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse
    );

    /**
     * Requesting this endpoint with a valid protocol name results in a list of successful mapping results in a JSON array.
     * Each result contains objects to represent the Matrix room or rooms that represent a portal to this third party network.
     * Each has the Matrix room alias string, an identifier for the particular third party network protocol, and an object
     * containing the network-specific fields that comprise this identifier. It should attempt to canonicalise the identifier
     * as much as reasonably possible given the network type.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <br>
     * Return: {@link List} of the {@link ProtocolLocation}s.
     * <p>Status code 200: At least one portal room was found.</p>
     * <p>Status code 404: No portal rooms were found.</p>
     *
     * @param protocol      Required. The protocol used to communicate to the third party network.
     * @param uriInfo       Request Information.
     * @param httpHeaders   Http headers.
     * @param asyncResponse Asynchronous response.
     */
    @Operation(
        summary = "Requesting this endpoint with a valid protocol name results in a list of successful mapping results"
            + "in a JSON array.",
        description = "Each result contains objects to represent the Matrix room or rooms that represent a portal to this third party"
            + " network. Each has the Matrix room alias string, an identifier for the particular third party network protocol, and"
            + " an object containing the network-specific fields that comprise this identifier.It should attempt to canonicalise"
            + " the identifier as much as reasonably possible given the network type.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "At least one portal room was found.",
                content = @Content(
                    array = @ArraySchema(
                        schema = @Schema(
                            implementation = ProtocolLocation.class
                        )
                    )
                )
            ),
            @ApiResponse(
                responseCode = "404",
                description = "No portal rooms were found.",
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
            "Third party protocols"
        }
    )
    @GET
    @Secured
    @Path("/location/{protocol}")
    void locationProtocol(
        @Parameter(
            description = "The protocol used to communicate to the third party network.",
            required = true
        ) @PathParam("protocol") String protocol,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse
    );

    /**
     * Retrieve a Matrix User ID linked to a user on the third party service, given a set of user parameters.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <br>
     * Return: {@link List} of the {@link ProtocolUser}s.
     * <p>Status code 200: The Matrix User IDs found with the given parameters.</p>
     * <p>Status code 404: The Matrix User ID was not found.</p>
     *
     * @param protocol      Required. The name of the protocol.
     * @param uriInfo       Request Information.
     * @param httpHeaders   Http headers.
     * @param asyncResponse Asynchronous response.
     */
    @Operation(
        summary = "Retrieve a Matrix User ID linked to a user on the third party service, given a set of user parameters.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The Matrix User IDs found with the given parameters.",
                content = @Content(
                    array = @ArraySchema(
                        schema = @Schema(
                            implementation = ProtocolUser.class
                        )
                    )
                )
            ),
            @ApiResponse(
                responseCode = "404",
                description = "The Matrix User ID was not found.",
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
            "Third party protocols"
        }
    )
    @GET
    @Secured
    @Path("/user/{protocol}")
    void userProtocol(
        @Parameter(
            description = "The name of the protocol",
            required = true
        ) @PathParam("protocol") String protocol,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse
    );

    /**
     * Retrieve an array of third party network locations from a Matrix room alias.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <br>
     * Return: {@link List} of the {@link ProtocolLocation}s.
     * <p>Status code 200: At least one portal room was found.</p>
     * <p>Status code 404: No portal rooms were found.</p>
     *
     * @param alias         Required. The Matrix room alias to look up.
     * @param uriInfo       Request Information.
     * @param httpHeaders   Http headers.
     * @param asyncResponse Asynchronous response.
     */
    @Operation(
        summary = "Retrieve an array of third party network locations from a Matrix room alias.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "At least one portal room was found.",
                content = @Content(
                    array = @ArraySchema(
                        schema = @Schema(
                            implementation = ProtocolLocation.class
                        )
                    )
                )
            ),
            @ApiResponse(
                responseCode = "404",
                description = "No portal rooms were found.",
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
            "Third party protocols"
        }
    )
    @GET
    @Secured
    @Path("/location")
    void location(
        @Parameter(
            description = "The Matrix room alias to look up",
            required = true
        ) @QueryParam("alias") String alias,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse
    );

    /**
     * Retrieve an array of third party users from a Matrix User ID.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <br>
     * Return: {@link List} of the {@link ProtocolUser}s.
     * <p>Status code 200: The Matrix User IDs found with the given parameters.</p>
     * <p>Status code 404: The Matrix User ID was not found.</p>
     *
     * @param userId        Required. The Matrix User ID to look up.
     * @param uriInfo       Request Information.
     * @param httpHeaders   Http headers.
     * @param asyncResponse Asynchronous response.
     */
    @Operation(
        summary = "Retrieve an array of third party users from a Matrix User ID.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The Matrix User IDs found with the given parameters.",
                content = @Content(
                    array = @ArraySchema(
                        schema = @Schema(
                            implementation = ProtocolUser.class
                        )
                    )
                )
            ),
            @ApiResponse(
                responseCode = "404",
                description = "The Matrix User ID was not found.",
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
            "Third party protocols"
        }
    )
    @GET
    @Secured
    @Path("/user")
    void user(
        @Parameter(
            description = "The Matrix User String to look up",
            required = true
        ) @QueryParam("userid") String userId,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse
    );
}
