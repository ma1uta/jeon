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

package io.github.ma1uta.matrix.application.api;

import io.github.ma1uta.matrix.EmptyResponse;
import io.github.ma1uta.matrix.ErrorResponse;
import io.github.ma1uta.matrix.application.model.TransactionRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 * Application service APIs which are used by the homeserver. All application services MUST implement these APIs.
 */
@Path("/_matrix/app/v1")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface ApplicationApi {

    /**
     * This API is called by the homeserver when it wants to push an event (or batch of events) to the application service.
     * <br>
     * Note that the application service should distinguish state events from message events via the presence of a state_key,
     * rather than via the event type.
     * <br>
     * Return: {@link EmptyResponse}.
     * <p>Status responseCode 200: The transaction was processed successfully.</p>
     *
     * @param txnId         Required. The transaction ID for this set of events. Homeservers generate these IDs and they are used to
     *                      ensure idempotency of requests.
     * @param request       JSON body request.
     * @param uriInfo       Information about the request.
     * @param httpHeaders   Http headers.
     * @param asyncResponse Asynchronous response.
     */
    @Operation(
        summary = "This API is called by the homeserver when it wants to push an event (or batch of events) to the application service.",
        description = "Note that the application service should distinguish state events from message events via the presence"
            + " of a state_key, rather than via the event type.",
        responses = {
            @ApiResponse(
                responseCode = "200", description = "The transaction was processed successfully.",
                content = @Content(
                    schema = @Schema(
                        implementation = EmptyResponse.class
                    )
                )
            ),
        }
    )
    @PUT
    @Path("/transactions/{txnId}")
    void transaction(
        @Parameter(
            name = "txnId",
            description = "The transaction ID for this set of events. Homeservers generate these IDs and they are used to ensure"
                + " idempotency of requests.",
            required = true
        ) @PathParam("txnId") String txnId,
        @RequestBody(
            description = "JSON body request."
        ) TransactionRequest request,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse
    );

    /**
     * This endpoint is invoked by the homeserver on an application service to query the existence of a given room alias.
     * The homeserver will only query room aliases inside the application service's aliases namespace. The homeserver will
     * send this request when it receives a request to join a room alias within the application service's namespace.
     * <br>
     * Return: {@link EmptyResponse}.
     * <p>Status responseCode 200: The application service indicates that this room alias exists. The application service MUST have
     * created a room and associated it with the queried room alias using the client-server API. Additional information
     * about the room such as its name and topic can be set before responding.</p>
     * <p>Status responseCode 401: The homeserver has not supplied credentials to the application service. Optional error information can
     * be included in the body of this response.</p>
     * <p>Status responseCode 403: The credentials supplied by the homeserver were rejected.</p>
     * <p>Status responseCode 404: The application service indicates that this room alias does not exist. Optional error information can
     * be included in the body of this response.</p>
     *
     * @param roomAlias     Required. The room alias being queried.
     * @param uriInfo       Information about the request.
     * @param httpHeaders   Http headers.
     * @param asyncResponse Asynchronous response.
     */
    @Operation(
        summary = "This endpoint is invoked by the homeserver on an application service to query the existence of a given room alias.",
        description = "The homeserver will only query room aliases inside the application service's aliases namespace. The homeserver will"
            + " send this request when it receives a request to join a room alias within the application service's namespace.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The application service indicates that this room alias exists."
                    + " The application service MUST have created a room and associated it with the queried room alias using the"
                    + " client-server API. Additional information about the room such as its name and topic can be set before responding.",
                content = @Content(
                    schema = @Schema(
                        implementation = EmptyResponse.class
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
                description = "The application service indicates that this room alias does not exist."
                    + " Optional error information can be included in the body of this response.",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponse.class
                    )
                )
            )
        }
    )
    @GET
    @Path("/rooms/{roomAlias}")
    void rooms(
        @Parameter(
            name = "roomAlias",
            description = "The room alias being queried.",
            required = true
        ) @PathParam("roomAlias") String roomAlias,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse
    );

    /**
     * This endpoint is invoked by the homeserver on an application service to query the existence of a given user ID.
     * The homeserver will only query user IDs inside the application service's users namespace. The homeserver will
     * send this request when it receives an event for an unknown user ID in the application service's namespace.
     * <br>
     * Return: {@link EmptyResponse}.
     * <p>Status responseCode 200: The application service indicates that this user exists. The application service MUST create
     * the user using the client-server API.</p>
     * <p>Status responseCode 401: The homeserver has not supplied credentials to the application service. Optional error information
     * can be included in the body of this response.</p>
     * <p>Status responseCode 403: The credentials supplied by the homeserver were rejected.</p>
     * <p>Status responseCode 404: The application service indicates that this user does not exist. Optional error information can be
     * included in the body of this response.</p>
     *
     * @param userId        Required. The user ID being queried.
     * @param uriInfo       Information about the request.
     * @param httpHeaders   Http headers.
     * @param asyncResponse Asynchronous response.
     */
    @Operation(
        summary = "This endpoint is invoked by the homeserver on an application service to query the existence of a given user ID.",
        description = "The homeserver will only query user IDs inside the application service's users namespace. The homeserver will"
            + " send this request when it receives an event for an unknown user ID in the application service's namespace.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The application service indicates that this user exists."
                    + " The application service MUST create the user using the client-server API.",
                content = @Content(
                    schema = @Schema(
                        implementation = EmptyResponse.class
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
                description = "The application service indicates that this user does not exist."
                    + " Optional error information can be included in the body of this response.",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponse.class
                    )
                )
            )
        }
    )
    @GET
    @Path("/users/{userId}")
    void users(
        @Parameter(
            name = "userId",
            description = "The user ID being queried.",
            required = true
        ) @PathParam("userId") String userId,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse
    );
}
