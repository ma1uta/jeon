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
import io.github.ma1uta.matrix.event.nested.Answer;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;

/**
 * Message Event.
 * <p>
 * This event is sent by the callee when they wish to answer the call.
 * </p>
 */
@Schema(
    description = "This event is sent by the callee when they wish to answer the call."
)
public class CallAnswerContent implements EventContent {

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
     * Required. The session description object.
     */
    @Schema(
        description = "The session description object.",
        required = true
    )
    private Answer answer;

    /**
     * Required. The version of the VoIP specification this message adheres to. This specification is version 0.
     */
    @Schema(
        description = "The version of the VoIP specification this message adheres to. This specification is version 0.",
        required = true
    )
    private Long version;

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

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
}
