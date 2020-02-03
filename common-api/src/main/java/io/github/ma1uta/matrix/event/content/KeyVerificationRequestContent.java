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
 * Requests a key verification with another user's devices. Typically sent as a to-device event.
 */
public class KeyVerificationRequestContent implements EventContent {

    /**
     * Required. The device ID which is initiating the request.
     */
    @Schema(
        name = "from_device",
        description = "The device ID which is initiating the request.",
        required = true
    )
    @JsonbProperty("from_device")
    private String fromDevice;

    /**
     * Required. An opaque identifier for the verification request. Must be unique with respect to the devices involved.
     */
    @Schema(
        name = "transaction_id",
        description = "An opaque identifier for the verification request. Must be unique with respect to the devices involved.",
        required = true
    )
    @JsonbProperty("transaction_id")
    private String transactionId;

    /**
     * Required. The verification methods supported by the sender.
     */
    @Schema(
        description = "The verification methods supported by the sender.",
        required = true
    )
    private List<String> methods;

    /**
     * Required. The POSIX timestamp in milliseconds for when the request was made.
     * If the request is in the future by more than 5 minutes or more than 10 minutes in the past,
     * the message should be ignored by the receiver.
     */
    @Schema(
        description = "The POSIX timestamp in milliseconds for when the request was made."
            + " If the request is in the future by more than 5 minutes or more than 10 minutes in the past,"
            + " the message should be ignored by the receiver.",
        required = true
    )
    private Long timestamp;

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

    public List<String> getMethods() {
        return methods;
    }

    public void setMethods(List<String> methods) {
        this.methods = methods;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
