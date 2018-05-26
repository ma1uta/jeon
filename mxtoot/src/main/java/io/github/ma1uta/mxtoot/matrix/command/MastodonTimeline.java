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
import io.github.ma1uta.matrix.Event;
import io.github.ma1uta.matrix.bot.BotHolder;
import io.github.ma1uta.matrix.bot.Command;
import io.github.ma1uta.matrix.client.MatrixClient;
import io.github.ma1uta.mxtoot.mastodon.MxMastodonClient;
import io.github.ma1uta.mxtoot.matrix.MxTootConfig;
import io.github.ma1uta.mxtoot.matrix.MxTootDao;
import io.github.ma1uta.mxtoot.matrix.MxTootService;
import io.github.ma1uta.mxtoot.matrix.TimelineState;
import okhttp3.OkHttpClient;

/**
 * Run mastodon timeline.
 */
public class MastodonTimeline implements Command<MxTootConfig, MxTootDao, MxTootService<MxTootDao>, MxMastodonClient> {
    @Override
    public String name() {
        return "timeline";
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
            matrixClient.sendFormattedNotice(config.getRoomId(), "Usage: " + help());
        } else {
            TimelineState clientState = TimelineState.valueOf(arguments.trim().toUpperCase());
            config.setTimelineState(clientState);

            if (holder.getData() == null) {
                MastodonClient client = new MastodonClient.Builder(config.getMastodonServer(), new OkHttpClient.Builder(), new Gson())
                    .useStreamingApi().accessToken(config.getMastodonAccessToken()).build();

                MxMastodonClient mastodonClient = new MxMastodonClient(client, holder);
                holder.setData(mastodonClient);
                holder.addShutdownListener(mastodonClient);
            }

            switch (clientState) {
                case ON:
                case AUTO:
                    if (!holder.getData().streaming()) {
                        matrixClient.sendFormattedNotice(config.getRoomId(), "Cannot streaming");
                    }
                    break;
                case OFF:
                    holder.getData().shutdown();
                    break;
                default:
                    matrixClient.sendFormattedNotice(config.getRoomId(), "Unknown status " + clientState);
            }
        }
    }

    @Override
    public String help() {
        return "start, stop or autostart timeline (only owner can invoke)";
    }

    @Override
    public String usage() {
        return "!timeline [on|off|auto]";
    }
}
