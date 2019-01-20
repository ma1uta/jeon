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

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ma1uta.matrix.Id;
import io.github.ma1uta.matrix.event.content.TypingContent;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;

/**
 * Informs the client of the list of users currently typing.
 */
@Schema(
    description = "Informs the client of the list of users currently typing."
)
public class Typing extends Event<TypingContent> {

    /**
     * Informs the client of the list of users currently typing.
     */
    public static final String TYPE = "m.typing";

    /**
     * Required. The ID of the room associated with this event.
     */
    @Schema(
        name = "room_id",
        description = "The ID of the room associated with this event.",
        required = true
    )
    @JsonbProperty("room_id")
    private Id roomId;

    @JsonProperty("room_id")
    public Id getRoomId() {
        return roomId;
    }

    public void setRoomId(Id roomId) {
        this.roomId = roomId;
    }

    @Override
    public String getType() {
        return TYPE;
    }
}
