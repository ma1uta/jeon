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

package io.github.ma1uta.matrix.client.model.push;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ma1uta.matrix.client.model.Event;

import java.util.List;

/**
 * Notification.
 */
public class Notification {

    /**
     * Required. The action(s) to perform when the conditions for this rule are met. See Push Rules: API.
     */
    private List<Object> actions;

    /**
     * Required. The Event object for the event that triggered the notification.
     */
    private Event event;

    /**
     * The profile tag of the rule that matched this event.
     */
    @JsonProperty("profile_tag")
    private String profileTag;

    /**
     * Required. Indicates whether the user has sent a read receipt indicating that they have read this message.
     */
    private Boolean read;

    /**
     * Required. The ID of the room in which the event was posted.
     */
    @JsonProperty("room_id")
    private String roomId;

    /**
     * Required. The unix timestamp at which the event notification was sent, in milliseconds.
     */
    private Long ts;

    public List<Object> getActions() {
        return actions;
    }

    public void setActions(List<Object> actions) {
        this.actions = actions;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getProfileTag() {
        return profileTag;
    }

    public void setProfileTag(String profileTag) {
        this.profileTag = profileTag;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public Long getTs() {
        return ts;
    }

    public void setTs(Long ts) {
        this.ts = ts;
    }
}
