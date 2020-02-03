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

package io.github.ma1uta.matrix.server.model.federation.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ma1uta.matrix.event.Unsigned;
import io.github.ma1uta.matrix.event.content.EventContent;
import io.github.ma1uta.matrix.server.model.federation.PersistedDataUnit;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.Map;
import javax.json.bind.annotation.JsonbProperty;

/**
 * These events are broadcast from one homeserver to any others that have joined the same room (identified by Room ID).
 * They are persisted in long-term storage and record the history of messages and state for a room.
 * <br>
 * Like email, it is the responsibility of the originating server of a PDU to deliver that event to its recipient servers.
 * However PDUs are signed using the originating server's private key so that it is possible to deliver them through third-party servers.
 */
@Schema(
    description = "These events are broadcast from one homeserver to any others that have joined the same room (identified by Room ID)."
        + " They are persisted in long-term storage and record the history of messages and state for a room."
)
public class PDUv1 implements PersistedDataUnit {

    /**
     * Required. Room identifier.
     */
    @Schema(
        name = "room_id",
        description = "Room identifier.",
        required = true
    )
    @JsonbProperty("room_id")
    private String roomId;

    /**
     * Required. The ID of the user sending the event.
     */
    @Schema(
        description = "The ID of the user sending the event.",
        required = true
    )
    private String sender;

    /**
     * Required. server_name of the homeserver that created this event.
     */
    @Schema(
        description = "server_name of the homeserver that created this event.",
        required = true
    )
    private String origin;

    /**
     * Required. Unique identifier for the event being sent.
     */
    @Schema(
        name = "event_id",
        description = "Unique identifier for the event being sent.",
        required = true
    )
    @JsonbProperty("event_id")
    private String eventId;

    /**
     * Required. Timestamp in milliseconds on origin homeserver when this event was created.
     */
    @Schema(
        name = "origin_server_ts",
        description = "Timestamp in milliseconds on origin homeserver when this event was created.",
        required = true
    )
    @JsonbProperty("origin_server_ts")
    private Long originServerTs;

    /**
     * Required. Event type.
     */
    @Schema(
        description = "Event type.",
        required = true
    )
    private String type;

    /**
     * Optional. If this key is present, the event is a state event, and it will replace previous events with the same type and
     * state_key in the room state.
     */
    @Schema(
        name = "state_key",
        description = "If this key is present, the event is a state event, and it will replace previous events with the same type and"
            + " state_key in the room state."
    )
    @JsonbProperty("state_key")
    private String stateKey;

    /**
     * Required. The content of the event.
     */
    @Schema(
        description = "The content of the event."
    )
    private EventContent content;

    /**
     * Required. Event IDs and hashes of the most recent events in the room that the homeserver was aware of when it made this event.
     */
    @JsonbProperty("prev_events")
    private List<Map<String, Map<String, String>>> prevEvents;

    /**
     * Required. The maximum depth of the prev_events, plus one.
     */
    @Schema(
        description = "The maximum depth of the prev_events, plus one.",
        required = true
    )
    private Long depth;

    /**
     * Required. Event IDs and hashes for the "auth events" of this event.
     */
    @Schema(
        name = "auth_events",
        description = "Event IDs and hashes for the \"auth events\" of this event.",
        required = true
    )
    @JsonbProperty("auth_events")
    private List<Map<String, Map<String, String>>> authEvents;

    /**
     * Required. Hashes of the PDU, following the algorithm specified in Signing Events.
     */
    @Schema(
        description = "Hashes of the PDU, following the algorithm specified in Signing Events.",
        required = true
    )
    private Map<String, String> hashes;

    /**
     * Required. Signatures of the redacted PDU, following the algorithm specified in Signing Events.
     */
    @Schema(
        description = "Signatures of the redacted PDU, following the algorithm specified in Signing Events.",
        required = true
    )
    private Map<String, Map<String, String>> signatures;

    /**
     * Optional. For redaction events, the ID of the event being redacted.
     */
    @Schema(
        description = "For redaction events, the ID of the event being redacted."
    )
    private String redacts;

    /**
     * Optional. Additional data added by the origin server but not covered by the signatures.
     */
    @Schema(
        description = "Additional data added by the origin server but not covered by the signatures."
    )
    private Unsigned unsigned;

    @JsonProperty("room_id")
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

    @JsonProperty("event_id")
    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    @JsonProperty("origin_server_ts")
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

    @JsonProperty("state_key")
    public String getStateKey() {
        return stateKey;
    }

    public void setStateKey(String stateKey) {
        this.stateKey = stateKey;
    }

    public EventContent getContent() {
        return content;
    }

    public void setContent(EventContent content) {
        this.content = content;
    }

    @JsonProperty("prev_events")
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

    @JsonProperty("auth_events")
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
