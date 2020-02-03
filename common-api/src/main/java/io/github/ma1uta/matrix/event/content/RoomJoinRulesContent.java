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
 * A room may be ``public`` meaning anyone can join the room without any prior action. Alternatively, it can be ``invite`` meaning
 * that a user who wishes to join the room must first receive an invite to the room from someone already inside of the room.
 * Currently, ``knock`` and ``private`` are reserved keywords which are not implemented.
 */
@Schema(
    description = "A room may be ``public`` meaning anyone can join the room without any prior action."
        + " Alternatively, it can be ``invite`` meaning that a user who wishes to join the room must first receive"
        + " an invite to the room from someone already inside of the room. Currently, ``knock`` and ``private`` are"
        + " reserved keywords which are not implemented."
)
public class RoomJoinRulesContent implements EventContent {

    /**
     * The type of rules used for users wishing to join this room. One of: ["public", "knock", "invite", "private"]
     */
    @Schema(
        name = "join_rule",
        description = "The type of rules used for users wishing to join this room.",
        allowableValues = {"public", "knock", "invite", "private"}
    )
    @JsonbProperty("join_rule")
    private String joinRule;

    @JsonProperty("join_rule")
    public String getJoinRule() {
        return joinRule;
    }

    public void setJoinRule(String joinRule) {
        this.joinRule = joinRule;
    }
}
