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
 * **NB: Usage of this event is discouraged in favour of the** `receipts module`_.
 * **Most clients will not recognise this event.** Feedback events are events sent to acknowledge a message in some way.
 * There are two supported acknowledgements: ``delivered`` (sent when the event has been received) and ``read``
 * (sent when the event has been observed by the end-user).
 * The ``target_event_id`` should reference the ``m.room.message`` event being acknowledged.
 *
 * @deprecated in favor of {@link ReceiptContent}.
 */
@Deprecated
@Schema(
    description = "DEPRECATED. **NB: Usage of this event is discouraged in favour of the** `receipts module`_."
)
public class RoomMessageFeedbackContent implements EventContent {

    /**
     * The event that this feedback is related to.
     */
    @Schema(
        description = "The event that this feedback is related to."
    )
    @JsonbProperty("target_event_id")
    private String targetEventId;

    /**
     * The type of feedback.
     */
    @Schema(
        description = "The type of feedback.",
        allowableValues = "delivered, read"
    )
    private String type;

    @JsonProperty("target_event_id")
    public String getTargetEventId() {
        return targetEventId;
    }

    public void setTargetEventId(String targetEventId) {
        this.targetEventId = targetEventId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
