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

package io.github.ma1uta.matrix.event.content;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

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
}
