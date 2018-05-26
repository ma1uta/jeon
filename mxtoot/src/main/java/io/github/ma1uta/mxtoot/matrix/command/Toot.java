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

import com.sys1yagi.mastodon4j.api.entity.Status;
import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException;
import com.sys1yagi.mastodon4j.api.method.Statuses;
import io.github.ma1uta.matrix.Event;
import io.github.ma1uta.matrix.bot.BotHolder;
import io.github.ma1uta.matrix.bot.Command;
import io.github.ma1uta.matrix.client.MatrixClient;
import io.github.ma1uta.mxtoot.mastodon.MxMastodonClient;
import io.github.ma1uta.mxtoot.matrix.MxTootConfig;
import io.github.ma1uta.mxtoot.matrix.MxTootDao;
import io.github.ma1uta.mxtoot.matrix.MxTootService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Toot message.
 */
public class Toot implements Command<MxTootConfig, MxTootDao, MxTootService<MxTootDao>, MxMastodonClient> {

    private static final Logger LOGGER = LoggerFactory.getLogger(Toot.class);

    @Override
    public String name() {
        return "toot";
    }

    @Override
    public void invoke(BotHolder<MxTootConfig, MxTootDao, MxTootService<MxTootDao>, MxMastodonClient> holder, Event event,
                       String arguments) {
        MxTootConfig config = holder.getConfig();
        MatrixClient matrixClient = holder.getMatrixClient();

        if (holder.getData() == null) {
            matrixClient.sendNotice(config.getRoomId(), "Client isn't initialized, start registration via !reg command.");
            return;
        }

        try {
            Status status = new Statuses(holder.getData().getMastodonClient()).postStatus(arguments, null, null, false, null).execute();

            matrixClient.sendNotice(config.getRoomId(), "Tooted: " + status.getUri());
        } catch (Mastodon4jRequestException e) {
            String msg = "Cannot toot";
            LOGGER.error(msg, e);
            matrixClient.sendNotice(config.getRoomId(), msg);
        }
    }

    @Override
    public String help() {
        return "toot message";
    }

    @Override
    public String usage() {
        return "!toot <message>";
    }
}
