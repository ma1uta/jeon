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

package io.github.ma1uta.matrix.push.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

/**
 * JSON body request for push api.
 */
public class Notification {

    /**
     * Notification's priority.
     */
    public static class Priority {

        private Priority() {
        }

        /**
         * High.
         */
        public static final String HIGH = "high";

        /**
         * Low.
         */
        public static final String LOW = "low";
    }

    /**
     * The Matrix event ID of the event being notified about. This is required if the notification is about a particular Matrix event.
     * It may be omitted for notifications that only contain updated badge counts. This ID can and should be used to detect duplicate
     * notification requests.
     */
    @JsonProperty("event_id")
    private String eventId;

    /**
     * The ID of the room in which this event occurred. Required if the notification relates to a specific Matrix event.
     */
    @JsonProperty("room_id")
    private String roomId;

    /**
     * The type of the event as in the event's type field. Required if the notification relates to a specific Matrix event.
     */
    private String type;

    /**
     * The sender of the event as in the corresponding event field. Required if the notification relates to a specific Matrix event.
     */
    private String sender;

    /**
     * The current display name of the sender in the room in which the event occurred.
     */
    @JsonProperty("sender_display_name")
    private String senderDisplayName;

    /**
     * The name of the room in which the event occurred.
     */
    @JsonProperty("room_name")
    private String roomName;

    /**
     * An alias to display for the room in which the event occurred.
     */
    @JsonProperty("room_alias")
    private String roomAlias;

    /**
     * This is true if the user receiving the notification is the subject of a member event (i.e. the state_key of the member event
     * is equal to the user's Matrix ID).
     */
    @JsonProperty("user_is_target")
    private Boolean userIsTarget;

    /**
     * The priority of the notification. If omitted, high is assumed. This may be used by push gateways to deliver less time-sensitive
     * notifications in a way that will preserve battery power on mobile devices. One of: ["high", "low"].
     */
    private String prio;

    /**
     * The content field from the event, if present. If the event had no content field, this field is omitted.
     */
    private Map<String, Object> content;

    /**
     * This is a dictionary of the current number of unacknowledged communications for the recipient user. Counts whose value is zero
     * are omitted.
     */
    private Counts counts;

    /**
     * Required. This is an array of devices that the notification should be sent to.
     */
    private List<Device> devices;

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSenderDisplayName() {
        return senderDisplayName;
    }

    public void setSenderDisplayName(String senderDisplayName) {
        this.senderDisplayName = senderDisplayName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomAlias() {
        return roomAlias;
    }

    public void setRoomAlias(String roomAlias) {
        this.roomAlias = roomAlias;
    }

    public Boolean getUserIsTarget() {
        return userIsTarget;
    }

    public void setUserIsTarget(Boolean userIsTarget) {
        this.userIsTarget = userIsTarget;
    }

    public String getPrio() {
        return prio;
    }

    public void setPrio(String prio) {
        this.prio = prio;
    }

    public Map<String, Object> getContent() {
        return content;
    }

    public void setContent(Map<String, Object> content) {
        this.content = content;
    }

    public Counts getCounts() {
        return counts;
    }

    public void setCounts(Counts counts) {
        this.counts = counts;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }
}
