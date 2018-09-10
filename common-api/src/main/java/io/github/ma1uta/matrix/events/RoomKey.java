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

package io.github.ma1uta.matrix.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ma1uta.matrix.EventContent;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This event type is used to exchange keys for end-to-end encryption. Typically it is encrypted as an m.room.encrypted event,
 * then sent as a to-device event.
 */
@ApiModel(description = "This event type is used to exchange keys for end-to-end encryption."
    + " Typically it is encrypted as an m.room.encrypted event, then sent as a to-device event.")
public class RoomKey implements EventContent {

    /**
     * Required. The encryption algorithm the key in this event is to be used with. Must be 'm.megolm.v1.aes-sha2'.
     */
    @ApiModelProperty(
        value = "The encryption algorithm the key in this event is to be used with. Must be 'm.megolm.v1.aes-sha2'.",
        required = true
    )
    private String algorithm;

    /**
     * Required. The room where the key is used.
     */
    @ApiModelProperty(
        name = "room_id",
        value = "The room where the key is used.",
        required = true
    )
    @JsonProperty("room_id")
    private String roomId;

    /**
     * Required. The ID of the session that the key is for.
     */
    @ApiModelProperty(
        name = "session_id",
        value = "The ID of the session that the key is for.",
        required = true
    )
    @JsonProperty("session_id")
    private String sessionId;

    /**
     * Required. The key to be exchanged.
     */
    @ApiModelProperty(
        name = "session_key",
        value = "The key to be exchanged.",
        required = true
    )
    @JsonProperty("session_key")
    private String sessionKey;

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }
}
