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
import io.github.ma1uta.matrix.event.encrypted.MegolmEncryptedContent;
import io.github.ma1uta.matrix.event.encrypted.OlmEncryptedContent;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;

/**
 * This event type is used when sending encrypted events. It can be used either within a room
 * (in which case it will have all of the Room Event fields), or as a to-device event.
 */
@Schema(
    description = "This event type is used when sending encrypted events. It can be used either within a room"
        + " (in which case it will have all of the Room Event fields), or as a to-device event.",
    subTypes = {
        MegolmEncryptedContent.class,
        OlmEncryptedContent.class
    }
)
public abstract class RoomEncryptedContent implements EventContent {

    /**
     * Required. The encryption algorithm used to encrypt this event. The value of this field determines which other
     * properties will be present. One of: ["m.olm.v1.curve25519-aes-sha2", "m.megolm.v1.aes-sha2"].
     *
     * @return algorithm.
     */
    @Schema(
        name = "algorithm",
        description = "The encryption algorithm used to encrypt this event. The value of this field determines which other properties"
            + " will be present.",
        required = true,
        allowableValues = "m.olm.v1.curve25519-aes-sha2, m.megolm.v1.aes-sha2"
    )
    @JsonbProperty("algorithm")
    public abstract String getAlgorithm();

    /**
     * Required. The Curve25519 key of the sender.
     */
    @Schema(
        name = "sender_key",
        description = "The Curve25519 key of the sender.",
        required = true
    )
    @JsonbProperty("sender_key")
    private String senderKey;

    /**
     * The ID of the sending device. Required with Megolm.
     */
    @Schema(
        name = "device_id",
        description = "The ID of the sending device. Required with Megolm."
    )
    @JsonbProperty("device_id")
    private String deviceId;

    /**
     * The ID of the session used to encrypt the message. Required with Megolm.
     */
    @Schema(
        name = "session_id",
        description = "The ID of the session used to encrypt the message. Required with Megolm."
    )
    @JsonbProperty("session_id")
    private String sessionId;

    @JsonProperty("sender_key")
    public String getSenderKey() {
        return senderKey;
    }

    public void setSenderKey(String senderKey) {
        this.senderKey = senderKey;
    }

    @JsonProperty("device_id")
    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @JsonProperty("session_id")
    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
