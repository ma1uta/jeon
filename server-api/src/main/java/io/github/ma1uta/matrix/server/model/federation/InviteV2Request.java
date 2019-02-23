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
import io.github.ma1uta.matrix.event.Event;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;

/**
 * V2 Invite JSON request.
 */
@Schema(
    description = "V2 Invite JSON request."
)
public class InviteV2Request {

    /**
     * Required. The version of the room where the user is being invited to.
     */
    @Schema(
        name = "room_version",
        description = "The version of the room where the user is being invited to.",
        required = true
    )
    @JsonbProperty("room_version")
    private String roomVersion;

    /**
     * Required. An invite event. Note that events have a different format depending on the room version - check the room version
     * specification for precise event formats.
     */
    @Schema(
        description = "An invite event. Note that events have a different format depending on the room version - check the room version"
            + " specification for precise event formats.",
        required = true
    )
    private InviteEvent event;

    /**
     * An optional list of simplified events to help the receiver of the invite identify the room. The recommended events to include
     * are the join rules, canonical alias, avatar, and name of the room.
     */
    @Schema(
        name = "invite_room_state",
        description = "An optional list of simplified events to help the receiver of the invite identify the room."
            + " The recommended events to include are the join rules, canonical alias, avatar, and name of the room."
    )
    @JsonbProperty("invite_room_state")
    private Event inviteRoomState;

    @JsonProperty("room_version")
    public String getRoomVersion() {
        return roomVersion;
    }

    public void setRoomVersion(String roomVersion) {
        this.roomVersion = roomVersion;
    }

    public InviteEvent getEvent() {
        return event;
    }

    public void setEvent(InviteEvent event) {
        this.event = event;
    }

    @JsonProperty("invite_room_state")
    public Event getInviteRoomState() {
        return inviteRoomState;
    }

    public void setInviteRoomState(Event inviteRoomState) {
        this.inviteRoomState = inviteRoomState;
    }
}
