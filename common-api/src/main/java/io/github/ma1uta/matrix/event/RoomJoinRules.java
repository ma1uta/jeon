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

package io.github.ma1uta.matrix.event;

import io.github.ma1uta.matrix.event.content.RoomJoinRulesContent;
import io.github.ma1uta.matrix.support.DeserializerUtil;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

/**
 * A room may be ``public`` meaning anyone can join the room without any prior action. Alternatively, it can be ``invite`` meaning
 * that a user who wishes to join the room must first receive an invite to the room from someone already inside of the room.
 * Currently, ``knock`` and ``private`` are reserved keywords which are not implemented.
 */
@Schema(
    description = "A room may be ``public`` meaning anyone can join the room without any prior action."
        + " Alternatively, it can be ``invite`` meaning that a user who wishes to join the room must first receive"
        + " an invite to the room from someone already inside of the room. Currently, ``knock`` and ``private`` are"
        + " reserved keywords which are not implemented."
)
public class RoomJoinRules extends StateEvent<RoomJoinRulesContent> {

    public RoomJoinRules() {
    }

    public RoomJoinRules(Map props) {
        super(props);
        setContent(DeserializerUtil.toObject(props, "content", RoomJoinRulesContent::new));
    }

    @Override
    public String getType() {
        return EventType.ROOM_JOIN_RULES;
    }
}
