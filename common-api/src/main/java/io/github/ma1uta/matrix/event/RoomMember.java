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

import io.github.ma1uta.matrix.event.content.RoomMemberContent;
import io.github.ma1uta.matrix.support.DeserializerUtil;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

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

    public RoomMember() {
    }

    public RoomMember(Map props) {
        super(props);
        setContent(DeserializerUtil.toObject(props, "content", RoomMemberContent::new));
    }

    @Override
    public String getType() {
        return EventType.ROOM_MEMBER;
    }
}
