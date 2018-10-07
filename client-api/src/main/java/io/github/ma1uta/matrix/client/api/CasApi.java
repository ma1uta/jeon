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

import io.github.ma1uta.matrix.EmptyResponse;
import io.github.ma1uta.matrix.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.UriInfo;

/**
 * Central Authentication Service (CAS) is a web-based single sign-on protocol.
 * <br>
 * An overview of the process, as used in Matrix, is as follows:
 * <ul>
 * <li>The Matrix client instructs the user's browser to navigate to the /login/cas/redirect endpoint on the user's homeserver.</li>
 * <li>The homeserver responds with an HTTP redirect to the CAS user interface, which the browser follows.</li>
 * <li>The CAS system authenticates the user.</li>
 * <li>The CAS server responds to the user's browser with a redirect back to the /login/cas/ticket endpoint on the homeserver,
 * which the browser follows. A 'ticket' identifier is passed as a query parameter in the redirect.</li>
 * <li>The homeserver receives the ticket ID from the user's browser, and makes a request to the CAS server to validate the ticket.</li>
 * <li>Having validated the ticket, the homeserver responds to the browser with a third HTTP redirect, back to the Matrix
 * client application. A login token is passed as a query parameter in the redirect.</li>
 * <li>The Matrix client receives the login token and passes it to the /login API.</li>
 * </ul>
 */
@Path("/_matrix/client/r0/login/cas")
public interface CasApi {

    /**
     * A web-based Matrix client should instruct the user's browser to navigate to this endpoint in order to log in via CAS.
     * <br>
     * The server MUST respond with an HTTP redirect to the CAS interface. The URI MUST include a service parameter giving the path
     * of the /login/cas/ticket endpoint (including the redirectUrl query parameter).
     * <br>
     * For example, if the endpoint is called with redirectUrl=https://client.example.com/?q=p, it might redirect to
     * {@code https://cas.example.com/?service=https%3A%2F%2Fserver.example.com%2F_matrix%2Fclient%2Fr0%2Flogin%2Fcas%2Fticket%3Fredirect
     * Url%3Dhttps%253A%252F%252Fclient.example.com%252F%253Fq%253Dp}.
     * <br>
     * Return: {@link EmptyResponse}.
     * A redirect to the CAS interface.
     *
     * @param redirectUrl   Required. URI to which the user will be redirected after the homeserver has authenticated the user with CAS.
     * @param uriInfo       Request Information.
     * @param httpHeaders   Http headers.
     * @param asyncResponse Asynchronous response.
     */
    @Operation(
        summary = "A web-based Matrix client should instruct the user's browser to navigate to this endpoint in order to log in via CAS.",
        description = "The server MUST respond with an HTTP redirect to the CAS interface. The URI MUST include a service parameter giving "
            + "the path of the /login/cas/ticket endpoint (including the redirectUrl query parameter).",
        responses = {
            @ApiResponse(
                responseCode = "302",
                description = "A redirect to the CAS interface",
                content = @Content(
                    schema = @Schema(
                        implementation = EmptyResponse.class
                    )
                )
            )
        }
    )
    @GET
    @Path("/redirect")
    void redirect(
        @Parameter(
            description = "URI to which the user will be redirected after the homeserver has authenticated the user with CAS.",
            required = true
        ) @QueryParam("redirectUrl") String redirectUrl,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse
    );

    /**
     * Once the CAS server has authenticated the user, it will redirect the browser to this endpoint (assuming /login/cas/redirect
     * gave it the correct service parameter).
     * <br>
     * The server MUST call /proxyValidate on the CAS server, to validate the ticket supplied by the browser.
     * <br>
     * If validation is successful, the server must generate a Matrix login token. It must then respond with an HTTP redirect to the
     * URI given in the redirectUrl parameter, adding a loginToken query parameter giving the generated token.
     * <br>
     * If validation is unsuccessful, the server should respond with a 401 Unauthorized error, the body of which will be displayed
     * to the user.
     * <br>
     * Return: {@link EmptyResponse}.
     * <p>Status code 302: A redirect to the Matrix client.</p>
     * <p>Status code 401: The server was unable to validate the CAS ticket.</p>
     *
     * @param redirectUrl   Required. The redirectUrl originally provided by the client to /login/cas/redirect.
     * @param ticket        Required. CAS authentication ticket.
     * @param uriInfo       Request Information.
     * @param httpHeaders   Http headers.
     * @param asyncResponse Asynchronous response.
     */
    @Operation(
        summary = "Once the CAS server has authenticated the user, it will redirect the browser to this endpoint "
            + "(assuming /login/cas/redirect gave it the correct service parameter).",
        description = "The server MUST call /proxyValidate on the CAS server, to validate the ticket supplied by the browser. "
            + "If validation is successful, the server must generate a Matrix login token. It must then respond with an HTTP redirect "
            + "to the URI given in the redirectUrl parameter, adding a loginToken query parameter giving the generated token. "
            + "If validation is unsuccessful, the server should respond with a 401 Unauthorized error, the body of which will be displayed "
            + "to the user.",
        responses = {
            @ApiResponse(
                responseCode = "302",
                description = "A redirect to the Matrix client.",
                content = @Content(
                    schema = @Schema(
                        implementation = EmptyResponse.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "401",
                description = "The server was unable to validate the CAS ticket.",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponse.class
                    )
                )
            )
        }
    )
    @GET
    @Path("/ticket")
    void ticket(
        @Parameter(
            description = "The redirectUrl originally provided by the client to /login/cas/redirect.",
            required = true
        ) @QueryParam("redirectUrl") String redirectUrl,
        @Parameter(
            description = "CAS authentication ticket",
            required = true
        ) @QueryParam("ticket") String ticket,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse
    );
}
