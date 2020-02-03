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

import io.github.ma1uta.matrix.event.content.RoomAliasesContent;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * This event is sent by a homeserver directly to inform of changes to the list of aliases it knows about for that room.
 * The state_key for this event is set to the homeserver which owns the room alias. The entire set of known aliases for
 * the room is the union of all the m.room.aliases events, one for each homeserver. Clients should check the validity of
 * any room alias given in this list before presenting it to the user as trusted fact. The lists given by this event should
 * be considered simply as advice on which aliases might exist, for which the client can perform the lookup to confirm whether
 * it receives the correct room ID.
 */
@Schema(
    description = "This event is sent by a homeserver directly to inform of changes to the list of aliases it knows about"
        + " for that room. The state_key for this event is set to the homeserver which owns the room alias. The entire set of known"
        + " aliases for the room is the union of all the m.room.aliases events, one for each homeserver. Clients should check the"
        + " validity of any room alias given in this list before presenting it to the user as trusted fact. The lists given by this"
        + " event should be considered simply as advice on which aliases might exist, for which the client can perform the lookup"
        + " to confirm whether it receives the correct room ID."
)
public class RoomAliases extends StateEvent<RoomAliasesContent> {

    /**
     * This event is sent by a homeserver directly to inform of changes to the list of aliases it knows about for that room.
     * The state_key for this event is set to the homeserver which owns the room alias. The entire set of known aliases for
     * the room is the union of all the m.room.aliases events, one for each homeserver. Clients should check the validity of
     * any room alias given in this list before presenting it to the user as trusted fact. The lists given by this event should
     * be considered simply as advice on which aliases might exist, for which the client can perform the lookup to confirm whether
     * it receives the correct room ID.
     */
    public static final String TYPE = "m.room.aliases";

    @Override
    public String getType() {
        return TYPE;
    }
}
