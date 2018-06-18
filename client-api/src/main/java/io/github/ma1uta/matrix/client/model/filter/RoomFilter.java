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

package io.github.ma1uta.matrix.client.model.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Room filter.
 */
@ApiModel("Room filter.")
public class RoomFilter {

    /**
     * A list of room IDs to exclude. If this list is absent then no rooms are excluded. A matching room will be excluded even if it
     * is listed in the 'rooms' filter. This filter is applied before the filters in ephemeral, state, timeline or account_data.
     */
    @ApiModelProperty(name = "not_rooms", value = "A list of room IDs to exclude. If this list is absent then no rooms are excluded. "
        + "A matching room will be excluded even if it is listed in the 'rooms' filter. This filter is applied before the filters in "
        + "ephemeral, state, timeline or account_data.")
    @JsonProperty("not_rooms")
    private List<String> notRooms;

    /**
     * A list of room IDs to include. If this list is absent then all rooms are included. This filter is applied before the filters
     * in ephemeral, state, timeline or account_data.
     */
    @ApiModelProperty("A list of room IDs to include. If this list is absent then all rooms are included. This filter is applied "
        + "before the filters in ephemeral, state, timeline or account_data.")
    private List<String> rooms;

    /**
     * The events that aren't recorded in the room history, e.g. typing and receipts, to include for rooms.
     */
    @ApiModelProperty("The events that aren't recorded in the room history, e.g. typing and receipts, to include for rooms.")
    private RoomEventFilter ephemeral;

    /**
     * Include rooms that the user has left in the sync, default false.
     */
    @ApiModelProperty(name = "include_leave", value = "Include rooms that the user has left in the sync, default false.")
    @JsonProperty("include_leave")
    private Boolean includeLeave;

    /**
     * The state events to include for rooms.
     */
    @ApiModelProperty("The state events to include for rooms.")
    private RoomEventFilter state;

    /**
     * The message and state update events to include for rooms.
     */
    @ApiModelProperty("The message and state update events to include for rooms.")
    private RoomEventFilter timeline;

    /**
     * The per user account data to include for rooms.
     */
    @ApiModelProperty(name = "account_data", value = "The per user account data to include for rooms.")
    @JsonProperty("account_data")
    private RoomEventFilter accountData;

    public List<String> getNotRooms() {
        return notRooms;
    }

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

    public RoomEventFilter getAccountData() {
        return accountData;
    }

    public void setAccountData(RoomEventFilter accountData) {
        this.accountData = accountData;
    }
}
