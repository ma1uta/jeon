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

package io.github.ma1uta.matrix.server.model.federation;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ma1uta.matrix.Unsigned;

import java.util.List;
import java.util.Map;

/**
 * These events are broadcast from one homeserver to any others that have joined the same room (identified by Room ID).
 * They are persisted in long-term storage and record the history of messages and state for a room.
 * <p/>
 * Like email, it is the responsibility of the originating server of a PDU to deliver that event to its recipient servers.
 * However PDUs are signed using the originating server's private key so that it is possible to deliver them through third-party servers.
 */
public class PersistedDataUnit {

    /**
     * Required. Room identifier.
     */
    @JsonProperty("room_id")
    private String roomId;

    /**
     * Required. The ID of the user sending the event.
     */
    private String sender;

    /**
     * Required. server_name of the homeserver that created this event.
     */
    private String origin;

    /**
     * Required. Unique identifier for the event being sent.
     */
    @JsonProperty("event_id")
    private String eventId;

    /**
     * Required. Timestamp in milliseconds on origin homeserver when this event was created.
     */
    @JsonProperty("origin_server_ts")
    private Long originServerTs;

    /**
     * Required. Event type.
     */
    private String type;

    /**
     * Optional. If this key is present, the event is a state event, and it will replace previous events with the same type and
     * state_key in the room state.
     */
    @JsonProperty("state_key")
    private String stateKey;

    /**
     * Required. The content of the event.
     */
    private Map<String, String> content;

    /**
     * Required. Event IDs and hashes of the most recent events in the room that the homeserver was aware of when it made this event.
     */
    @JsonProperty("prev_events")
    private List<Map<String, Map<String, String>>> prevEvents;

    /**
     * Required. The maximum depth of the prev_events, plus one.
     */
    private Long depth;

    /**
     * Required. Event IDs and hashes for the "auth events" of this event.
     */
    @JsonProperty("auth_events")
    private List<Map<String, Map<String, String>>> authEvents;

    /**
     * Required. Hashes of the PDU, following the algorithm specified in Signing Events.
     */
    private Map<String, String> hashes;

    /**
     * Required. Signatures of the redacted PDU, following the algorithm specified in Signing Events.
     */
    private Map<String, Map<String, String>> signatures;

    /**
     * Optional. For redaction events, the ID of the event being redacted.
     */
    private String redacts;

    /**
     * Optional. Additional data added by the origin server but not covered by the signatures.
     */
    private Unsigned unsigned;

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public Long getOriginServerTs() {
        return originServerTs;
    }

    public void setOriginServerTs(Long originServerTs) {
        this.originServerTs = originServerTs;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStateKey() {
        return stateKey;
    }

    public void setStateKey(String stateKey) {
        this.stateKey = stateKey;
    }

    public Map<String, String> getContent() {
        return content;
    }

    public void setContent(Map<String, String> content) {
        this.content = content;
    }

    public List<Map<String, Map<String, String>>> getPrevEvents() {
        return prevEvents;
    }

    public void setPrevEvents(List<Map<String, Map<String, String>>> prevEvents) {
        this.prevEvents = prevEvents;
    }

    public Long getDepth() {
        return depth;
    }

    public void setDepth(Long depth) {
        this.depth = depth;
    }

    public List<Map<String, Map<String, String>>> getAuthEvents() {
        return authEvents;
    }

    public void setAuthEvents(List<Map<String, Map<String, String>>> authEvents) {
        this.authEvents = authEvents;
    }

    public Map<String, String> getHashes() {
        return hashes;
    }

    public void setHashes(Map<String, String> hashes) {
        this.hashes = hashes;
    }

    public Map<String, Map<String, String>> getSignatures() {
        return signatures;
    }

    public void setSignatures(Map<String, Map<String, String>> signatures) {
        this.signatures = signatures;
    }

    public String getRedacts() {
        return redacts;
    }

    public void setRedacts(String redacts) {
        this.redacts = redacts;
    }

    public Unsigned getUnsigned() {
        return unsigned;
    }

    public void setUnsigned(Unsigned unsigned) {
        this.unsigned = unsigned;
    }
}
