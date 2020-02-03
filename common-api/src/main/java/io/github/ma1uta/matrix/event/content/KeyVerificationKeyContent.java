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
 * Sends the ephemeral public key for a device to the partner device. Typically sent as a to-device event.
 */
@Schema(
    description = "Sends the ephemeral public key for a device to the partner device. Typically sent as a to-device event."
)
public class KeyVerificationKeyContent implements EventContent {

    /**
     * Required. An opaque identifier for the verification process.
     * Must be the same as the one used for the m.key.verification.start message.
     */
    @Schema(
        name = "transaction_id",
        description = "An opaque identifier for the verification process."
            + " Must be the same as the one used for the m.key.verification.start message.",
        required = true
    )
    @JsonbProperty("transaction_id")
    private String transactionId;

    /**
     * Required. The device's ephemeral public key, encoded as unpadded base64.
     */
    @Schema(
        description = "The device's ephemeral public key, encoded as unpadded base64.",
        required = true
    )
    private String key;

    @JsonProperty("transaction_id")
    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
