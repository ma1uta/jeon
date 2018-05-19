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
import io.github.ma1uta.matrix.RateLimit;
import io.github.ma1uta.matrix.Secured;
import io.github.ma1uta.matrix.client.model.typing.TypingRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

/**
 * When a client receives an m.typing event, it MUST use the user ID list to REPLACE its knowledge of every user who is currently
 * typing. The reason for this is that the server does not remember users who are not currently typing as that list gets big quickly.
 * The client should mark as not typing any user ID who is not in that list.
 * <p/>
 * It is recommended that clients store a boolean indicating whether the user is typing or not. Whilst this value is true a timer
 * should fire periodically every N seconds to send a typing HTTP request. The value of N is recommended to be no more than 20-30 seconds.
 * This request should be re-sent by the client to continue informing the server the user is still typing. As subsequent requests
 * will replace older requests, a safety margin of 5 seconds before the expected timeout runs out is recommended. When the user
 * stops typing, the state change of the boolean to false should trigger another HTTP request to inform the server that the user has
 * stopped typing.
 * <p/>
 * <a href="https://matrix.org/docs/spec/client_server/r0.3.0.html#id285">Specification.</a>
 */
@Path("/_matrix/client/r0/rooms")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface TypingApi {

    /**
     * This tells the server that the user is typing for the next N milliseconds where N is the value specified in the timeout key.
     * Alternatively, if typing is false, it tells the server that the user has stopped typing.
     * <p/>
     * Rate-limited: Yes.
     * <p/>
     * Requires auth: Yes.
     *
     * @param roomId          Required. The user who has started to type.
     * @param userId          Required. The room in which the user is typing.
     * @param request         json body request.
     * @param servletRequest  servlet request.
     * @param servletResponse servlet response.
     * @param securityContext security context.
     * @return Status code 200: The new typing state was set.
     *     Status code 429: This request was rate-limited.
     */
    @PUT
    @RateLimit
    @Secured
    @Path("/{roomId}/typing/{userId}")
    EmptyResponse typing(@PathParam("roomId") String roomId, @PathParam("userId") String userId, TypingRequest request,
                         @Context HttpServletRequest servletRequest, @Context HttpServletResponse servletResponse,
                         @Context SecurityContext securityContext);
}
