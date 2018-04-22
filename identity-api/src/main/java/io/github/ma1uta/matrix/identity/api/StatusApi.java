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

package io.github.ma1uta.matrix.identity.api;

import io.github.ma1uta.matrix.EmptyResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Checks that an Identity server is available at this API endpopint.
 *
 * @author ma1uta
 */
@Path("/_matrix/identity/api/v1")
@Produces(MediaType.APPLICATION_JSON)
public interface StatusApi extends IdentityApi {

    /**
     * To discover that an Identity server is available at a specific URL, this endpoint can be queried and will return an empty object.
     * <p/>
     * This is primarly used for auto-discovery and health check purposes by entities acting as a client for the Identity server.
     *
     * @return Status code {@code 200} if an Identity server is ready to serve requests.
     */
    @GET
    @Path("")
    EmptyResponse v1Status();
}
