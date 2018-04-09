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

import io.github.ma1uta.identity.configuration.AssociationConfiguration;
import io.github.ma1uta.identity.dao.AssociationDao;
import io.github.ma1uta.identity.model.Session;
import io.github.ma1uta.identity.service.KeyService;
import io.github.ma1uta.identity.service.SerializerService;
import io.github.ma1uta.identity.service.impl.AbstractAssociationService;
import io.github.ma1uta.matrix.identity.model.lookup.BulkLookupRequest;
import io.github.ma1uta.matrix.identity.model.lookup.BulkLookupResponse;
import io.github.ma1uta.matrix.identity.model.lookup.LookupResponse;
import org.jdbi.v3.core.Jdbi;

public class AssociationJdbiService extends AbstractAssociationService {

    private final Jdbi jdbi;

    public AssociationJdbiService(Jdbi jdbi,
                                  KeyService keyService,
                                  AssociationConfiguration configuration,
                                  SerializerService serializer) {
        super(keyService, configuration, serializer);
        this.jdbi = jdbi;
    }

    public Jdbi getJdbi() {
        return jdbi;
    }

    @Override
    public LookupResponse lookup(String address, String medium, boolean sign) {
        return getJdbi().withHandle(handle -> {
            handle.setReadOnly(true);
            return handle.inTransaction(h -> super.lookupInternal(address, medium, sign, h.attach(AssociationDao.class)));
        });
    }

    @Override
    public BulkLookupResponse lookup(BulkLookupRequest request) {
        return getJdbi().withHandle(handle -> {
            handle.setReadOnly(true);
            return handle.inTransaction(h -> super.lookupInternal(request, h.attach(AssociationDao.class)));
        });
    }

    @Override
    public void create(Session session, String mxid) {
        getJdbi().useTransaction(h -> super.createInternal(session, mxid, h.attach(AssociationDao.class)));
    }

    @Override
    public void expire() {
        getJdbi().useTransaction(h -> super.expireInternal(h.attach(AssociationDao.class)));
    }
}
