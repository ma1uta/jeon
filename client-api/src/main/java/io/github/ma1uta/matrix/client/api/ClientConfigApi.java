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

import io.github.ma1uta.matrix.EmptyResponse;
import io.github.ma1uta.matrix.Secured;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.Map;
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
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

/**
 * Client Behaviour.
 */
@Path("/_matrix/client/r0/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface ClientConfigApi {

    /**
     * Get some account_data for the client. This config is only visible to the user that set the account_data.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <br>
     * Return: {@link Map}.
     * <p>Status code 200: The account data content for the given type.</p>
     *
     * @param userId          Required. The id of the user to set account_data for. The access token must be authorized to make
     *                        requests for this user id.
     * @param type            Required. The event type of the account_data to set. Custom types should be namespaced to avoid clashes.
     * @param uriInfo         Request Information.
     * @param httpHeaders     Http headers.
     * @param asyncResponse   Asynchronous response.
     * @param securityContext Security context.
     */
    @Operation(
        summary = "Set some account_data for the client.",
        description = "This config is only visible to the user that set the account_data. The config will be "
            + "synced to clients in the top-level account_data.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The account_data was successfully added.",
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
            "User data"
        }
    )
    @GET
    @Secured
    @Path("/{userId}/account_data/{type}")
    void getConfig(
        @Parameter(
            description = "The id of the user to get account_data for. The access token must be authorized to make requests"
                + " for this user id.",
            required = true
        ) @PathParam("userId") String userId,
        @Parameter(
            description = "The event type of the account_data to get. Custom types should be namespaced to avoid clashes.",
            required = true
        ) @PathParam("type") String type,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );

    /**
     * Set some account_data for the client. This config is only visible to the user that set the account_data. The config will be
     * synced to clients in the top-level account_data.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <br>
     * Return: {@link EmptyResponse}.
     * <p>Status code 200: The account_data was successfully added.</p>
     *
     * @param userId          Required. The id of the user to set account_data for. The access token must be authorized to make
     *                        requests for this user id.
     * @param type            Required. The event type of the account_data to set. Custom types should be namespaced to avoid clashes.
     * @param accountData     Account data.
     * @param uriInfo         Request Information.
     * @param httpHeaders     Http headers.
     * @param asyncResponse   Asynchronous response.
     * @param securityContext Security context.
     */
    @Operation(
        summary = "Set some account_data for the client.",
        description = "This config is only visible to the user that set the account_data. The config will be "
            + "synced to clients in the top-level account_data.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The account_data was successfully added.",
                content = @Content(
                    schema = @Schema(
                        implementation = EmptyResponse.class
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
            "User data"
        }
    )
    @PUT
    @Secured
    @Path("/{userId}/account_data/{type}")
    void addConfig(
        @Parameter(
            description = "The id of the user to set account_data for. The access token must be authorized to make requests"
                + " for this user id.",
            required = true
        ) @PathParam("userId") String userId,
        @Parameter(
            description = "The event type of the account_data to set. Custom types should be namespaced to avoid clashes.",
            required = true
        ) @PathParam("type") String type,
        @RequestBody(
            description = "Account data"
        ) Map<String, Object> accountData,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );

    /**
     * Get some account_data for the client on a given room. This config is only visible to the user that set the account_data.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <br>
     * Return: {@link Map}.
     * <p>Status code 200: The account data content for the given type.</p>
     *
     * @param userId          Required. The id of the user to set account_data for. The access token must be authorized to make requests for
     *                        this user id.
     * @param roomId          Required. The id of the room to set account_data on.
     * @param type            Required. The event type of the account_data to set. Custom types should be namespaced to avoid clashes.
     * @param uriInfo         Request Information.
     * @param httpHeaders     Http headers.
     * @param asyncResponse   Asynchronous response.
     * @param securityContext Security context.
     */
    @Operation(
        summary = "Set some account_data for the client on a given room.",
        description = "This config is only visible to the user that set the account_data. The config will be synced to clients in the "
            + "per-room account_data.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The account_data was successfully added.",
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
            "User data"
        }
    )
    @GET
    @Secured
    @Path("/{userId}/rooms/{roomId}/account_data/{type}")
    void getRoomConfig(
        @Parameter(
            description = "The id of the user to get account_data for. The access token must be authorized to make requests"
                + " for this user id.",
            required = true
        ) @PathParam("userId") String userId,
        @Parameter(
            description = "The id of the room to get account_data on.",
            required = true
        ) @PathParam("roomId") String roomId,
        @Parameter(
            description = "The event type of the account_data to set. Custom types should be namespaced to avoid clashes.",
            required = true
        ) @PathParam("type") String type,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );

    /**
     * Set some account_data for the client on a given room. This config is only visible to the user that set the account_data.
     * The config will be synced to clients in the per-room account_data.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <br>
     * Return: {@link EmptyResponse}.
     * <p>Status code 200: The account_data was successfully added.</p>
     *
     * @param userId          Required. The id of the user to set account_data for. The access token must be authorized to make requests for
     *                        this user id.
     * @param roomId          Required. The id of the room to set account_data on.
     * @param type            Required. The event type of the account_data to set. Custom types should be namespaced to avoid clashes.
     * @param accountData     account data.
     * @param uriInfo         Request Information.
     * @param httpHeaders     Http headers.
     * @param asyncResponse   Asynchronous response.
     * @param securityContext Security context.
     */
    @Operation(
        summary = "Set some account_data for the client on a given room.",
        description = "This config is only visible to the user that set the account_data. The config will be synced to clients in the "
            + "per-room account_data.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The account_data was successfully added.",
                content = @Content(
                    schema = @Schema(
                        implementation = EmptyResponse.class
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
            "User data"
        }
    )
    @PUT
    @Secured
    @Path("/{userId}/rooms/{roomId}/account_data/{type}")
    void addRoomConfig(
        @Parameter(
            description = "The id of the user to set account_data for. The access token must be authorized to make requests"
                + " for this user id.",
            required = true
        ) @PathParam("userId") String userId,
        @Parameter(
            description = "The id of the room to set account_data on.",
            required = true
        ) @PathParam("roomId") String roomId,
        @Parameter(
            description = "The event type of the account_data to set. Custom types should be namespaced to avoid clashes.",
            required = true
        ) @PathParam("type") String type,
        @RequestBody(
            description = "Account data"
        ) Map<String, Object> accountData,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );
}
