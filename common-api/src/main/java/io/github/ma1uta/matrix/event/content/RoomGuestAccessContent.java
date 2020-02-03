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
import io.swagger.v3.oas.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;

/**
 * This event controls whether guest users are allowed to join rooms. If this event is absent, servers should act as if it is present
 * and has the guest_access value "forbidden".
 */
@Schema(
    description = "This event controls whether guest users are allowed to join rooms. If this event is absent,"
        + " servers should act as if it is present and has the guest_access value \"forbidden\"."
)
public class RoomGuestAccessContent implements EventContent {

    /**
     * Can join.
     */
    public static final String CAN_JOIN = "can_join";

    /**
     * Forbidden.
     */
    public static final String FORBIDDEN = "forbidden";

    /**
     * Whether guests can join the room. One of: ["can_join", "forbidden"].
     */
    @Schema(
        name = "guest_access",
        description = "Whether guests can join the room.",
        allowableValues = "can_join, forbidden"
    )
    @JsonbProperty("guest_access")
    private String guestAccess;

    @JsonProperty("guest_access")
    public String getGuestAccess() {
        return guestAccess;
    }

    public void setGuestAccess(String guestAccess) {
        this.guestAccess = guestAccess;
    }
}
