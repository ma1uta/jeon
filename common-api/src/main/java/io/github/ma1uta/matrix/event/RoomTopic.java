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

import io.github.ma1uta.matrix.event.content.RoomTopicContent;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * A topic is a short message detailing what is currently being discussed in the room. It can also be used as a way to display extra
 * information about the room, which may not be suitable for the room name. The room topic can also be set when creating a room using
 * /createRoom with the topic key.
 */
@Schema(
    description = "A topic is a short message detailing what is currently being discussed in the room. It can also be used as"
        + " a way to display extra information about the room, which may not be suitable for the room name. The room topic can also be"
        + " set when creating a room using /createRoom with the topic key."
)
public class RoomTopic extends StateEvent<RoomTopicContent> {

    /**
     * A topic is a short message detailing what is currently being discussed in the room. It can also be used as a way to
     * display extra information about the room, which may not be suitable for the room name. The room topic can also be set
     * when creating a room using /createRoom with the topic key.
     */
    public static final String TYPE = "m.room.topic";

    @Override
    public String getType() {
        return TYPE;
    }
}
