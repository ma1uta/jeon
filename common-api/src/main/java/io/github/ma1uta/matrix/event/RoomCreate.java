/*
 * Copyright Anatoliy Sablin tolya@sablin.xyz
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

package io.github.ma1uta.matrix.event;

import io.github.ma1uta.matrix.event.content.RoomCreateContent;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * This is the first event in a room and cannot be changed. It acts as the root of all other events.
 */
@Schema(
    description = "This is the first event in a room and cannot be changed. It acts as the root of all other events."
)
public class RoomCreate extends StateEvent<RoomCreateContent> {

    /**
     * This is the first event in a room and cannot be changed. It acts as the root of all other events.
     */
    public static final String TYPE = "m.room.create";

    @Override
    public String getType() {
        return TYPE;
    }
}
