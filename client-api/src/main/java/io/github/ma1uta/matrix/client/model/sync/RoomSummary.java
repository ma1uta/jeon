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

package io.github.ma1uta.matrix.client.model.sync;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import javax.json.bind.annotation.JsonbProperty;

/**
 * Room summary.
 */
@Schema(
    description = "Room summary."
)
public class RoomSummary {

    /**
     * The users which can be used to generate a room name if the room does not have one.
     * Required if the room's m.room.name or m.room.canonical_alias state events are unset or empty.
     * <p/>
     * This should be the first 5 members of the room, ordered by stream ordering, which are joined or invited.
     * The list must never include the client's own user ID. When no joined or invited members are available,
     * this should consist of the banned and left users. More than 5 members may be provided,
     * however less than 5 should only be provided when there are less than 5 members to represent.
     * <p/>
     * When lazy-loading room members is enabled, the membership events for the heroes MUST be included in the state,
     * unless they are redundant. When the list of users changes, the server notifies the client by sending a fresh list of heroes.
     * If there are no changes since the last sync, this field may be omitted.
     */
    @Schema(
        name = "m.heroes",
        description = "The users which can be used to generate a room name if the room does not have one."
            + " Required if the room's m.room.name or m.room.canonical_alias state events are unset or empty."
            + " This should be the first 5 members of the room, ordered by stream ordering, which are joined or invited."
            + " The list must never include the client's own user ID. When no joined or invited members are available,"
            + " this should consist of the banned and left users. More than 5 members may be provided,"
            + " however less than 5 should only be provided when there are less than 5 members to represent."
            + " When lazy-loading room members is enabled, the membership events for the heroes MUST be included in the state,"
            + " unless they are redundant. When the list of users changes, the server notifies the client by sending a fresh"
            + " list of heroes. If there are no changes since the last sync, this field may be omitted."
    )
    @JsonbProperty("m.heroes")
    private List<String> heroes;

    /**
     * The number of users with membership of join, including the client's own user ID.
     * If this field has not changed since the last sync, it may be omitted. Required otherwise.
     */
    @Schema(
        name = "m.joined_member_count",
        description = "The number of users with membership of join, including the client's own user ID."
            + " If this field has not changed since the last sync, it may be omitted. Required otherwise."
    )
    @JsonbProperty("m.joined_member_count")
    private Long joinedMemberCount;

    /**
     * The number of users with membership of invite. If this field has not changed since the last sync, it may be omitted.
     * Required otherwise.
     */
    @Schema(
        name = "m.invited_member_count",
        description = "The number of users with membership of invite. If this field has not changed since the last sync, it may be omitted."
            + " Required otherwise."
    )
    @JsonbProperty("m.invited_member_count")
    private Long invitedMemberCount;

    @JsonProperty("m.heroes")
    public List<String> getHeroes() {
        return heroes;
    }

    public void setHeroes(List<String> heroes) {
        this.heroes = heroes;
    }

    @JsonProperty("m.joined_member_count")
    public Long getJoinedMemberCount() {
        return joinedMemberCount;
    }

    public void setJoinedMemberCount(Long joinedMemberCount) {
        this.joinedMemberCount = joinedMemberCount;
    }

    @JsonProperty("m.invited_member_count")
    public Long getInvitedMemberCount() {
        return invitedMemberCount;
    }

    public void setInvitedMemberCount(Long invitedMemberCount) {
        this.invitedMemberCount = invitedMemberCount;
    }
}
