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

package io.github.ma1uta.matrix.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ma1uta.matrix.EventContent;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Message Event.
 * <p>
 * Sent by either party to signal their termination of the call. This can be sent either once the call has has been
 * established or before to abort the call.
 * </p>
 */
@ApiModel(description = "Sent by either party to signal their termination of the call. This can be sent either once the call has has been"
    + " established or before to abort the call.")
public class CallHangup implements EventContent {

    /**
     * Required. The ID of the call this event relates to.
     */
    @ApiModelProperty(
        value = "The ID of the call this event relates to.",
        required = true
    )
    @JsonProperty("call_id")
    private String callId;

    /**
     * Required. The version of the VoIP specification this message adheres to. This specification is version 0.
     */
    @ApiModelProperty(
        value = "The version of the VoIP specification this message adheres to. This specification is version 0.",
        required = true
    )
    private Long version;

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
}
