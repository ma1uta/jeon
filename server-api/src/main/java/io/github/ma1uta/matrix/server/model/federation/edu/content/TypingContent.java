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

package io.github.ma1uta.matrix.server.model.federation.edu.content;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ma1uta.matrix.Id;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;

/**
 * A typing notification EDU for a user in a room.
 */
@Schema(
    description = "A typing notification EDU for a user in a room."
)
public class TypingContent implements EphemeralDataUnitContent {

    /**
     * Required. The room where the user's typing status has been updated.
     */
    @Schema(
        name = "room_id",
        description = "The room where the user's typing status has been updated.",
        required = true
    )
    @JsonbProperty("room_id")
    private Id roomId;

    /**
     * Required. The user ID that has had their typing status changed.
     */
    @Schema(
        name = "user_id",
        description = "The user ID that has had their typing status changed.",
        required = true
    )
    @JsonbProperty("user_id")
    private Id userId;

    /**
     * Required. Whether the user is typing in the room or not.
     */
    @Schema(
        description = "Whether the user is typing in the room or not.",
        required = true
    )
    private Boolean typing;

    @JsonProperty("room_id")
    public Id getRoomId() {
        return roomId;
    }

    public void setRoomId(Id roomId) {
        this.roomId = roomId;
    }

    @JsonProperty("user_id")
    public Id getUserId() {
        return userId;
    }

    public void setUserId(Id userId) {
        this.userId = userId;
    }

    public Boolean getTyping() {
        return typing;
    }

    public void setTyping(Boolean typing) {
        this.typing = typing;
    }
}
