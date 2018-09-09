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

import io.github.ma1uta.matrix.EmptyResponse;
import io.github.ma1uta.matrix.application.model.TransactionRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * Application service APIs which are used by the homeserver. All application services MUST implement these APIs.
 */
@Api(
    value = "ApplicationService",
    description = "All application services MUST implement these APIs."
)
@Path("/v1")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface ApplicationApi {

    /**
     * This API is called by the homeserver when it wants to push an event (or batch of events) to the application service.
     * <br>
     * Note that the application service should distinguish state events from message events via the presence of a state_key,
     * rather than via the event type.
     *
     * @param txnId           Required. The transaction ID for this set of events. Homeservers generate these IDs and they are used to
     *                        ensure idempotency of requests.
     * @param request         JSON body request.
     * @param servletRequest  Servlet request.
     * @param servletResponse Servlet response.
     * @return <p>Status code 200: The transaction was processed successfully.</p>
     */
    @ApiOperation(
        value = "This API is called by the homeserver when it wants to push an event (or batch of events) to the application service.",
        notes = "Note that the application service should distinguish state events from message events via the presence of a state_key,"
            + " rather than via the event type."
    )
    @ApiResponses( {
        @ApiResponse(code = 200, message = "The transaction was processed successfully.")
    })
    @PUT
    @Path("/transactions/{txnId}")
    EmptyResponse transaction(
        @ApiParam(
            name = "txnId",
            value = "The transaction ID for this set of events. Homeservers generate these IDs and they are used to ensure"
                + " idempotency of requests.",
            required = true
        ) @PathParam("txnId") String txnId,
        @ApiParam(
            value = "JSON body request."
        ) TransactionRequest request,

        @Context HttpServletRequest servletRequest,
        @Context HttpServletResponse servletResponse
    );

    /**
     * This endpoint is invoked by the homeserver on an application service to query the existence of a given room alias.
     * The homeserver will only query room aliases inside the application service's aliases namespace. The homeserver will
     * send this request when it receives a request to join a room alias within the application service's namespace.
     *
     * @param roomAlias       Required. The room alias being queried.
     * @param servletRequest  Servlet request.
     * @param servletResponse Servlet response.
     * @return <p>Status code 200: The application service indicates that this room alias exists. The application service MUST have
     * created a room and associated it with the queried room alias using the client-server API. Additional information
     * about the room such as its name and topic can be set before responding.</p>
     * <p>Status code 401: The homeserver has not supplied credentials to the application service. Optional error information can
     * be included in the body of this response.</p>
     * <p>Status code 403: The credentials supplied by the homeserver were rejected.</p>
     * <p>Status code 404: The application service indicates that this room alias does not exist. Optional error information can
     * be included in the body of this response.</p>
     */
    @ApiOperation(
        value = "This endpoint is invoked by the homeserver on an application service to query the existence of a given room alias.",
        notes = "The homeserver will only query room aliases inside the application service's aliases namespace. The homeserver will"
            + " send this request when it receives a request to join a room alias within the application service's namespace."
    )
    @ApiResponses( {
        @ApiResponse(code = 200, message = "The application service indicates that this room alias exists. The application service MUST"
            + " have created a room and associated it with the queried room alias using the client-server API. Additional information"
            + " about the room such as its name and topic can be set before responding."),
        @ApiResponse(code = 401, message = "The homeserver has not supplied credentials to the application service. Optional error"
            + " information can be included in the body of this response."),
        @ApiResponse(code = 403, message = "The credentials supplied by the homeserver were rejected."),
        @ApiResponse(code = 404, message = "The application service indicates that this room alias does not exist. Optional error"
            + " information can be included in the body of this response.")
    })
    @GET
    @Path("/rooms/{roomAlias}")
    EmptyResponse rooms(
        @ApiParam(
            name = "roomAlias",
            value = "The room alias being queried.",
            required = true
        ) @PathParam("roomAlias") String roomAlias,

        @Context HttpServletRequest servletRequest,
        @Context HttpServletResponse servletResponse
    );

    /**
     * This endpoint is invoked by the homeserver on an application service to query the existence of a given user ID.
     * The homeserver will only query user IDs inside the application service's users namespace. The homeserver will
     * send this request when it receives an event for an unknown user ID in the application service's namespace.
     *
     * @param userId          Required. The user ID being queried.
     * @param servletRequest  Servlet request.
     * @param servletResponse Servlet response.
     * @return <p>Status code 200: The application service indicates that this user exists. The application service MUST create
     * the user using the client-server API.</p>
     * <p>Status code 401: The homeserver has not supplied credentials to the application service. Optional error information
     * can be included in the body of this response.</p>
     * <p>Status code 403: The credentials supplied by the homeserver were rejected.</p>
     * <p>Status code 404: The application service indicates that this user does not exist. Optional error information can be
     * included in the body of this response.</p>
     */
    @ApiOperation(
        value = "This endpoint is invoked by the homeserver on an application service to query the existence of a given user ID.",
        notes = "The homeserver will only query user IDs inside the application service's users namespace. The homeserver will"
            + " send this request when it receives an event for an unknown user ID in the application service's namespace."
    )
    @ApiResponses( {
        @ApiResponse(code = 200, message = "The application service indicates that this user exists. The application service MUST create"
            + " the user using the client-server API."),
        @ApiResponse(code = 401, message = "The homeserver has not supplied credentials to the application service. Optional error"
            + " information can be included in the body of this response."),
        @ApiResponse(code = 403, message = "The credentials supplied by the homeserver were rejected."),
        @ApiResponse(code = 404, message = "The application service indicates that this user does not exist. Optional error information"
            + " can be included in the body of this response.")
    })
    @GET
    @Path("/users/{userId}")
    EmptyResponse users(
        @ApiParam(
            name = "userId",
            value = "The user ID being queried.",
            required = true
        ) @PathParam("userId") String userId,

        @Context HttpServletRequest servletRequest,
        @Context HttpServletResponse servletResponse
    );
}
