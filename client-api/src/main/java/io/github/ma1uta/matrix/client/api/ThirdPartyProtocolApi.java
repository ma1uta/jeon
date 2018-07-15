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

import io.github.ma1uta.matrix.protocol.Protocol;
import io.github.ma1uta.matrix.protocol.ProtocolLocation;
import io.github.ma1uta.matrix.protocol.ProtocolUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 * Application services can provide access to third party networks via bridging. This allows Matrix users to communicate with users
 * on other communication platforms, with messages ferried back and forth by the application service. A single application service
 * may bridge multiple third party networks, and many individual locations within those networks. A single third party network
 * location may be bridged to multiple Matrix rooms.
 */
@Api(value = "ThirdPartyProtocol", description = "Application services can provide access to third party networks via bridging."
    + "This allows Matrix users to communicate with users on other communication platforms, with messages ferried back and forth"
    + "by the application service. A single application service may bridge multiple third party networks, and many individual"
    + "locations within those networks. A single third party network location may be bridged to multiple Matrix rooms.")
@Path("/_matrix/client/r0/thirdparty")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface ThirdPartyProtocolApi {

    /**
     * Fetches the overall metadata about protocols supported by the homeserver. Includes both the available protocols and all
     * fields required for queries against each protocol.
     *
     * @param servletRequest  servlet request.
     * @param servletResponse servlet response.
     * @return Status code 200: The protocols supported by the homeserver.
     */
    @ApiOperation(value = "Fetches the overall metadata about protocols supported by the homeserver."
        + "Includes both the available protocols and all fields required for queries against each protocol.")
    @ApiResponses( {
        @ApiResponse(code = 200, message = "The protocols supported by the homeserver.")
    })
    @GET
    @Path("/protocols")
    Map<String, Protocol> protocols(@Context HttpServletRequest servletRequest, @Context HttpServletResponse servletResponse);

    /**
     * Fetches the metadata from the homeserver about a particular third party protocol.
     *
     * @param protocol        Required. The name of the protocol.
     * @param servletRequest  servlet request.
     * @param servletResponse servlet response.
     * @return Status code 200: The protocol was found and metadata returned.
     *     Status code 404: The protocol is unknown.
     */
    @ApiOperation(value = "Fetches the metadata from the homeserver about a particular third party protocol.", response = Protocol.class)
    @ApiResponses( {
        @ApiResponse(code = 200, message = "The protocol was found and metadata returned."),
        @ApiResponse(code = 404, message = "The protocol is unknown.")
    })
    @GET
    @Path("/protocol/{protocol}")
    Protocol protocol(
        @ApiParam(value = "the name of the protocol", required = true) @PathParam("protocol") String protocol,
        @Context HttpServletRequest servletRequest, @Context HttpServletResponse servletResponse);

    /**
     * Requesting this endpoint with a valid protocol name results in a list of successful mapping results in a JSON array.
     * Each result contains objects to represent the Matrix room or rooms that represent a portal to this third party network.
     * Each has the Matrix room alias string, an identifier for the particular third party network protocol, and an object
     * containing the network-specific fields that comprise this identifier. It should attempt to canonicalise the identifier
     * as much as reasonably possible given the network type.
     *
     * @param protocol        Required. The protocol used to communicate to the third party network.
     * @param searchFields    One or more custom fields to help identify the third party location.
     * @param servletRequest  servlet request.
     * @param servletResponse servlet response.
     * @return Status code 200: At least one portal room was found.
     *     Status code 404: No portal rooms were found.
     */
    @ApiOperation(value = "Requesting this endpoint with a valid protocol name results in a list of successful mapping results"
        + "in a JSON array.",
        notes = "Each result contains objects to represent the Matrix room or rooms that represent a portal to this third party network."
            + " Each has the Matrix room alias string, an identifier for the particular third party network protocol, and an object"
            + " containing the network-specific fields that comprise this identifier.It should attempt to canonicalise the identifier"
            + " as much as reasonably possible given the network type.")
    @ApiResponses( {
        @ApiResponse(code = 200, message = "At least one portal room was found."),
        @ApiResponse(code = 404, message = "No portal rooms were found.")
    })
    @GET
    @Path("/location/{protocol}")
    List<ProtocolLocation> locationProtocol(
        @ApiParam(value = "The protocol used to communicate to the third party network.", required = true)
        @PathParam("protocol") String protocol,
        @ApiParam("One or more custom fields to help identify the third party location.") @QueryParam("searchFields") String searchFields,
        @Context HttpServletRequest servletRequest, @Context HttpServletResponse servletResponse);

    /**
     * Retrieve a Matrix User ID linked to a user on the third party service, given a set of user parameters.
     *
     * @param protocol        Required. The name of the protocol.
     * @param uriInfo         uri info to retrieve all query params.
     * @param servletRequest  servlet request.
     * @param servletResponse servlet response.
     * @return Status code 200: The Matrix User IDs found with the given parameters.
     *     Status code 404: The Matrix User ID was not found.
     */
    @ApiOperation("Retrieve a Matrix User ID linked to a user on the third party service, given a set of user parameters.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "The Matrix User IDs found with the given parameters."),
        @ApiResponse(code = 404, message = "The Matrix User ID was not found.")
    })
    @GET
    @Path("/user/{protocol}")
    List<ProtocolUser> userProtocol(
        @ApiParam(value = "The name of the protocol", required = true) @PathParam("protocol") String protocol,
        @Context UriInfo uriInfo, @Context HttpServletRequest servletRequest, @Context HttpServletResponse servletResponse);

    /**
     * Retreive an array of third party network locations from a Matrix room alias.
     *
     * @param alias           Required. The Matrix room alias to look up.
     * @param servletRequest  servlet request.
     * @param servletResponse servlet response.
     * @return Status code 200: At least one portal room was found.
     *     Status code 404: No portal rooms were found.
     */
    @ApiOperation("Retreive an array of third party network locations from a Matrix room alias.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "At least one portal room was found."),
        @ApiResponse(code = 404, message = "No portal rooms were found.")
    })
    @GET
    @Path("/location")
    List<ProtocolLocation> location(
        @QueryParam("alias") String alias,
        @Context HttpServletRequest servletRequest, @Context HttpServletResponse servletResponse);

    /**
     * Retreive an array of third party users from a Matrix User ID.
     *
     * @param userId          Required. The Matrix User ID to look up.
     * @param servletRequest  servlet request.
     * @param servletResponse servlet response.
     * @return Status code 200: The Matrix User IDs found with the given parameters.
     *     Status code 404: The Matrix User ID was not found.
     */
    @ApiOperation("Retreive an array of third party users from a Matrix User ID.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "The Matrix User IDs found with the given parameters."),
        @ApiResponse(code = 404, message = "The Matrix User ID was not found.")
    })
    @GET
    @Path("/user")
    List<ProtocolUser> user(
        @ApiParam(value = "The Matrix User Id to look up", required = true) @QueryParam("userid") String userId,
        @Context HttpServletRequest servletRequest, @Context HttpServletResponse servletResponse);
}
