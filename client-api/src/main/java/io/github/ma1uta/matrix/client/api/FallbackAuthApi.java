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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

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
 * If a client does not recognize any or all login flows it can use the fallback login API.
 */
@Path("/_matrix")
@Produces(MediaType.TEXT_HTML)
public interface FallbackAuthApi {

    /**
     * Fallback login.
     * <br>
     * Return: Status code 200: <p>This returns an HTML and JavaScript page which can perform the entire login process.
     * The page will attempt to call the JavaScript function window.onLogin when login has been successfully completed.</p>
     *
     * @param uriInfo       Request Information.
     * @param httpHeaders   Http headers.
     * @param asyncResponse Asynchronous response.
     */
    @Operation(
        summary = "Fallback login.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Login page for the fallback login."
            )
        },
        tags = {
            "Session management"
        }
    )
    @GET
    @Path("/static/client/login")
    void staticLogin(
        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse
    );

    /**
     * Clients cannot be expected to be able to know how to process every single login type. If a client does not know how to
     * handle a given login type, it can direct the user to a web browser with the URL of a fallback page which will allow the
     * user to complete that login step out-of-band in their web browser.
     * <br>
     * Return: Status code 200: <p>an HTML page which can perform this authentication stage.
     * This page must use the following JavaScript when the authentication has been completed.</p>
     *
     * @param auth          The type name of the stage it is attempting.
     * @param session       the ID of the session given by the homeserver.
     * @param uriInfo       Request Information.
     * @param httpHeaders   Http headers.
     * @param asyncResponse Asynchronous response.
     */
    @Operation(
        summary = "Fallback login endpoint. If a client does not know how to handle a given login type, it can direct the user to"
            + " a web browser with the URL of a fallback page which will allow the user to complete that login step out-of-band"
            + " in their web browser.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "n HTML page which can perform this authentication stage. This page must use the "
                    + "following JavaScript when the authentication has been completed."
            )

        },
        tags = {
            "Session management"
        }
    )
    @GET
    @Path("/client/r0/auth/{auth}/fallback/web")
    void auth(
        @Parameter(
            description = "The type name of the stage it is attempting."
        ) @PathParam("auth") String auth,
        @Parameter(
            description = "the ID of the session given by the homeserver."
        ) @QueryParam("session") String session,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse
    );
}
