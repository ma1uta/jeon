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

package io.github.ma1uta.matrix.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ma1uta.matrix.Id;
import io.github.ma1uta.matrix.event.content.EventContent;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;

/**
 * Room Event.
 *
 * @param <C> type of the event content.
 */
@Schema(
    description = "Room Event."
)
public abstract class RoomEvent<C extends EventContent> extends Event<C> {

    /**
     * Required. The globally unique event identifier.
     */
    @Schema(
        name = "event_id",
        description = "The globally unique event identifier.",
        required = true
    )
    @JsonbProperty("event_id")
    private Id eventId;

    /**
     * Required. The ID of the room associated with this event.
     */
    @Schema(
        name = "room_id",
        description = "The ID of the room associated with this event.",
        required = true
    )
    @JsonbProperty("room_id")
    private Id roomId;

    /**
     * Required. Contains the fully-qualified ID of the user who sent this event.
     */
    @Schema(
        description = "Contains the fully-qualified ID of the user who sent this event.",
        required = true
    )
    private Id sender;

    /**
     * Required. Timestamp in milliseconds on originating homeserver when this event was sent.
     */
    @Schema(
        name = "origin_server_ts",
        description = "Timestamp in milliseconds on originating homeserver when this event was sent.",
        required = true
    )
    @JsonbProperty("origin_server_ts")
    private Long originServerTs;

    /**
     * Contains optional extra information about the event.
     */
    @Schema(
        description = "Contains optional extra information about the event."
    )
    private Unsigned<C> unsigned;

    @JsonProperty("event_id")
    public Id getEventId() {
        return eventId;
    }

    public void setEventId(Id eventId) {
        this.eventId = eventId;
    }

    @JsonProperty("room_id")
    public Id getRoomId() {
        return roomId;
    }

    public void setRoomId(Id roomId) {
        this.roomId = roomId;
    }

    public Id getSender() {
        return sender;
    }

    public void setSender(Id sender) {
        this.sender = sender;
    }

    public Unsigned<C> getUnsigned() {
        return unsigned;
    }

    public void setUnsigned(Unsigned<C> unsigned) {
        this.unsigned = unsigned;
    }

    @JsonProperty("origin_server_ts")
    public Long getOriginServerTs() {
        return originServerTs;
    }

    public void setOriginServerTs(Long originServerTs) {
        this.originServerTs = originServerTs;
    }
}
