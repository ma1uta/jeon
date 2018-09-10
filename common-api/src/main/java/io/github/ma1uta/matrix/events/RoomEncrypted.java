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
import io.github.ma1uta.matrix.events.encrypted.MegolmEncrypted;
import io.github.ma1uta.matrix.events.encrypted.OlmEncrypted;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This event type is used when sending encrypted events. It can be used either within a room
 * (in which case it will have all of the Room Event fields), or as a to-device event.
 */
@ApiModel(description = "This event type is used when sending encrypted events. It can be used either within a room"
    + " (in which case it will have all of the Room Event fields), or as a to-device event.",
    subTypes = {
        MegolmEncrypted.class,
        OlmEncrypted.class
    })
public abstract class RoomEncrypted implements EventContent {

    /**
     * Required. The encryption algorithm used to encrypt this event. The value of this field determines which other
     * properties will be present. One of: ["m.olm.v1.curve25519-aes-sha2", "m.megolm.v1.aes-sha2"].
     *
     * @return algorithm.
     */
    @ApiModelProperty(
        name = "algorithm",
        value = "The encryption algorithm used to encrypt this event. The value of this field determines which other properties"
            + " will be present.",
        required = true,
        allowableValues = "m.olm.v1.curve25519-aes-sha2, m.megolm.v1.aes-sha2"
    )
    @JsonProperty(value = "algorithm", access = JsonProperty.Access.READ_ONLY)
    public abstract String getAlgorithm();

    /**
     * Required. The Curve25519 key of the sender.
     */
    @ApiModelProperty(
        name = "sender_key",
        value = "The Curve25519 key of the sender.",
        required = true
    )
    @JsonProperty("sender_key")
    private String senderKey;

    /**
     * The ID of the sending device. Required with Megolm.
     */
    @ApiModelProperty(
        name = "device_id",
        value = "The ID of the sending device. Required with Megolm."
    )
    @JsonProperty("device_id")
    private String deviceId;

    /**
     * The ID of the session used to encrypt the message. Required with Megolm.
     */
    @ApiModelProperty(
        name = "session_id",
        value = "The ID of the session used to encrypt the message. Required with Megolm."
    )
    @JsonProperty("session_id")
    private String sessionId;

    public String getSenderKey() {
        return senderKey;
    }

    public void setSenderKey(String senderKey) {
        this.senderKey = senderKey;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
