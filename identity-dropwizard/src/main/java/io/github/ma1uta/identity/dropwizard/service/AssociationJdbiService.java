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
import io.github.ma1uta.identity.model.Association;
import io.github.ma1uta.identity.model.Session;
import io.github.ma1uta.identity.service.impl.AbstractAssociationService;
import io.github.ma1uta.jeon.exception.MatrixException;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the {@link io.github.ma1uta.identity.service.AssociationService} based on jdbi.
 */
public class AssociationJdbiService extends AbstractAssociationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AssociationJdbiService.class);

    private final Jdbi jdbi;

    public AssociationJdbiService(Jdbi jdbi, AssociationConfiguration configuration) {
        super(configuration);
        this.jdbi = jdbi;
    }

    public Jdbi getJdbi() {
        return jdbi;
    }

    @Override
    public Optional<Association> lookup(String address, String medium) {
        try {
            return getJdbi().withHandle(handle -> {
                handle.setReadOnly(true);
                try {
                    return handle.inTransaction(h -> super.lookupInternal(address, medium, h.attach(AssociationDao.class)));
                } catch (Exception e) {
                    String msg = "Cannot find mxid";
                    LOGGER.error(msg, e);
                    throw new MatrixException(MatrixException.M_INTERNAL, msg);
                }
            });
        } catch (Exception e) {
            String msg = "Cannot find mxid";
            LOGGER.error(msg, e);
            throw new MatrixException(MatrixException.M_INTERNAL, msg);
        }
    }

    @Override
    public List<List<String>> lookup(List<List<String>> threepids) {
        try {
            return getJdbi().withHandle(handle -> {
                handle.setReadOnly(true);
                return handle.inTransaction(h -> super.lookupInternal(threepids, h.attach(AssociationDao.class)));
            });
        } catch (Exception e) {
            String msg = "Cannot find mxid";
            LOGGER.error(msg, e);
            throw new MatrixException(MatrixException.M_INTERNAL, msg);
        }
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
