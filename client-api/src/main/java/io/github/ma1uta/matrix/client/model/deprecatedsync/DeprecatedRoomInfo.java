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

package io.github.ma1uta.matrix.client.model.deprecatedsync;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ma1uta.matrix.Event;
import io.github.ma1uta.matrix.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.core.SecurityContext;

/**
 * Deprecated room info.
 *
 * @deprecated in favor of {@link io.github.ma1uta.matrix.client.api.SyncApi#sync(String, String, Boolean, String, Long,
 * HttpServletRequest, AsyncResponse, SecurityContext)}.
 */
@Deprecated
@ApiModel(description = "Deprecated room info.")
public class DeprecatedRoomInfo {

    /**
     * Required. The ID of this room.
     */
    @ApiModelProperty(
        notes = "room_id",
        value = "The ID of this room.",
        required = true
    )
    @JsonProperty("room_id")
    private String roomId;

    /**
     * Required. The user's membership state in this room. One of: ["invite", "join", "leave", "ban"]
     */
    @ApiModelProperty(
        value = "The user's membership state in this room.",
        required = true,
        allowableValues = "invite, join, leave, ban"
    )
    private String membership;

    /**
     * The invite event if membership is invite.
     */
    @ApiModelProperty(
        value = "The invite event if membership is invite."
    )
    private Event invite;

    /**
     * The pagination chunk for this room.
     */
    @ApiModelProperty(
        value = "The pagination chunk for this room."
    )
    private Page<Event> messages;

    /**
     * If the user is a member of the room this will be the current state of the room as a list of events.
     * If the user has left the room this will be the state of the room when they left it.
     */
    @ApiModelProperty(
        value = "If the user is a member of the room this will be the current state of the room as a list of events."
            + " If the user has left the room this will be the state of the room when they left it."
    )
    private List<Event> state;

    /**
     * Whether this room is visible to the /publicRooms API or not." One of: ["private", "public"].
     */
    @ApiModelProperty(
        value = "Whether this room is visible to the /publicRooms API or not.",
        allowableValues = "private, public"
    )
    private String visibility;

    /**
     * The private data that this user has attached to this room.
     */
    @ApiModelProperty(
        notes = "account_data",
        value = "The private data that this user has attached to this room."
    )
    @JsonProperty("account_data")
    private List<Event> accountData;

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getMembership() {
        return membership;
    }

    public void setMembership(String membership) {
        this.membership = membership;
    }

    public Event getInvite() {
        return invite;
    }

    public void setInvite(Event invite) {
        this.invite = invite;
    }

    public Page<Event> getMessages() {
        return messages;
    }

    public void setMessages(Page<Event> messages) {
        this.messages = messages;
    }

    public List<Event> getState() {
        return state;
    }

    public void setState(List<Event> state) {
        this.state = state;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public List<Event> getAccountData() {
        return accountData;
    }

    public void setAccountData(List<Event> accountData) {
        this.accountData = accountData;
    }
}
