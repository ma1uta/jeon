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

import io.github.ma1uta.matrix.Event;
import io.github.ma1uta.matrix.sdk.MatrixClient;

/**
 * Command interface.
 */
public interface Command {

    /**
     * Command name.
     *
     * @return name.
     */
    String name();

    /**
     * Invoking command.
     *
     * @param matrixClient matrix client.
     * @param botConfig    bot configuration.
     * @param event        event with command.
     * @param action       command.
     */
    void invoke(MatrixClient matrixClient, BotConfig botConfig, Event event, String action);

    /**
     * Help information.
     *
     * @return help information.
     */
    String help();

    /**
     * Usage information.
     *
     * @return usage information.
     */
    String usage();
}
