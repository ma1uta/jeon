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

import io.dropwizard.lifecycle.Managed;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.ws.rs.client.Client;

/**
 * Bot service.
 */
public class BotPool implements Managed {

    private static final int TIMEOUT = 10;

    private final ExecutorService pool;

    private final String homeserverUrl;

    private final String domain;

    private final String displayName;

    private final Client client;

    private final String appToken;

    private final Service<BotDao> service;

    public BotPool(String homeserverUrl, String domain, String displayName, Client client, String appToken, Service<BotDao> service) {
        this.domain = domain;
        this.service = service;
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

    public Service<BotDao> getService() {
        return service;
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
        getPool().submit(new Bot(getClient(), getHomeserverUrl(), getDomain(), getAppToken(), data, getService()));
    }

    @Override
    public void start() {
        getService().invoke((dao) -> {
            dao.findAll().forEach(this::submit);
        });
    }

    @Override
    public void stop() throws Exception {
        getPool().awaitTermination(TIMEOUT, TimeUnit.SECONDS);
    }
}
