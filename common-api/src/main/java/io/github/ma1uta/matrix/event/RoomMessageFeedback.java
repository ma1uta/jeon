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

package io.github.ma1uta.matrix.event;

import io.github.ma1uta.matrix.event.content.RoomMessageFeedbackContent;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * **NB: Usage of this event is discouraged in favour of the** `receipts module`_.
 * **Most clients will not recognise this event.** Feedback events are events sent to acknowledge a message in some way.
 * There are two supported acknowledgements: ``delivered`` (sent when the event has been received) and ``read``
 * (sent when the event has been observed by the end-user).
 * The ``target_event_id`` should reference the ``m.room.message`` event being acknowledged.
 *
 * @deprecated in favor of {@link Receipt}.
 */
@Deprecated
@Schema(
    description = "DEPRECATED. **NB: Usage of this event is discouraged in favour of the** `receipts module`_."
)
public class RoomMessageFeedback extends RoomEvent<RoomMessageFeedbackContent> {

    /**
     * NB: Usage of this event is discouraged in favour of the receipts module. Most clients will not recognise this event.
     * Feedback events are events sent to acknowledge a message in some way. There are two supported acknowledgements: delivered
     * (sent when the event has been received) and read (sent when the event has been observed by the end-user).
     * The target_event_id should reference the m.room.message event being acknowledged.
     *
     * @deprecated in favor of {@link Receipt#TYPE}.
     */
    @Deprecated
    public static final String TYPE = "m.room.message.feedback";

    @Override
    public String getType() {
        return TYPE;
    }
}
