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
import io.swagger.v3.oas.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;

/**
 * Make join response.
 */
@Schema(
    description = "Make join response."
)
public class MakeJoinResponse {

    /**
     * The version of the room where the server is trying to join. If not provided, the room version is assumed to be either "1" or "2".
     */
    @Schema(
        name = "room_version",
        description = "The version of the room where the server is trying to join. If not provided, the room version is assumed"
            + " to be either \"1\" or \"2\"."
    )
    @JsonbProperty("room_version")
    private String roomVersion;

    /**
     * An unsigned template event. Note that events have a different format depending on the room version - check the room version
     * specification for precise event formats.
     */
    @Schema(
        description = "An unsigned template event. Note that events have a different format depending on the room version - check"
            + " the room version specification for precise event formats."
    )
    private EventTemplate event;

    @JsonProperty("room_version")
    public String getRoomVersion() {
        return roomVersion;
    }

    public void setRoomVersion(String roomVersion) {
        this.roomVersion = roomVersion;
    }

    public EventTemplate getEvent() {
        return event;
    }

    public void setEvent(EventTemplate event) {
        this.event = event;
    }
}
