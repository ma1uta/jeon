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

import io.github.ma1uta.matrix.event.content.RoomMemberContent;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Adjusts the membership state for a user in a room. It is preferable to use the membership APIs (/rooms/&lt;room id&gt;/invite etc)
 * when performing membership actions rather than adjusting the state directly as there are a restricted set of valid transformations.
 * For example, user A cannot force user B to join a room, and trying to force this state change directly will fail.
 */
@Schema(
    description = "Adjusts the membership state for a user in a room. It is preferable to use the membership APIs"
        + " (/rooms/<room id>/invite etc) when performing membership actions rather than adjusting the state directly"
        + " as there are a restricted set of valid transformations. For example, user A cannot force user B to join a room,"
        + " and trying to force this state change directly will fail."
)
public class RoomMember extends StateEvent<RoomMemberContent> {

    /**
     * Adjusts the membership state for a user in a room. It is preferable to use the membership APIs
     * (/rooms/&lt;room id&gt;/invite etc)
     * when performing membership actions rather than adjusting the state directly as there are a restricted set of valid
     * transformations. For example, user A cannot force user B to join a room, and trying to force this state change directly
     * will fail.
     * <br>
     * The following membership states are specified:
     * <ul>
     * <li>invite - The user has been invited to join a room, but has not yet joined it. They may not participate in the room
     * until they join.</li>
     * <li>join - The user has joined the room (possibly after accepting an invite), and may participate in it.</li>
     * <li>leave - The user was once joined to the room, but has since left (possibly by choice, or possibly by being kicked).</li>
     * <li>ban - The user has been banned from the room, and is no longer allowed to join it until they are un-banned from
     * the room (by having their membership state set to a value other than ban).</li>
     * <li>knock - This is a reserved word, which currently has no meaning.</li>
     * </ul>
     * The third_party_invite property will be set if this invite is an invite event and is the successor
     * of an m.room.third_party_invite event, and absent otherwise.
     * <br>
     * This event may also include an invite_room_state key outside the content key. If present, this contains an array of
     * StrippedState Events. These events provide information on a subset of state events such as the room name.
     */
    public static final String TYPE = "m.room.member";

    @Override
    public String getType() {
        return TYPE;
    }
}
