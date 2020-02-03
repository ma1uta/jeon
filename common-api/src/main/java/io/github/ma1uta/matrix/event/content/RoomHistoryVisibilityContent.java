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
 * This event controls whether a user can see the events that happened in a room from before they joined.
 */
@Schema(
    description = "This event controls whether a user can see the events that happened in a room from before they joined."
)
public class RoomHistoryVisibilityContent implements EventContent {

    /**
     * World readable.
     */
    public static final String WORLD_READABLE = "world_readable";

    /**
     * Shared.
     */
    public static final String SHARED = "shared";

    /**
     * Invited.
     */
    public static final String INVITED = "invited";

    /**
     * Joined.
     */
    public static final String JOINED = "joined";

    /**
     * Required. Who can see the room history. One of: ["invited", "joined", "shared", "world_readable"].
     */
    @Schema(
        name = "history_visibility",
        description = "Who can see the room history.",
        required = true,
        allowableValues = "invited, joined, shared, world_readable"
    )
    @JsonbProperty("history_visibility")
    private String historyVisibility;

    @JsonProperty("history_visibility")
    public String getHistoryVisibility() {
        return historyVisibility;
    }

    public void setHistoryVisibility(String historyVisibility) {
        this.historyVisibility = historyVisibility;
    }
}
