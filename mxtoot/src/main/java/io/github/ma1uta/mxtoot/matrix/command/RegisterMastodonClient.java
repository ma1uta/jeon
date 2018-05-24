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

package io.github.ma1uta.mxtoot.matrix.command;

import com.google.gson.Gson;
import com.sys1yagi.mastodon4j.MastodonClient;
import com.sys1yagi.mastodon4j.api.Scope;
import com.sys1yagi.mastodon4j.api.entity.auth.AppRegistration;
import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException;
import com.sys1yagi.mastodon4j.api.method.Apps;
import io.github.ma1uta.matrix.Event;
import io.github.ma1uta.matrix.bot.BotHolder;
import io.github.ma1uta.matrix.bot.Command;
import io.github.ma1uta.matrix.client.MatrixClient;
import io.github.ma1uta.mxtoot.matrix.MxTootConfig;
import io.github.ma1uta.mxtoot.matrix.MxTootDao;
import io.github.ma1uta.mxtoot.matrix.MxTootService;
import io.github.ma1uta.mxtoot.matrix.mastodon.MxMastodonClient;
import okhttp3.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Register a new toot client.
 */
public class RegisterMastodonClient implements Command<MxTootConfig, MxTootDao, MxTootService<MxTootDao>, MxMastodonClient> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterMastodonClient.class);

    @Override
    public String name() {
        return "reg";
    }

    @Override
    public void invoke(BotHolder<MxTootConfig, MxTootDao, MxTootService<MxTootDao>, MxMastodonClient> holder, Event event,
                       String arguments) {
        MxTootConfig config = holder.getConfig();
        if (config.getOwner() != null && !config.getOwner().equals(event.getSender())) {
            return;
        }

        MatrixClient matrixClient = holder.getMatrixClient();

        if (arguments == null || arguments.trim().isEmpty()) {
            matrixClient.sendFormattedNotice(config.getRoomId(), "Usage: " + usage());
            return;
        }

        config.setMastodonServer(arguments);

        Apps apps = new Apps(new MastodonClient.Builder(config.getMastodonServer(), new OkHttpClient.Builder(), new Gson()).build());

        try {
            AppRegistration appRegistration = apps
                .createApp(config.getMastodonUsername(), "urn:ietf:wg:oauth:2.0:oob", new Scope(Scope.Name.ALL)).execute();

            config.setMastodonClientId(appRegistration.getClientId());
            config.setMastodonClientSecret(appRegistration.getClientSecret());

            String authUrl = apps.getOAuthUrl(appRegistration.getClientId(), new Scope(Scope.Name.ALL), "urn:ietf:wg:oauth:2.0:oob");

            matrixClient.sendFormattedNotice(config.getRoomId(), authUrl.replaceAll("\\s", "+"));
            matrixClient
                .sendFormattedNotice(config.getRoomId(), "Please open url, login, get auth code and invoke command: !auth <auth code>");
        } catch (Mastodon4jRequestException e) {
            String msg = "Cannot start registration of the mastodon client: ";
            LOGGER.error(msg, e);
            matrixClient.sendFormattedNotice(config.getRoomId(), msg + e.getMessage());
        }
    }

    @Override
    public String help() {
        return "start registration of the new mastodon client (can invoke only owner)";
    }

    @Override
    public String usage() {
        return "!reg <mastodon server>";
    }
}
