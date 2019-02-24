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

package io.github.ma1uta.matrix.server.model.federation;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ma1uta.matrix.Id;
import io.github.ma1uta.matrix.event.content.RoomMemberContent;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;

/**
 * Third party invite event.
 */
@Schema(
    description = "Third party invite event."
)
public class ThirdPartyInvite {

    /**
     * Required. The event type. Must be m.room.member
     */
    @Schema(
        description = "The event type. Must be m.room.member",
        required = true,
        defaultValue = "m.room.member"
    )
    private String type;

    /**
     * Required. The room ID the event is for. Must match the ID given in the path.
     */
    @Schema(
        name = "room_id",
        description = "The room ID the event is for. Must match the ID given in the path.",
        required = true
    )
    @JsonbProperty("room_id")
    private Id roomId;

    /**
     * Required. The user ID of the user who sent the original m.room.third_party_invite event.
     */
    @Schema(
        description = "The user ID of the user who sent the original m.room.third_party_invite event.",
        required = true
    )
    private Id sender;

    /**
     * Required. The user ID of the invited user.
     */
    @Schema(
        name = "state_key",
        description = "The user ID of the invited user.",
        required = true
    )
    @JsonbProperty("state_key")
    private Id stateKey;

    /**
     * Required. The event content
     */
    @Schema(
        description = "The event content",
        required = true
    )
    private RoomMemberContent content;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("room_id")
    public Id getRoomId() {
        return roomId;
    }

    public void setRoomId(Id roomId) {
        this.roomId = roomId;
    }

    public Id getSender() {
        return sender;
    }

    public void setSender(Id sender) {
        this.sender = sender;
    }

    @JsonProperty("state_key")
    public Id getStateKey() {
        return stateKey;
    }

    public void setStateKey(Id stateKey) {
        this.stateKey = stateKey;
    }

    public RoomMemberContent getContent() {
        return content;
    }

    public void setContent(RoomMemberContent content) {
        this.content = content;
    }
}
