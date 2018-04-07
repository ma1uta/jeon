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

import static io.github.ma1uta.jeon.exception.MatrixException.M_INTERNAL;

import io.github.ma1uta.identity.configuration.SessionServiceConfiguration;
import io.github.ma1uta.identity.dao.SessionDao;
import io.github.ma1uta.identity.model.Session;
import io.github.ma1uta.jeon.exception.MatrixException;
import io.github.ma1uta.matrix.ErrorResponse;
import io.github.ma1uta.matrix.identity.model.validation.ValidationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;

public class SessionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionService.class);

    private final SessionDao sessionDao;
    private final EmailClient emailClient;
    private final AssociationService associationService;
    private final InvitationService invitationService;
    private final SessionServiceConfiguration configuration;

    public SessionService(SessionDao sessionDao, EmailClient emailClient,
                          AssociationService associationService,
                          InvitationService invitationService, SessionServiceConfiguration configuration) {
        this.sessionDao = sessionDao;
        this.emailClient = emailClient;
        this.associationService = associationService;
        this.invitationService = invitationService;
        this.configuration = configuration;
    }

    public SessionDao getSessionDao() {
        return sessionDao;
    }

    public EmailClient getEmailClient() {
        return emailClient;
    }

    public SessionServiceConfiguration getConfiguration() {
        return configuration;
    }

    public AssociationService getAssociationService() {
        return associationService;
    }

    public InvitationService getInvitationService() {
        return invitationService;
    }

    /**
     * Create new session.
     *
     * @param clientSecret client secret.
     * @param email        client email.
     * @param nextLink     url to open.
     * @param sendAttempt  attempt
     */
    public String create(String clientSecret, String email, Long sendAttempt, String nextLink) {
        boolean create = true;
        if (sendAttempt != null) {
            create = getSessionDao().findBySecretEmail(clientSecret, email, "email").stream()
                .anyMatch(s -> s.getSendAttempt() == null || s.getSendAttempt() < sendAttempt);
        }

        if (create) {
            String sid = UUID.randomUUID().toString();
            String token = UUID.randomUUID().toString();
            getSessionDao()
                .insertOrUpdate(sid, token, clientSecret, email, sendAttempt != null ? Long.toString(sendAttempt) : null, nextLink);

            String emailMessage;
            try {
                emailMessage = MessageFormat.format(getConfiguration().getEmailMessageTemplate(), token, clientSecret, sid,
                    validationUrl(getConfiguration().getHomeserver(), token, clientSecret, sid));
            } catch (UnsupportedEncodingException e) {
                String msg = "Cannot send verification email";
                LOGGER.error(msg, e);
                throw new MatrixException(M_INTERNAL, msg);
            }
            getEmailClient().send(email, getConfiguration().getEmailSubject(), emailMessage);
            return sid;
        } else {
            return "";
        }
    }

    /**
     * Validate existing session.
     *
     * @param token        validation token.
     * @param clientSecret client secret.
     * @param sid          session id.
     */
    public String validate(String token, String clientSecret, String sid) {
        List<Session> sessionList = getSessionDao().findBySecretSidToken(clientSecret, sid, token);
        switch (sessionList.size()) {
            case 0:
                throw new MatrixException(ErrorResponse.Code.M_THREEPID_NOT_FOUND, "Not found 3pid for this email.");
            case 1:
                getSessionDao().validate(sid);
                return sessionList.get(0).getNextLink();
            default:
                throw new MatrixException(M_INTERNAL, "Too many sessions with the same id.");
        }
    }

    /**
     * Find validated session.
     *
     * @param sid          session id.
     * @param clientSecret client secret.
     */
    public ValidationResponse getSession(String sid, String clientSecret) {
        List<Session> sessionList = getSessionDao().findBySecretSid(clientSecret, sid);
        switch (sessionList.size()) {
            case 0:
                throw new MatrixException(ErrorResponse.Code.M_SESSION_NOT_VALIDATED,
                    "This validation session has not yet been completed.");
            case 1:
                ValidationResponse response = new ValidationResponse();
                Session session = sessionList.get(0);
                response.setAddress(session.getAddress());
                response.setMedium(session.getMedium());
                ZoneOffset offset = ZoneOffset.systemDefault().getRules().getOffset(LocalDateTime.now());
                response.setValidatedAt(session.getValidated().toEpochSecond(offset));
                return response;
            default:
                throw new MatrixException(ErrorResponse.Code.M_SESSION_NOT_VALIDATED, "Too many sessions.");
        }
    }

    /**
     * Bind mxid and the 3pid.
     *
     * @param sid          session id.
     * @param mxid         matrix id.
     * @param clientSecret client secret.
     */
    public boolean publish(String sid, String clientSecret, String mxid) {
        List<Session> sessionList = getSessionDao().findBySecretSid(clientSecret, sid);
        switch (sessionList.size()) {
            case 0:
                throw new MatrixException(ErrorResponse.Code.M_SESSION_NOT_VALIDATED, "This validation session hs not yet been completed.");
            case 1:
                Session session = sessionList.get(0);
                getAssociationService().create(session, mxid);
                getInvitationService().sendInvite(session.getMedium(), session.getAddress(), mxid);
                return true;
            default:
                throw new MatrixException(ErrorResponse.Code.M_SESSION_NOT_VALIDATED, "Too many sessions");
        }
    }

    /**
     * Url for session validation.
     *
     * @param clientSecret client secret.
     * @param sid          session id.
     * @param token        session token.
     */
    public String validationUrl(String homeserver, String token, String clientSecret, String sid) throws UnsupportedEncodingException {
        return String.format("https://%s/_matrix/identity/api/v1/validate/email/submitToken?token=%s&client_secret=%s&sid=%s",
            homeserver, URLEncoder.encode(token, "UTF-8"), URLEncoder.encode(clientSecret, "UTF-8"), URLEncoder.encode(sid, "UTF-8"));
    }

    /**
     * Delete expired sessions.
     */
    public void cleanup() {
        getSessionDao().deleteOldest();
    }
}
