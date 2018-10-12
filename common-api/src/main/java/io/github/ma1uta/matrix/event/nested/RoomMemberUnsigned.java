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

package io.github.ma1uta.matrix.event.nested;

import io.github.ma1uta.matrix.StrippedState;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * Unsigned data.
 */
@Schema(
    description = "Unsigned data."
)
public class RoomMemberUnsigned {

    /**
     * A subset of the state of the room at the time of the invite, if membership is invite. Note that this state is informational,
     * and SHOULD NOT be trusted; once the client has joined the room, it SHOULD fetch the live state from the server and discard
     * the invite_room_state. Also, clients must not rely on any particular state being present here; they SHOULD behave properly
     * (with possibly a degraded but not a broken experience) in the absence of any particular events here. If they are set on the room,
     * at least the state for m.room.avatar, m.room.canonical_alias, m.room.join_rules, and m.room.name SHOULD be included.
     */
    @Schema(
        description = "A subset of the state of the room at the time of the invite, if membership is invite. Note that this state"
            + " is informational, and SHOULD NOT be trusted; once the client has joined the room, it SHOULD fetch the live state from"
            + " the server and discard the invite_room_state. Also, clients must not rely on any particular state being present here;"
            + " they SHOULD behave properly (with possibly a degraded but not a broken experience) in the absence of any particular"
            + " events here. If they are set on the room, at least the state for m.room.avatar, m.room.canonical_alias, m.room.join_rules,"
            + " and m.room.name SHOULD be included."
    )
    private List<StrippedState> events;

    public List<StrippedState> getEvents() {
        return events;
    }

    public void setEvents(List<StrippedState> events) {
        this.events = events;
    }
}
