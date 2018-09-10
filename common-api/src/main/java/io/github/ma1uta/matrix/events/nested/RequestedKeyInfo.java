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

package io.github.ma1uta.matrix.events.nested;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Requested key info.
 */
@ApiModel(description = "Requested key info.")
public class RequestedKeyInfo {

    /**
     * Required. The encryption algorithm the requested key in this event is to be used with.
     */
    @ApiModelProperty(
        value = "Required. The encryption algorithm the requested key in this event is to be used with.",
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
     * Required. The Curve25519 key of the device which initiated the session originally.
     */
    @ApiModelProperty(
        name = "sender_key",
        value = "The Curve25519 key of the device which initiated the session originally.",
        required = true
    )
    @JsonProperty("sender_key")
    private String senderKey;

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

    public String getSenderKey() {
        return senderKey;
    }

    public void setSenderKey(String senderKey) {
        this.senderKey = senderKey;
    }
}
