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

package io.github.ma1uta.matrix.client.model.eventcontext;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ma1uta.matrix.event.Event;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import javax.json.bind.annotation.JsonbProperty;

/**
 * Events that happened just before and after the specified event.
 */
@Schema(
    description = "Events that happened just before and after the specified event."
)
public class EventContextResponse {

    /**
     * A token that can be used to paginate backwards with.
     */
    @Schema(
        description = "A token that can be used to paginate backwards with."
    )
    private String start;

    /**
     * A token that can be used to paginate forwards with.
     */
    @Schema(
        description = "A token that can be used to paginate forwards with."
    )
    private String end;

    /**
     * A list of room events that happened just before the requested event, in reverse-chronological order.
     */
    @Schema(
        description = "A list of room events that happened just before the requested event, in reverse-chronological order."
    )
    @JsonbProperty("events_before")
    private List<Event> eventsBefore;

    /**
     * Details of the requested event.
     */
    @Schema(
        description = "Details of the requested event."
    )
    private Event event;

    /**
     * A list of room events that happened just after the requested event, in chronological order.
     */
    @Schema(
        description = "A list of room events that happened just after the requested event, in chronological order."
    )
    @JsonbProperty("events_after")
    private List<Event> eventsAfter;

    /**
     * The state of the room at the last event returned.
     */
    @Schema(
        description = "The state of the room at the last event returned."
    )
    private Event state;

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    @JsonProperty("events_before")
    public List<Event> getEventsBefore() {
        return eventsBefore;
    }

    public void setEventsBefore(List<Event> eventsBefore) {
        this.eventsBefore = eventsBefore;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @JsonProperty("events_after")
    public List<Event> getEventsAfter() {
        return eventsAfter;
    }

    public void setEventsAfter(List<Event> eventsAfter) {
        this.eventsAfter = eventsAfter;
    }

    public Event getState() {
        return state;
    }

    public void setState(Event state) {
        this.state = state;
    }
}
