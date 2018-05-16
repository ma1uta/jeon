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
import io.dropwizard.hibernate.UnitOfWorkAwareProxyFactory;
import io.dropwizard.lifecycle.Managed;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.ws.rs.client.Client;

/**
 * Bot service.
 */
public class BotService implements Managed {

    private static final int TIMEOUT = 10;

    private final ExecutorService pool;

    private final String homeserverUrl;

    private final String domain;

    private final String displayName;

    private final Client client;

    private final String appToken;

    private final BotDao dao;

    private final UnitOfWorkAwareProxyFactory proxyFactory;

    public BotService(String homeserverUrl, String domain, String displayName, Client client, String appToken, BotDao dao,
                      UnitOfWorkAwareProxyFactory proxyFactory) {
        this.domain = domain;
        this.dao = dao;
        this.proxyFactory = proxyFactory;
        this.pool = Executors.newCachedThreadPool();
        this.homeserverUrl = homeserverUrl;
        this.displayName = displayName;
        this.client = client;
        this.appToken = appToken;
    }

    public ExecutorService getPool() {
        return pool;
    }

    public String getHomeserverUrl() {
        return homeserverUrl;
    }

    public String getDomain() {
        return domain;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Client getClient() {
        return client;
    }

    public String getAppToken() {
        return appToken;
    }

    public BotDao getDao() {
        return dao;
    }

    public UnitOfWorkAwareProxyFactory getProxyFactory() {
        return proxyFactory;
    }

    /**
     * Run new bot.
     *
     * @param username bot's username
     */
    public void startNewBot(String username) {
        BotConfig data = new BotConfig();
        data.setDisplayName(getDisplayName());
        data.setUserId(username);
        data.setDeviceId(UUID.randomUUID().toString());

        submit(data);
    }

    protected void submit(BotConfig data) {
        getPool().submit(getProxyFactory()
            .create(Bot.class, new Class<?>[] {Client.class, String.class, String.class, String.class, BotConfig.class, BotDao.class},
                new Object[] {getClient(), getHomeserverUrl(), getDomain(), getAppToken(), data, getDao()}));
    }

    @Override
    @UnitOfWork
    public void start() throws Exception {
        getDao().findAll().forEach(this::submit);
    }

    @Override
    public void stop() throws Exception {
        getPool().awaitTermination(TIMEOUT, TimeUnit.SECONDS);
    }
}
