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
 * Accepts a previously sent m.key.verification.start message. Typically sent as a to-device event.
 */
@Schema(
    description = "Accepts a previously sent m.key.verification.start message. Typically sent as a to-device event."
)
public class KeyVerificationAcceptContent implements EventContent {

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
     * Required. The verification method to use. Must be 'm.sas.v1'.
     */
    @Schema(
        description = "The verification method to use. Must be 'm.sas.v1'.",
        required = true
    )
    private String method;

    /**
     * Required. The key agreement protocol the device is choosing to use, out of the options in the m.key.verification.start message.
     */
    @Schema(
        name = "key_agreement_protocol",
        description = "The key agreement protocol the device is choosing to use,"
            + " out of the options in the m.key.verification.start message.",
        required = true
    )
    @JsonbProperty("key_agreement_protocol")
    private String keyAgreementProtocol;

    /**
     * Required. The hash method the device is choosing to use, out of the options in the m.key.verification.start message.
     */
    @Schema(
        description = "The hash method the device is choosing to use, out of the options in the m.key.verification.start message.",
        required = true
    )
    private String hash;

    /**
     * Required. The message authentication code the device is choosing to use, out of the options in the m.key.verification.start message.
     */
    @Schema(
        name = "message_authentication_code",
        description = "The message authentication code the device is choosing to use,"
            + " out of the options in the m.key.verification.start message.",
        required = true
    )
    @JsonbProperty("message_authentication_code")
    private String messageAuthenticationCode;

    /**
     * Required. The SAS methods both devices involved in the verification process understand.
     * Must be a subset of the options in the m.key.verification.start message. One of: ["decimal", "emoji"].
     */
    @Schema(
        name = "short_authentication_string",
        description = "The SAS methods both devices involved in the verification process understand."
            + " Must be a subset of the options in the m.key.verification.start message.",
        required = true,
        allowableValues = {"decimal", "emoji"}
    )
    @JsonbProperty("short_authentication_string")
    private String shortAuthenticationString;

    /**
     * Required. The hash (encoded as unpadded base64) of the concatenation of the device's ephemeral public key
     * (encoded as unpadded base64) and the canonical JSON representation of the m.key.verification.start message.
     */
    @Schema(
        description = "The hash (encoded as unpadded base64) of the concatenation of the device's ephemeral public key"
            + " (encoded as unpadded base64) and the canonical JSON representation of the m.key.verification.start message.",
        required = true
    )
    private String commitment;

    @JsonProperty("transaction_id")
    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @JsonProperty("key_agreement_protocol")
    public String getKeyAgreementProtocol() {
        return keyAgreementProtocol;
    }

    public void setKeyAgreementProtocol(String keyAgreementProtocol) {
        this.keyAgreementProtocol = keyAgreementProtocol;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    @JsonProperty("message_authentication_code")
    public String getMessageAuthenticationCode() {
        return messageAuthenticationCode;
    }

    public void setMessageAuthenticationCode(String messageAuthenticationCode) {
        this.messageAuthenticationCode = messageAuthenticationCode;
    }

    @JsonProperty("short_authentication_string")
    public String getShortAuthenticationString() {
        return shortAuthenticationString;
    }

    public void setShortAuthenticationString(String shortAuthenticationString) {
        this.shortAuthenticationString = shortAuthenticationString;
    }

    public String getCommitment() {
        return commitment;
    }

    public void setCommitment(String commitment) {
        this.commitment = commitment;
    }
}
