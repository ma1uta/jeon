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

package io.github.ma1uta.macpub.matrix;

import io.dropwizard.hibernate.UnitOfWork;
import io.github.ma1uta.jeon.exception.MatrixException;
import io.github.ma1uta.matrix.EmptyResponse;
import io.github.ma1uta.matrix.ErrorResponse;
import io.github.ma1uta.matrix.application.api.ApplicationApi;
import io.github.ma1uta.matrix.application.model.TransactionRequest;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

/**
 * Application REST service.
 */
public class AppResource implements ApplicationApi {

    private final BotDao dao;
    private final TransactionDao transactionDao;
    private final BotService botService;
    private final String hsToken;
    private final String url;

    public AppResource(BotDao dao, TransactionDao transactionDao, BotService botService, String hsToken,
                       String url) {
        this.dao = dao;
        this.transactionDao = transactionDao;
        this.botService = botService;
        this.hsToken = hsToken;
        this.url = url;
    }

    @Context
    private HttpServletRequest request;

    public BotDao getDao() {
        return dao;
    }

    public String getHsToken() {
        return hsToken;
    }

    public String getUrl() {
        return url;
    }

    public BotService getBotService() {
        return botService;
    }

    public TransactionDao getTransactionDao() {
        return transactionDao;
    }

    @Override
    @UnitOfWork
    public EmptyResponse transaction(String txnId, TransactionRequest request) {
        validateAsToken();

        if (!getTransactionDao().exist(txnId)) {
            request.getEvents().forEach(event -> {
                String roomId = event.getRoomId();
                //TODO
                //getBotService().startNewBot(event.getRoomId(), "");
            });
            Transaction transaction = new Transaction();
            transaction.setId(txnId);
            transaction.setProcessed(LocalDateTime.now());
            getTransactionDao().save(transaction);
        }

        return new EmptyResponse();
    }

    @Override
    public EmptyResponse rooms(String roomAlias) {
        throw new MatrixException(getUrl().toUpperCase() + "_NOT_FOUND", "", HttpServletResponse.SC_NOT_FOUND);
    }

    @Override
    @UnitOfWork
    public EmptyResponse users(String userId) {
        validateAsToken();
        if (getDao().user(userId)) {
            throw new MatrixException(ErrorResponse.Code.M_USER_IN_USE, "User has been already registred", HttpServletResponse.SC_CONFLICT);
        } else {
            getBotService().startNewBot(userId);
            return new EmptyResponse();
        }
    }

    protected void validateAsToken() {
        String asToken = request.getParameter("access_token");
        if (StringUtils.isBlank(asToken)) {
            throw new MatrixException(getUrl().toUpperCase() + "_UNAUTHORIZED", "", HttpServletResponse.SC_UNAUTHORIZED);
        }

        if (!getHsToken().equals(asToken)) {
            throw new MatrixException(ErrorResponse.Code.M_FORBIDDEN, "", HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
