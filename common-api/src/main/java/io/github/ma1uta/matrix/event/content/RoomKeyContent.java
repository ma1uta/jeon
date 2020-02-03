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

package io.github.ma1uta.matrix.event.content;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;

/**
 * This event type is used to exchange keys for end-to-end encryption. Typically it is encrypted as an m.room.encrypted event,
 * then sent as a to-device event.
 */
@Schema(
    description = "This event type is used to exchange keys for end-to-end encryption."
        + " Typically it is encrypted as an m.room.encrypted event, then sent as a to-device event."
)
public class RoomKeyContent implements EventContent {

    /**
     * Required. The encryption algorithm the key in this event is to be used with. Must be 'm.megolm.v1.aes-sha2'.
     */
    @Schema(
        description = "The encryption algorithm the key in this event is to be used with. Must be 'm.megolm.v1.aes-sha2'.",
        required = true
    )
    private String algorithm;

    /**
     * Required. The room where the key is used.
     */
    @Schema(
        name = "room_id",
        description = "The room where the key is used.",
        required = true
    )
    @JsonbProperty("room_id")
    private String roomId;

    /**
     * Required. The ID of the session that the key is for.
     */
    @Schema(
        name = "session_id",
        description = "The ID of the session that the key is for.",
        required = true
    )
    @JsonbProperty("session_id")
    private String sessionId;

    /**
     * Required. The key to be exchanged.
     */
    @Schema(
        name = "session_key",
        description = "The key to be exchanged.",
        required = true
    )
    @JsonbProperty("session_key")
    private String sessionKey;

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    @JsonProperty("room_id")
    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    @JsonProperty("session_id")
    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @JsonProperty("session_key")
    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }
}
