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
 * Cancels a key verification process/request. Typically sent as a to-device event.
 */
@Schema(
    description = "Cancels a key verification process/request. Typically sent as a to-device event."
)
public class KeyVerificationCancelContent implements EventContent {

    /**
     * Cancel codes.
     */
    public static class CancelCode {

        protected CancelCode() {
            // singleton
        }

        /**
         * The user cancelled the verification.
         */
        public static final String M_USER = "m.user";

        /**
         * The verification process timed out. Verification processes can define their own timeout parameters.
         */
        public static final String M_TIMEOUT = "m.timeout";

        /**
         * The device does not know about the given transaction ID.
         */
        public static final String M_UNKNOWN_TRANSACTION = "m.unknown_transaction";

        /**
         * The device does not know how to handle the requested method.
         * This should be sent for m.key.verification.start messages and messages defined by individual verification processes.
         */
        public static final String M_UNKNOWN_METHOD = "m.unknown_method";

        /**
         * The device received an unexpected message. Typically raised when one of the parties is handling the verification out of order.
         */
        public static final String M_UNEXPECTED_MESSAGE = "m.unexpected_message";

        /**
         * The key was not verified.
         */
        public static final String M_KEY_MISMATCH = "m.key_mismatch";

        /**
         * The expected user did not match the user verified.
         */
        public static final String M_USER_MISMATCH = "m.user_mismatch";

        /**
         * The message received was invalid.
         */
        public static final String M_INVALID_MESSAGE = "m.invalid_message";

        /**
         * A m.key.verification.request was accepted by a different device.
         * The device receiving this error can ignore the verification request.
         */
        public static final String M_ACCEPTED = "m.accepted";
    }

    /**
     * Required. The opaque identifier for the verification process/request.
     */
    @Schema(
        name = "transaction_id",
        description = "The opaque identifier for the verification process/request.",
        required = true
    )
    @JsonbProperty("transaction_id")
    private String transactionId;

    /**
     * Required. A human readable description of the code. The client should only rely on this string if it does not understand the code.
     */
    @Schema(
        description = "A human readable description of the code."
            + " The client should only rely on this string if it does not understand the code.",
        required = true
    )
    private String reason;

    /**
     * Required. The error code for why the process/request was cancelled by the user.
     * Error codes should use the Java package naming convention.
     */
    @Schema(
        description = "The error code for why the process/request was cancelled by the user."
            + " Error codes should use the Java package naming convention.",
        required = true,
        allowableValues = {"m.user", "m.timeout", "m.unknown_transaction", "m.unknown_method", "m.unexpected_message",
            "m.key_mismatch", "m.user_mismatch", "m.invalid_message", "m.accepted"}
    )
    private String code;

    @JsonProperty("transaction_id")
    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
