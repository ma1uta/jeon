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

package io.github.ma1uta.macpub.matrix.command;

import io.github.ma1uta.macpub.matrix.BotConfig;
import io.github.ma1uta.macpub.matrix.Command;
import io.github.ma1uta.matrix.Event;
import io.github.ma1uta.matrix.sdk.MatrixClient;

/**
 * Leave current room.
 */
public class Leave implements Command {
    @Override
    public String name() {
        return "leave";
    }

    @Override
    public void invoke(MatrixClient matrixClient, BotConfig botConfig, Event event, String action) {
        if (botConfig.getOwner() == null || botConfig.getOwner().equals(event.getSender())) {
            matrixClient.room().leaveRoom(botConfig.getRoomId());
        }
    }

    @Override
    public String help() {
        return "leave room (invoked only by owner).";
    }

    @Override
    public String usage() {
        return "!leave";
    }
}
