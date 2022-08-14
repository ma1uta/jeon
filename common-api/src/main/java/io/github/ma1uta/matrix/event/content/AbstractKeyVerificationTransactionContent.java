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
 * Abstract key verification content with transaction id field.
 */
@Schema(
    description = "Abstract key verification content with transaction id field."
)
public abstract class AbstractKeyVerificationTransactionContent implements EventContent {

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

    @JsonProperty("transaction_id")
    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
