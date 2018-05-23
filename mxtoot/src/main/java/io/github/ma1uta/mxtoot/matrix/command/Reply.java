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
import io.github.ma1uta.mxtoot.matrix.MxTootConfig;
import io.github.ma1uta.mxtoot.matrix.MxTootDao;
import io.github.ma1uta.mxtoot.matrix.MxTootService;
import io.github.ma1uta.mxtoot.matrix.mastodon.MxMastodonClient;

/**
 * Reply.
 */
public class Reply implements Command<MxTootConfig, MxTootDao, MxTootService<MxTootDao>, MxMastodonClient> {
    @Override
    public String name() {
        return "reply";
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

        if (arguments == null || arguments.trim().isEmpty()) {
            matrixClient.sendNotice(config.getRoomId(), "Usage: " + usage());
            return;
        }

        String trimmed = arguments.trim();
        int spaceIndex = trimmed.indexOf(" ");
        if (spaceIndex == -1) {
            matrixClient.sendNotice(config.getRoomId(), "Usage: " + usage());
            return;
        }

        Long statusId;
        try {
            statusId = Long.parseLong(trimmed.substring(0, spaceIndex));
        } catch (NumberFormatException e) {
            matrixClient.sendNotice(config.getRoomId(), "Status id is not a number.\nUsage: " + usage());
            return;
        }
        String message = trimmed.substring(spaceIndex);

        try {
            Status status = new Statuses(holder.getData().getMastodonClient()).postStatus(message, statusId, null, false, null).execute();
            matrixClient.sendNotice(config.getRoomId(), "Tooted: " + status.getUrl());
        } catch (Mastodon4jRequestException e) {
            matrixClient.sendNotice(config.getRoomId(), "Cannot toot");
        }
    }

    @Override
    public String help() {
        return "reply";
    }

    @Override
    public String usage() {
        return "!reply <status_id> <message>";
    }
}
