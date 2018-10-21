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

import io.github.ma1uta.matrix.event.content.RoomGuestAccessContent;
import io.github.ma1uta.matrix.support.DeserializerUtil;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

/**
 * This event controls whether guest users are allowed to join rooms. If this event is absent, servers should act as if it is present
 * and has the guest_access value "forbidden".
 */
@Schema(
    description = "This event controls whether guest users are allowed to join rooms. If this event is absent,"
        + " servers should act as if it is present and has the guest_access value \"forbidden\"."
)
public class RoomGuestAccess extends StateEvent<RoomGuestAccessContent> {

    public RoomGuestAccess() {
    }

    public RoomGuestAccess(Map props) {
        super(props);
        setContent(DeserializerUtil.toObject(props, "content", RoomGuestAccessContent::new));
    }

    @Override
    public String getType() {
        return EventType.ROOM_GUEST_ACCESS;
    }
}