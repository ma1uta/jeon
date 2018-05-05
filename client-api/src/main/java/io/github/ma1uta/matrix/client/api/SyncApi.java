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

import io.github.ma1uta.matrix.client.model.sync.SyncResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * To read events, the intended flow of operation is for clients to first call the /sync API without a since parameter.
 * This returns the most recent message events for each room, as well as the state of the room at the start of the returned timeline.
 * The response also includes a next_batch field, which should be used as the value of the since parameter in the next call to /sync.
 * Finally, the response includes, for each room, a prev_batch field, which can be passed as a start parameter to
 * the /rooms/&lt;room_id&gt;/messages API to retrieve earlier messages.
 * <p/>
 * <a href="https://matrix.org/docs/spec/client_server/r0.3.0.html#syncing">Specification.</a>
 */
@Path("/_matrix/client/r0")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface SyncApi {

    /**
     * Presence.
     */
    String OFFLINE = "offline";

    /**
     * Synchronise the client's state with the latest state on the server. Clients use this API when they first log in to get
     * an initial snapshot of the state on the server, and then continue to call this API to get incremental deltas to the state,
     * and to receive new messages.
     * <p/>
     * Requires auth:
     *
     * @param filter      The ID of a filter created using the filter API or a filter JSON object encoded as a string. The server will
     *                    detect whether it is an ID or a JSON object by whether the first character is a "{" open brace. Passing the JSON
     *                    inline is best suited to one off requests. Creating a filter using the filter API is recommended for clients that
     *                    reuse the same filter multiple times, for example in long poll requests.
     * @param since       A point in time to continue a sync from.
     * @param fullState   Controls whether to include the full state for all rooms the user is a member of.
     *                    <p/>
     *                    If this is set to true, then all state events will be returned, even if since is non-empty.
     *                    The timeline will still be limited by the since parameter. In this case, the timeout parameter will be ignored
     *                    and the query will return immediately, possibly with an empty timeline.
     *                    <p/>
     *                    If false, and since is non-empty, only state which has changed since the point indicated by since will be
     *                    returned.
     *                    <p/>
     *                    By default, this is false.
     * @param setPresence Controls whether the client is automatically marked as online by polling this API. If this parameter is
     *                    omitted then the client is automatically marked as online when it uses this API. Otherwise if the parameter
     *                    is set to "offline" then the client is not marked as being online when it uses this API. One of: ["offline"]
     * @param timeout     The maximum time to wait, in milliseconds, before returning this request. If no events (or other data) become
     *                    available before this time elapses, the server will return a response with empty fields.
     *                    <p/>
     *                    By default, this is 0, so the server will return immediately even if the response is empty.
     * @return Status code 200: The initial snapshot or delta for the client to use to update their state.
     */
    @GET
    @Path("/sync")
    SyncResponse sync(@QueryParam("filter") String filter, @QueryParam("since") String since, @QueryParam("full_state") Boolean fullState,
                      @QueryParam("set_presence") String setPresence, @QueryParam("timeout") Long timeout);
}
