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

package io.github.ma1uta.matrix.client.model.event;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

/**
 * Joined room members.
 */
@Schema(
    description = "Joined room members."
)
public class JoinedMembersResponse {

    /**
     * A map from user ID to a RoomMemberContent object.
     */
    @Schema(
        description = "A map from user ID to a RoomMemberContent object."
    )
    private Map<String, RoomMember> joined;

    public Map<String, RoomMember> getJoined() {
        return joined;
    }

    public void setJoined(Map<String, RoomMember> joined) {
        this.joined = joined;
    }
}
