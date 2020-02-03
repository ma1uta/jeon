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
 * State Event.
 * <br>
 * state_key: A zero-length string.
 * <br>
 * Defines how messages sent in this room should be encrypted.
 */
@Schema(
    description = "State Event. Defines how messages sent in this room should be encrypted."
)
public class RoomEncryptionContent implements EventContent {

    /**
     * Required. The encryption algorithm to be used to encrypt messages sent in this room. Must be 'm.megolm.v1.aes-sha2'.
     */
    @Schema(
        description = "The encryption algorithm to be used to encrypt messages sent in this room. Must be 'm.megolm.v1.aes-sha2'.",
        required = true
    )
    private String algorithm;

    /**
     * How long the session should be used before changing it. 604800000 (a week) is the recommended default.
     */
    @Schema(
        name = "rotation_period_ms",
        description = "How long the session should be used before changing it. 604800000 (a week) is the recommended default."
    )
    @JsonbProperty("rotation_period_ms")
    private Long rotationPeriodMs;

    /**
     * How many messages should be sent before changing the session. 100 is the recommended default.
     */
    @Schema(
        name = "rotation_period_msgs",
        description = "How many messages should be sent before changing the session. 100 is the recommended default."
    )
    @JsonbProperty("rotation_period_msgs")
    private Long rotationPeriodMsgs;

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    @JsonProperty("rotation_period_ms")
    public Long getRotationPeriodMs() {
        return rotationPeriodMs;
    }

    public void setRotationPeriodMs(Long rotationPeriodMs) {
        this.rotationPeriodMs = rotationPeriodMs;
    }

    @JsonProperty("rotation_period_msgs")
    public Long getRotationPeriodMsgs() {
        return rotationPeriodMsgs;
    }

    public void setRotationPeriodMsgs(Long rotationPeriodMsgs) {
        this.rotationPeriodMsgs = rotationPeriodMsgs;
    }
}
