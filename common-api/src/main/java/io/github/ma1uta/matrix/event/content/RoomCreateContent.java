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

package io.github.ma1uta.matrix.event.content;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ma1uta.matrix.event.nested.PreviousRoom;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;

/**
 * This is the first event in a room and cannot be changed. It acts as the root of all other events.
 */
@Schema(
    description = "This is the first event in a room and cannot be changed. It acts as the root of all other events."
)
public class RoomCreateContent implements EventContent {

    /**
     * Required. The user_id of the room creator. This is set by the homeserver.
     */
    @Schema(
        description = "The user_id of the room creator. This is set by the homeserver.",
        required = true
    )
    private String creator;

    /**
     * Whether users on other servers can join this room. Defaults to ``true`` if key does not exist.
     */
    @Schema(
        name = "m.federate",
        description = "Whether users on other servers can join this room. Defaults to ``true`` if key does not exist."
    )
    @JsonbProperty("m.federate")
    private Boolean federate;

    /**
     * The version of the room. Defaults to "1" if the key does not exist.
     */
    @Schema(
        name = "room_version",
        description = "The version of the room. Defaults to \"1\" if the key does not exist."
    )
    @JsonbProperty("room_version")
    private String roomVersion;

    /**
     * A reference to the room this room replaces, if the previous room was upgraded.
     */
    @Schema(
        description = "A reference to the room this room replaces, if the previous room was upgraded."
    )
    private PreviousRoom predecessor;

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @JsonProperty("m.federate")
    public Boolean getFederate() {
        return federate;
    }

    public void setFederate(Boolean federate) {
        this.federate = federate;
    }

    @JsonProperty("room_version")
    public String getRoomVersion() {
        return roomVersion;
    }

    public void setRoomVersion(String roomVersion) {
        this.roomVersion = roomVersion;
    }

    public PreviousRoom getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(PreviousRoom predecessor) {
        this.predecessor = predecessor;
    }
}
