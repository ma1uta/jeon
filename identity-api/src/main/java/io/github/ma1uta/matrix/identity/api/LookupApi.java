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

import io.github.ma1uta.matrix.identity.model.lookup.BulkLookupRequest;
import io.github.ma1uta.matrix.identity.model.lookup.BulkLookupResponse;
import io.github.ma1uta.matrix.identity.model.lookup.LookupResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * Association lookup.
 *
 * @author ma1uta
 */
@Path("/_matrix/identity/api/v1")
public interface LookupApi {

    /**
     * Look up the Matrix user ID for a 3pid.
     *
     * @param medium  Required. The medium type of the 3pid. See the 3PID Types Appendix.
     * @param address Required. The address of the 3pid being looked up. See the 3PID Types Appendix.
     * @return The association for that 3pid, or the empty object if no association is known.
     */
    @GET
    @Path("/lookup")
    @Produces(MediaType.APPLICATION_JSON)
    LookupResponse lookup(@QueryParam("medium") String medium, @QueryParam("address") String address);

    /**
     * Lookup Matrix user IDs for a list of 3pids.
     *
     * @param request JSON body
     * @return A list of known 3PID mappings for the supplied 3PIDs.
     */
    @POST
    @Path("/bulk_lookup")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    BulkLookupResponse bulkLookup(BulkLookupRequest request);
}
