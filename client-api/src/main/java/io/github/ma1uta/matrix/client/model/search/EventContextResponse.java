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

package io.github.ma1uta.matrix.client.model.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ma1uta.matrix.client.model.Event;
import io.github.ma1uta.matrix.client.model.profile.Profile;

import java.util.List;
import java.util.Map;

/**
 * Event context.
 */
public class EventContextResponse {

    /**
     * Pagination token for the start of the chunk.
     */
    private String start;

    /**
     * Pagination token for the end of the chunk.
     */
    private String end;

    /**
     * The historic profile information of the users that sent the events returned.
     */
    @JsonProperty("profile_info")
    private Map<String, Profile> profileInfo;

    /**
     * Events just before the result.
     */
    @JsonProperty("events_before")
    private List<Event> eventsBefore;

    /**
     * Events just after the result.
     */
    @JsonProperty("events_after")
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

    public Map<String, Profile> getProfileInfo() {
        return profileInfo;
    }

    public void setProfileInfo(Map<String, Profile> profileInfo) {
        this.profileInfo = profileInfo;
    }

    public List<Event> getEventsBefore() {
        return eventsBefore;
    }

    public void setEventsBefore(List<Event> eventsBefore) {
        this.eventsBefore = eventsBefore;
    }

    public List<Event> getEventsAfter() {
        return eventsAfter;
    }

    public void setEventsAfter(List<Event> eventsAfter) {
        this.eventsAfter = eventsAfter;
    }
}
