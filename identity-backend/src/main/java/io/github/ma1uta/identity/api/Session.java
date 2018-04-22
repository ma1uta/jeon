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

package io.github.ma1uta.identity.api;

import io.github.ma1uta.identity.service.SessionService;
import io.github.ma1uta.jeon.exception.MatrixException;
import io.github.ma1uta.matrix.ErrorResponse;
import io.github.ma1uta.matrix.identity.api.SessionApi;
import io.github.ma1uta.matrix.identity.model.associations.SessionResponse;
import io.github.ma1uta.matrix.identity.model.associations.ValidationResponse;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

/**
 * Implementation of the {@link SessionApi}.
 */
public class Session implements SessionApi {

    @Context
    private HttpServletResponse response;

    public Session(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    private final SessionService sessionService;

    public SessionService getSessionService() {
        return sessionService;
    }

    @Override
    public SessionResponse create(String clientSecret, String email, Long sendAttempt, String nextLink) {
        if (StringUtils.isAnyBlank(clientSecret, email)) {
            throw new MatrixException(ErrorResponse.Code.M_BAD_JSON, "Missing client secret or email");
        }
        SessionResponse response = new SessionResponse();
        response.setSid(getSessionService().create(clientSecret, "email", email, sendAttempt, nextLink));
        return response;
    }

    @Override
    public ValidationResponse getValidate(String sid, String clientSecret, String token) {
        return validate(sid, clientSecret, token);
    }

    @Override
    public ValidationResponse postValidate(String sid, String clientSecret, String token) {
        return validate(sid, clientSecret, token);
    }

    protected ValidationResponse validate(String sid, String clientSecret, String token) {
        if (StringUtils.isAnyBlank(sid, clientSecret, token)) {
            throw new MatrixException(ErrorResponse.Code.M_BAD_JSON, "Missing client secret, sid or token");
        }
        String nextLink = getSessionService().validate(token, clientSecret, sid);
        if (nextLink != null) {
            response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
            response.setHeader("Location", nextLink);
        }
        ValidationResponse response = new ValidationResponse();
        response.setValidated(true);
        return response;
    }
}
