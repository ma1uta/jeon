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

package io.github.ma1uta.identity.dao;

import io.github.ma1uta.identity.model.Invitation;

import java.util.List;

/**
 * Invitation Dao contract.
 */
public interface InvitationDao {

    /**
     * Persist a new invitation.
     *
     * @param invitation invitation.
     */
    void insert(Invitation invitation);

    /**
     * Find invitation via the address and the medium.
     *
     * @param address email or msisdn.
     * @param medium  'email' or 'msisdn'.
     * @return invitations.
     */
    List<Invitation> findByAddressMedium(String address, String medium);
}
