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
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * The current location of the user's read marker in a room. This event appears in the user's room account data for the room the marker
 * is applicable for.
 */
@Schema(
    description = "The current location of the user's read marker in a room. This event appears in the user's room account"
        + " data for the room the marker is applicable for."
)
public class FullyRead implements EventContent {

    /**
     * Required. The event the user's read marker is located at in the room.
     */
    @Schema(
        name = "event_id",
        description = "The event the user's read marker is located at in the room.",
        required = true
    )
    @JsonProperty("event_id")
    private String eventId;

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }
}
