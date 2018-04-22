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

package io.github.ma1uta.identity.dropwizard.service;

import io.github.ma1uta.identity.configuration.InvitationServiceConfiguration;
import io.github.ma1uta.identity.dao.InvitationDao;
import io.github.ma1uta.identity.service.AssociationService;
import io.github.ma1uta.identity.service.KeyService;
import io.github.ma1uta.identity.service.RestService;
import io.github.ma1uta.identity.service.SerializerService;
import io.github.ma1uta.identity.service.impl.AbstractInvitationService;
import org.apache.commons.lang3.tuple.Triple;
import org.jdbi.v3.core.Jdbi;

import java.util.List;

/**
 * Implementation of the {@link io.github.ma1uta.identity.service.InvitationService} based on the jdbi.
 */
public class InvitationJdbiService extends AbstractInvitationService {

    private final Jdbi jdbi;

    public InvitationJdbiService(AssociationService associationService, KeyService keyService, SerializerService serializer,
                                 InvitationServiceConfiguration configuration, RestService restService, Jdbi jdbi) {
        super(associationService, keyService, serializer, configuration, restService);
        this.jdbi = jdbi;
    }

    public Jdbi getJdbi() {
        return jdbi;
    }

    @Override
    public Triple<String, String, List<String>> create(String address, String medium, String roomId, String sender) {
        return getJdbi().inTransaction(h -> super.createInternal(address, medium, roomId, sender, h.attach(InvitationDao.class)));
    }

    @Override
    public void sendInvite(String address, String medium, String mxid) {
        getJdbi().useHandle(handle -> {
            handle.setReadOnly(true);
            handle.useTransaction(h -> super.sendInviteInternal(address, medium, mxid, h.attach(InvitationDao.class)));
        });
    }
}
