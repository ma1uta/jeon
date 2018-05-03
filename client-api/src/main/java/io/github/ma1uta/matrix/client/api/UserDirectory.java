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

import io.github.ma1uta.matrix.client.model.userdirectory.SearchRequest;
import io.github.ma1uta.matrix.client.model.userdirectory.SearchResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * User directory.
 * <p/>
 * Provides search over all users.
 */
@Path("/_matrix/client/r0/user_directory")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface UserDirectory {

    /**
     * This API performs a server-side search over all users registered on the server. It searches user ID and displayname
     * case-insensitively for users that you share a room with or that are in public rooms.
     * <p/>
     * Rate-limited: Yes.
     * <p/>
     * Requires auth: Yes.
     *
     * @param request json body request.
     * @return Status code 200: The results of the search.
     *     Status code 429: This request was rate-limited.
     */
    @Path("/search")
    SearchResponse search(SearchRequest request);
}
