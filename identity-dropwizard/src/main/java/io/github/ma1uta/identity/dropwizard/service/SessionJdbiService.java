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

import io.github.ma1uta.identity.configuration.SessionServiceConfiguration;
import io.github.ma1uta.identity.dao.SessionDao;
import io.github.ma1uta.identity.model.Session;
import io.github.ma1uta.identity.service.AssociationService;
import io.github.ma1uta.identity.service.InvitationService;
import io.github.ma1uta.identity.service.NotificationService;
import io.github.ma1uta.identity.service.impl.AbstractSessionService;
import io.github.ma1uta.jeon.exception.MatrixException;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation of the {@link io.github.ma1uta.identity.service.SessionService} based on the jdbi.
 */
public class SessionJdbiService extends AbstractSessionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionJdbiService.class);

    private final Jdbi jdbi;

    public SessionJdbiService(NotificationService notificationService,
                              AssociationService associationService,
                              InvitationService invitationService,
                              SessionServiceConfiguration configuration, Jdbi jdbi) {
        super(notificationService, associationService, invitationService, configuration);
        this.jdbi = jdbi;
    }

    public Jdbi getJdbi() {
        return jdbi;
    }

    @Override
    public String create(String clientSecret, String medium, String address, Long sendAttempt, String nextLink) {
        return getJdbi()
            .inTransaction(h -> super.createInternal(clientSecret, medium, address, sendAttempt, nextLink, h.attach(SessionDao.class)));
    }

    @Override
    public String validate(String token, String clientSecret, String sid) {
        return getJdbi().inTransaction(h -> super.validateInternal(token, clientSecret, sid, h.attach(SessionDao.class)));
    }

    @Override
    public Session getSession(String sid, String clientSecret) {
        try {
            return getJdbi().withHandle(handle -> {
                handle.setReadOnly(true);
                return handle.inTransaction(h -> super.getSessionInternal(sid, clientSecret, h.attach(SessionDao.class)));
            });
        } catch (Exception e) {
            String msg = "Cannot get session";
            LOGGER.error(msg, e);
            throw new MatrixException(MatrixException.M_INTERNAL, msg);
        }
    }

    @Override
    public boolean publish(String sid, String clientSecret, String mxid) {
        return getJdbi().inTransaction(h -> super.publishInternal(sid, clientSecret, mxid, h.attach(SessionDao.class)));
    }

    @Override
    public void cleanup() {
        getJdbi().useTransaction(h -> super.cleanupInternal(h.attach(SessionDao.class)));
    }
}
