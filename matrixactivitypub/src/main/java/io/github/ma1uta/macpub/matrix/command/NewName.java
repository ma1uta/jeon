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

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Set new name.
 */
public class NewName implements Command {
    @Override
    public String name() {
        return "name";
    }

    @Override
    public void invoke(MatrixClient matrixClient, BotConfig botConfig, Event event, String action) {
        String[] arguments = action.trim().split(" ");
        if (arguments.length < 2) {
            matrixClient.sendNotice(botConfig.getRoomId(), "Usage: " + usage());
        } else {
            String newName = Arrays.stream(arguments).skip(1).collect(Collectors.joining(" "));
            matrixClient.setDisplayName(newName);
            botConfig.setDisplayName(newName);
        }
    }

    @Override
    public String help() {
        return "set new name.";
    }

    @Override
    public String usage() {
        return "!name <new name>";
    }
}
