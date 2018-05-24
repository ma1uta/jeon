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

package io.github.ma1uta.mxtoot.matrix;

import io.dropwizard.lifecycle.Managed;
import io.github.ma1uta.matrix.Id;
import io.github.ma1uta.matrix.bot.AbstractBotPool;
import io.github.ma1uta.matrix.bot.Command;
import io.github.ma1uta.matrix.bot.RunState;
import io.github.ma1uta.mxtoot.matrix.mastodon.MxMastodonClient;

import java.util.List;
import java.util.UUID;
import javax.ws.rs.client.Client;

/**
 * Bot service.
 */
public class MxTootBotPool extends AbstractBotPool<MxTootConfig, MxTootDao, MxTootService<MxTootDao>, MxMastodonClient> implements Managed {

    public MxTootBotPool(String homeserverUrl, String domain, String displayName, Client client, String appToken,
                         MxTootService<MxTootDao> service,
                         List<Class<? extends Command<MxTootConfig, MxTootDao, MxTootService<MxTootDao>, MxMastodonClient>>> commandClasses,
                         RunState runState) {
        super(homeserverUrl, domain, displayName, client, appToken, service, commandClasses, runState);
    }

    @Override
    protected MxTootConfig createConfig(String username) {
        MxTootConfig config = new MxTootConfig();
        config.setUserId(username);
        String localpart = Id.localpart(username);
        int nameIndex = localpart.indexOf("_");
        if (nameIndex > -1) {
            config.setMastodonUsername(localpart.substring(nameIndex));
        }
        config.setDisplayName(getDisplayName());
        config.setDeviceId(UUID.randomUUID().toString());
        return config;
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void stop() throws InterruptedException {
        super.stop();
    }
}
