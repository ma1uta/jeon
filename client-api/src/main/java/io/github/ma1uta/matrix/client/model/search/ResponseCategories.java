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

package io.github.ma1uta.matrix.client.model.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;

/**
 * Response categories.
 */
@Schema(
    description = "Response categories."
)
public class ResponseCategories {

    /**
     * Mapping of category name to search criteria.
     */
    @Schema(
        description = "Mapping of category name to search criteria."
    )
    @JsonbProperty("room_events")
    private RoomEventResults roomEvents;

    @JsonProperty("room_events")
    public RoomEventResults getRoomEvents() {
        return roomEvents;
    }

    public void setRoomEvents(RoomEventResults roomEvents) {
        this.roomEvents = roomEvents;
    }
}
