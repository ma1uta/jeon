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

import io.github.ma1uta.jeon.exception.MatrixException;
import io.github.ma1uta.matrix.EmptyResponse;
import io.github.ma1uta.matrix.ErrorResponse;
import io.github.ma1uta.matrix.application.api.ApplicationApi;
import io.github.ma1uta.matrix.application.model.TransactionRequest;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Application REST service.
 */
public class AppResource implements ApplicationApi {

    private final TransactionDao transactionDao;
    private final BotPool botPool;
    private final Service<BotDao> botService;
    private final Service<TransactionDao> transactionService;
    private final String hsToken;
    private final String url;

    public AppResource(TransactionDao transactionDao, BotPool botPool, String hsToken,
                       String url, Service<BotDao> botService,
                       Service<TransactionDao> transactionService) {
        this.transactionDao = transactionDao;
        this.botPool = botPool;
        this.hsToken = hsToken;
        this.url = url;
        this.botService = botService;
        this.transactionService = transactionService;
    }

    public String getHsToken() {
        return hsToken;
    }

    public String getUrl() {
        return url;
    }

    public BotPool getBotPool() {
        return botPool;
    }

    public TransactionDao getTransactionDao() {
        return transactionDao;
    }

    public Service<BotDao> getBotService() {
        return botService;
    }

    public Service<TransactionDao> getTransactionService() {
        return transactionService;
    }

    @Override
    public EmptyResponse transaction(String txnId, TransactionRequest request, HttpServletRequest servletRequest,
                                     HttpServletResponse servletResponse) {
        validateAsToken(servletRequest);

        Boolean exist = getTransactionService().invoke((dao) -> {
            return dao.exist(txnId);
        });
        if (!exist) {
            request.getEvents().forEach(event -> {
                String roomId = event.getRoomId();
                //TODO
                //getBotPool().startNewBot(event.getRoomId(), "");
            });
            getTransactionService().invoke((dao) -> {
                Transaction transaction = new Transaction();
                transaction.setId(txnId);
                transaction.setProcessed(LocalDateTime.now());
                getTransactionDao().save(transaction);
            });
        }

        return new EmptyResponse();
    }

    @Override
    public EmptyResponse rooms(String roomAlias, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        throw new MatrixException(getUrl().toUpperCase() + "_NOT_FOUND", "", HttpServletResponse.SC_NOT_FOUND);
    }

    @Override
    public EmptyResponse users(String userId, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        validateAsToken(servletRequest);
        if (getBotService().invoke((dao) -> {
            return dao.user(userId);
        })) {
            throw new MatrixException(ErrorResponse.Code.M_USER_IN_USE, "User has been already registred", HttpServletResponse.SC_CONFLICT);
        } else {
            getBotPool().startNewBot(userId);
            return new EmptyResponse();
        }
    }

    protected void validateAsToken(HttpServletRequest servletRequest) {
        String asToken = servletRequest.getParameter("access_token");
        if (StringUtils.isBlank(asToken)) {
            throw new MatrixException(getUrl().toUpperCase() + "_UNAUTHORIZED", "", HttpServletResponse.SC_UNAUTHORIZED);
        }

        if (!getHsToken().equals(asToken)) {
            throw new MatrixException(ErrorResponse.Code.M_FORBIDDEN, "", HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
