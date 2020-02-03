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

package io.github.ma1uta.matrix.client.model.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ma1uta.matrix.client.model.profile.Profile;
import io.github.ma1uta.matrix.event.Event;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.Map;
import javax.json.bind.annotation.JsonbProperty;

/**
 * Event context.
 */
@Schema(
    description = "Event context."
)
public class EventContextResponse {

    /**
     * Pagination token for the start of the chunk.
     */
    @Schema(
        description = "Pagination token for the start of the chunk."
    )
    private String start;

    /**
     * Pagination token for the end of the chunk.
     */
    @Schema(
        description = "Pagination token for the end of the chunk."
    )
    private String end;

    /**
     * The historic profile information of the users that sent the events returned.
     * <p/>
     * The string key is the user ID for which the profile belongs to.
     */
    @Schema(
        description = "The historic profile information of the users that sent the events returned."
    )
    @JsonbProperty("profile_info")
    private Map<String, Profile> profileInfo;

    /**
     * Events just before the result.
     */
    @Schema(
        description = "Events just before the result."
    )
    @JsonbProperty("events_before")
    private List<Event> eventsBefore;

    /**
     * Events just after the result.
     */
    @Schema(
        description = "Events just after the result."
    )
    @JsonbProperty("events_after")
    private List<Event> eventsAfter;

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

    @JsonProperty("profile_info")
    public Map<String, Profile> getProfileInfo() {
        return profileInfo;
    }

    public void setProfileInfo(Map<String, Profile> profileInfo) {
        this.profileInfo = profileInfo;
    }

    @JsonProperty("events_before")
    public List<Event> getEventsBefore() {
        return eventsBefore;
    }

    public void setEventsBefore(List<Event> eventsBefore) {
        this.eventsBefore = eventsBefore;
    }

    @JsonProperty("events_after")
    public List<Event> getEventsAfter() {
        return eventsAfter;
    }

    public void setEventsAfter(List<Event> eventsAfter) {
        this.eventsAfter = eventsAfter;
    }
}
