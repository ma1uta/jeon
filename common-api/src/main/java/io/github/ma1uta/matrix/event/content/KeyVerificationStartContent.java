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

import java.util.List;
import javax.json.bind.annotation.JsonbProperty;

/**
 * Begins a key verification process. Typically sent as a to-device event.
 */
@Schema(
    description = "Begins a key verification process. Typically sent as a to-device event."
)
public class KeyVerificationStartContent implements EventContent {

    /**
     * Required. The device ID which is initiating the process.
     */
    @Schema(
        name = "from_device",
        description = "The device ID which is initiating the process.",
        required = true
    )
    @JsonbProperty("from_device")
    private String fromDevice;

    /**
     * Required. An opaque identifier for the verification process. Must be unique with respect to the devices involved.
     * Must be the same as the transaction_id given in the m.key.verification.request if this process is originating from a request.
     */
    @Schema(
        name = "transaction_id",
        description = "An opaque identifier for the verification process. Must be unique with respect to the devices involved."
            + " Must be the same as the transaction_id given in the m.key.verification.request if this process is originating"
            + " from a request.",
        required = true
    )
    @JsonbProperty("transaction_id")
    private String transactionId;

    /**
     * Required. The verification method to use.
     */
    @Schema(
        description = "The verification method to use.",
        required = true
    )
    private String method;

    /**
     * Optional method to use to verify the other user's key with. Applicable when the method chosen only verifies one user's key.
     */
    @Schema(
        name = "next_method",
        description = "Optional method to use to verify the other user's key with."
            + " Applicable when the method chosen only verifies one user's key."
    )
    @JsonbProperty("next_method")
    private String nextMethod;

    /**
     * Required. The key agreement protocols the sending device understands. Must include at least curve25519.
     */
    @Schema(
        name = "key_agreement_protocol",
        description = "The key agreement protocols the sending device understands. Must include at least curve25519."
    )
    @JsonbProperty("key_agreement_protocol")
    private List<String> keyAgreementProtocol;

    /**
     * Required. The hash methods the sending device understands. Must include at least sha256.
     */
    @Schema(
        description = "The hash methods the sending device understands. Must include at least sha256."
    )
    private List<String> hashes;

    /**
     * Required. The message authentication codes that the sending device understands. Must include at least hkdf-hmac-sha256.
     */
    @Schema(
        name = "message_authentication_codes",
        description = "The message authentication codes that the sending device understands. Must include at least hkdf-hmac-sha256."
    )
    @JsonbProperty("message_authentication_codes")
    private List<String> messageAuthenticationCodes;

    /**
     * Required. The SAS methods the sending device (and the sending device's user) understands. Must include at least decimal.
     * Optionally can include emoji. One of: ["decimal", "emoji"]
     */
    @Schema(
        name = "short_authentication_string",
        description = "The SAS methods the sending device (and the sending device's user) understands. Must include at least decimal."
            + " Optionally can include emoji.",
        allowableValues = {"decimal", "emoji"}
    )
    @JsonbProperty("short_authentication_string")
    private List<String> shortAuthenticationString;

    @JsonProperty("from_device")
    public String getFromDevice() {
        return fromDevice;
    }

    public void setFromDevice(String fromDevice) {
        this.fromDevice = fromDevice;
    }

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

    @JsonProperty("next_method")
    public String getNextMethod() {
        return nextMethod;
    }

    public void setNextMethod(String nextMethod) {
        this.nextMethod = nextMethod;
    }

    @JsonProperty("key_agreement_protocol")
    public List<String> getKeyAgreementProtocol() {
        return keyAgreementProtocol;
    }

    public void setKeyAgreementProtocol(List<String> keyAgreementProtocol) {
        this.keyAgreementProtocol = keyAgreementProtocol;
    }

    public List<String> getHashes() {
        return hashes;
    }

    public void setHashes(List<String> hashes) {
        this.hashes = hashes;
    }

    @JsonProperty("message_authentication_codes")
    public List<String> getMessageAuthenticationCodes() {
        return messageAuthenticationCodes;
    }

    public void setMessageAuthenticationCodes(List<String> messageAuthenticationCodes) {
        this.messageAuthenticationCodes = messageAuthenticationCodes;
    }

    @JsonProperty("short_authentication_string")
    public List<String> getShortAuthenticationString() {
        return shortAuthenticationString;
    }

    public void setShortAuthenticationString(List<String> shortAuthenticationString) {
        this.shortAuthenticationString = shortAuthenticationString;
    }
}
