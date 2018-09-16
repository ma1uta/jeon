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

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;

/**
 * It is realistic to expect that some clients will be written to be run within a web browser or similar environment.
 * In these cases, the homeserver should respond to pre-flight requests and supply Cross-Origin Resource Sharing (CORS) headers
 * on all requests.
 */
@Path("/")
public interface CORSApi {

    /**
     * When a client approaches the server with a pre-flight (OPTIONS) request,
     * the server should respond with the CORS headers for that route.
     * The recommended CORS headers to be returned by servers on all requests are:
     * <pre>
     * Access-Control-Allow-Origin: *
     * Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS
     * Access-Control-Allow-Headers: Origin, X-Requested-With, Content-Type, Accept, Authorization
     * </pre>
     *
     * @param servletRequest Servlet request.
     * @param asyncResponse  Asynchronous response.
     */
    @OPTIONS
    @Path("/{path:.*}")
    void options(
        @Context HttpServletRequest servletRequest,
        @Suspended AsyncResponse asyncResponse
    );
}
