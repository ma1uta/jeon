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

package io.github.ma1uta.identity.service;

import io.github.ma1uta.identity.model.Session;
import io.github.ma1uta.matrix.identity.model.lookup.BulkLookupRequest;
import io.github.ma1uta.matrix.identity.model.lookup.BulkLookupResponse;
import io.github.ma1uta.matrix.identity.model.lookup.LookupResponse;

/**
 * Service to lookup, create and delete associations.
 */
public interface AssociationService {
    /**
     * Lookup association.
     *
     * @param medium  'email' or 'msisdn'.
     * @param address email address or phone number.
     * @param sign    if true then sign result else false.
     * @return response with the mxid.
     */
    LookupResponse lookup(String address, String medium, boolean sign);

    /**
     * Bulk lookup.
     *
     * @param request bulk request.
     * @return response triples of the mxid, address and medium.
     */
    BulkLookupResponse lookup(BulkLookupRequest request);

    /**
     * Create new association.
     *
     * @param mxid    matrix id.
     * @param session session id.
     */
    void create(Session session, String mxid);

    /**
     * Move expired associations to the "expired_association" table.
     */
    void expire();
}
