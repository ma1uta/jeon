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

package io.github.ma1uta.matrix.application.api;

import io.github.ma1uta.matrix.ErrorResponse;
import io.github.ma1uta.matrix.Id;
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

import java.util.List;
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
 * Application services may declare which protocols they support via their registration configuration for the homeserver.
 * These networks are generally for third party services such as IRC that the application service is managing.
 * Application services may populate a Matrix room directory for their registered protocols, as defined in the Client-Server API Extensions.
 * <br>
 * Each protocol may have several "locations" (also known as "third party locations" or "3PLs"). A location within a protocol is a place
 * in the third party network, such as an IRC channel. Users of the third party network may also be represented by the application service.
 * <br>
 * Locations and users can be searched by fields defined by the application service, such as by display name or other attribute.
 * When clients request the homeserver to search in a particular "network" (protocol), the search fields will be passed along
 * to the application service for filtering.
 */
@Path("/_matrix/app/v1/thirdparty")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface ProtocolApi {

    /**
     * This API is called by the homeserver when it wants to present clients with specific information about the various third party
     * networks that an application service supports.
     * <br>
     * <b>Requires Auth</b>: Yes.
     * <br>
     * Return: {@link Protocol}.
     * <p>Status code 200: The protocol was found and metadata returned.</p>
     * <p>Status code 401: The homeserver has not supplied credentials to the application service. Optional error information can
     * be included in the body of this response.</p>
     * <p>Status code 403: The credentials supplied by the homeserver were rejected.</p>
     * <p>Status code 404: No protocol was found with the given path.The protocol is unknown.</p>
     *
     * @param protocol      Required. The name of the protocol.
     * @param uriInfo       Information about the request.
     * @param httpHeaders   Http headers.
     * @param asyncResponse Asynchronous response.
     */
    @Operation(
        summary = "This API is called by the homeserver when it wants to present clients with specific information about the various"
            + " third party networks that an application service supports.",
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
                responseCode = "401",
                description = "The homeserver has not supplied credentials to the application service."
                    + " Optional error information can be included in the body of this response.",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponse.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "403",
                description = "The credentials supplied by the homeserver were rejected.",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponse.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "404",
                description = "No protocol was found with the given path.The protocol is unknown.",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponse.class
                    )
                )
            )
        }
    )
    @Secured
    @GET
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
     * Retrieve a list of Matrix portal rooms that lead to the matched third party location.
     * <br>
     * <b>Requires Auth</b>: Yes.
     * <br>
     * Return: {@link List} of the {@link ProtocolLocation}s.
     * <p>Status code 200: At least one portal room was found.</p>
     * <p>Status code 401: The homeserver has not supplied credentials to the application service. Optional error information can
     * be included in the body of this response.</p>
     * <p>Status code 403: The credentials supplied by the homeserver were rejected.</p>
     * <p>Status code 404: No mappings were found with the given parameters.</p>
     *
     * @param protocol      Required. The protocol ID.
     * @param uriInfo       Information about the request.
     * @param httpHeaders   Http headers.
     * @param asyncResponse Asynchronous response.
     */
    @Operation(
        summary = "Retrieve a list of Matrix portal rooms that lead to the matched third party location.",
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
                responseCode = "401",
                description = "The homeserver has not supplied credentials to the application service."
                    + " Optional error information can be included in the body of this response.",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponse.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "403",
                description = "The credentials supplied by the homeserver were rejected.",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponse.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "404",
                description = "No mappings were found with the given parameters.",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponse.class
                    )
                )
            )
        }
    )
    @Secured
    @GET
    @Path("/location/{protocol}")
    void locationProtocol(
        @Parameter(
            description = "The protocol ID.",
            required = true
        ) @PathParam("protocol") String protocol,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse
    );

    /**
     * This API is called by the homeserver in order to retrieve a Matrix User ID linked to a user on the third party network,
     * given a set of user parameters.
     * <b>Required Auth</b>: Yes.
     * <br>
     * Return: {@link List} of the {@link ProtocolUser}s.
     * <p>Status code 200: The Matrix User IDs found with the given parameters.</p>
     * <p>Status code 401: The homeserver has not supplied credentials to the application service. Optional error information can
     * be included in the body of this response.</p>
     * <p>Status code 403: The credentials supplied by the homeserver were rejected.</p>
     * <p>Status code 404: No users were found with the given parameters.</p>
     *
     * @param protocol      Required. The protocol ID.
     * @param uriInfo       Information about the request.
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
                responseCode = "401",
                description = "The homeserver has not supplied credentials to the application service."
                    + " Optional error information can be included in the body of this response.",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponse.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "403",
                description = "The credentials supplied by the homeserver were rejected.",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponse.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "404",
                description = "No users were found with the given parameters.",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponse.class
                    )
                )
            )
        }
    )
    @Secured
    @GET
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
     * <b>Requires Auth</b>: Yes.
     * <br>
     * Return: {@link List} of the {@link ProtocolLocation}s.
     * <p>Status code 200: All found third party locations.</p>
     * <p>Status code 401: The homeserver has not supplied credentials to the application service. Optional error information can
     * be included in the body of this response.</p>
     * <p>Status code 403: The credentials supplied by the homeserver were rejected.</p>
     * <p>Status code 404: No mappings were found with the given parameters.</p>
     *
     * @param alias         Required. The Matrix room alias to look up.
     * @param uriInfo       Information about the request.
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
        }
    )
    @Secured
    @GET
    @Path("/location")
    void location(
        @Parameter(
            description = "The Matrix room alias to look up.",
            required = true
        ) @QueryParam("alias") Id alias,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse
    );

    /**
     * Retrieve an array of third party users from a Matrix User ID.
     * <br>
     * <b>Requires Auth</b>: Yes.
     * <br>
     * Return: {@link List} of the {@link ProtocolUser}s.
     * <p>Status code 200: An array of third party users.</p>
     * <p>Status code 401: The homeserver has not supplied credentials to the application service. Optional error information can
     * be included in the body of this response.</p>
     * <p>Status code 403: The credentials supplied by the homeserver were rejected.</p>
     * <p>Status code 404: No mappings were found with the given parameters.</p>
     *
     * @param userId        Required. The Matrix User ID to look up.
     * @param uriInfo       Information about the request.
     * @param httpHeaders   Http headers.
     * @param asyncResponse Asynchronous response.
     */
    @Operation(
        summary = "Retrieve an array of third party users from a Matrix User ID.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "An array of third party users.",
                content = @Content(
                    array = @ArraySchema(
                        schema = @Schema(
                            implementation = ProtocolUser.class
                        )
                    )
                )
            ),
            @ApiResponse(
                responseCode = "401",
                description = "The homeserver has not supplied credentials to the application service."
                    + " Optional error information can be included in the body of this response.",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponse.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "403",
                description = "The credentials supplied by the homeserver were rejected.",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponse.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "404",
                description = "No mappings were found with the given parameters.",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponse.class
                    )
                )
            )
        }
    )
    @Secured
    @GET
    @Path("/user")
    void user(
        @Parameter(
            description = "The Matrix User Id to look up",
            required = true
        ) @QueryParam("userid") Id userId,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse
    );
}
