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

package io.github.ma1uta.matrix.client.model.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import javax.json.bind.annotation.JsonbProperty;

/**
 * Room filter.
 */
@Schema(
    description = "Room filter."
)
public class RoomFilter {

    /**
     * A list of room IDs to exclude. If this list is absent then no rooms are excluded. A matching room will be excluded even if it
     * is listed in the 'rooms' filter. This filter is applied before the filters in ephemeral, state, timeline or account_data.
     */
    @Schema(
        description = "A list of room IDs to exclude. If this list is absent then no rooms are excluded. "
            + "A matching room will be excluded even if it is listed in the 'rooms' filter. This filter is applied before the filters in "
            + "ephemeral, state, timeline or account_data."
    )
    @JsonbProperty("not_rooms")
    private List<String> notRooms;

    /**
     * A list of room IDs to include. If this list is absent then all rooms are included. This filter is applied before the filters
     * in ephemeral, state, timeline or account_data.
     */
    @Schema(
        description = "A list of room IDs to include. If this list is absent then all rooms are included. This filter is applied "
            + "before the filters in ephemeral, state, timeline or account_data."
    )
    private List<String> rooms;

    /**
     * The events that aren't recorded in the room history, e.g. typing and receipts, to include for rooms.
     */
    @Schema(
        description = "The events that aren't recorded in the room history, e.g. typing and receipts, to include for rooms."
    )
    private RoomEventFilter ephemeral;

    /**
     * Include rooms that the user has left in the sync, default false.
     */
    @Schema(
        description = "Include rooms that the user has left in the sync, default false."
    )
    @JsonbProperty("include_leave")
    private Boolean includeLeave;

    /**
     * The state events to include for rooms.
     */
    @Schema(
        description = "The state events to include for rooms."
    )
    private RoomEventFilter state;

    /**
     * The message and state update events to include for rooms.
     */
    @Schema(
        description = "The message and state update events to include for rooms."
    )
    private RoomEventFilter timeline;

    /**
     * The per user account data to include for rooms.
     */
    @Schema(
        description = "The per user account data to include for rooms."
    )
    @JsonbProperty("account_data")
    private RoomEventFilter accountData;

    public List<String> getNotRooms() {
        return notRooms;
    }

    @JsonProperty("not_rooms")
    public void setNotRooms(List<String> notRooms) {
        this.notRooms = notRooms;
    }

    public List<String> getRooms() {
        return rooms;
    }

    public void setRooms(List<String> rooms) {
        this.rooms = rooms;
    }

    public RoomEventFilter getEphemeral() {
        return ephemeral;
    }

    public void setEphemeral(RoomEventFilter ephemeral) {
        this.ephemeral = ephemeral;
    }

    @JsonProperty("include_leave")
    public Boolean getIncludeLeave() {
        return includeLeave;
    }

    public void setIncludeLeave(Boolean includeLeave) {
        this.includeLeave = includeLeave;
    }

    public RoomEventFilter getState() {
        return state;
    }

    public void setState(RoomEventFilter state) {
        this.state = state;
    }

    public RoomEventFilter getTimeline() {
        return timeline;
    }

    public void setTimeline(RoomEventFilter timeline) {
        this.timeline = timeline;
    }

    @JsonProperty("account_data")
    public RoomEventFilter getAccountData() {
        return accountData;
    }

    public void setAccountData(RoomEventFilter accountData) {
        this.accountData = accountData;
    }
}
