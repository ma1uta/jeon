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
 * Message Event.
 * <p>
 * Sent by either party to signal their termination of the call. This can be sent either once the call has has been
 * established or before to abort the call.
 * </p>
 */
@Schema(
    description = "Sent by either party to signal their termination of the call. This can be sent either once the call has has been"
        + " established or before to abort the call."
)
public class CallHangupContent implements EventContent {

    /**
     * Hangup's reasons.
     */
    public static class Reason {

        protected Reason() {
            // singleton
        }

        /**
         * ICE failed.
         */
        public static final String ICE_FAILED = "ice_failed";

        /**
         * Invite timeout.
         */
        public static final String INVITE_TIMEOUT = "invite_timeout";
    }

    /**
     * Required. The ID of the call this event relates to.
     */
    @Schema(
        description = "The ID of the call this event relates to.",
        required = true
    )
    @JsonbProperty("call_id")
    private String callId;

    /**
     * Required. The version of the VoIP specification this message adheres to. This specification is version 0.
     */
    @Schema(
        description = "The version of the VoIP specification this message adheres to. This specification is version 0.",
        required = true
    )
    private Long version;

    /**
     * Optional error reason for the hangup. This should not be provided when the user naturally ends or rejects the call.
     * When there was an error in the call negotiation, this should be ice_failed for when ICE negotiation fails or invite_timeout
     * for when the other party did not answer in time. One of: ["ice_failed", "invite_timeout"]
     */
    @Schema(
        description = "Optional error reason for the hangup. This should not be provided when the user naturally ends or rejects the call."
            + " When there was an error in the call negotiation, this should be ice_failed for when ICE negotiation fails or invite_timeout"
            + " for when the other party did not answer in time.",
        allowableValues = {"ice_failed", "invite_timeout"}
    )
    private String reason;

    @JsonProperty("call_id")
    public String getCallId() {
        return callId;
    }

    public void setCallId(String callId) {
        this.callId = callId;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
