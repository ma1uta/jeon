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
 * A state event signifying that a room has been upgraded to a different room version, and that clients should go there.
 */
@Schema(
    description = "A state event signifying that a room has been upgraded to a different room version, and that clients should go there."
)
public class TombstoneContent implements EventContent {

    /**
     * Required. A server-defined message.
     */
    @Schema(
        description = "A server-defined message.",
        required = true
    )
    private String body;

    /**
     * Required. The new room the client should be visiting.
     */
    @Schema(
        name = "replacement_room",
        description = "The new room the client should be visiting.",
        required = true
    )
    @JsonbProperty("replacement_room")
    private String replacementRoom;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @JsonProperty("replacement_room")
    public String getReplacementRoom() {
        return replacementRoom;
    }

    public void setReplacementRoom(String replacementRoom) {
        this.replacementRoom = replacementRoom;
    }
}
