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

package io.github.ma1uta.matrix.bot;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.ws.rs.client.Client;

/**
 * Bot service.
 *
 * @param <C> bot configuration.
 * @param <D> bot dao.
 * @param <S> bot service.
 * @param <E> extra data.
 */
public abstract class AbstractBotPool<C extends BotConfig, D extends BotDao<C>, S extends Service<D>, E> {

    private static final int TIMEOUT = 10;

    private final ExecutorService pool;

    private final String homeserverUrl;

    private final String domain;

    private final String displayName;

    private final Client client;

    private final String appToken;

    private final S service;

    private final List<Class<? extends Command<C, D, S, E>>> commandClasses;

    public AbstractBotPool(String homeserverUrl, String domain, String displayName, Client client, String appToken,
                           S service,
                           List<Class<? extends Command<C, D, S, E>>> commandClasses) {
        this.domain = domain;
        this.service = service;
        this.commandClasses = commandClasses;
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

    public S getService() {
        return service;
    }

    public List<Class<? extends Command<C, D, S, E>>> getCommandClasses() {
        return commandClasses;
    }

    protected abstract C createConfig(String username);

    /**
     * Run new bot.
     *
     * @param username bot's username
     */
    public void startNewBot(String username) {
        submit(createConfig(username));
    }

    protected void submit(C data) {
        getPool().submit(new Bot<>(getClient(), getHomeserverUrl(), getDomain(), getAppToken(), data, getService(), getCommandClasses()));
    }

    /**
     * Start pool.
     */
    public void start() {
        getService().invoke((dao) -> {
            dao.findAll().forEach(this::submit);
        });
    }

    /**
     * Stop pool.
     *
     * @throws InterruptedException when cannot stop bot's thread.
     */
    public void stop() throws InterruptedException {
        getPool().awaitTermination(TIMEOUT, TimeUnit.SECONDS);
    }
}
