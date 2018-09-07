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

package io.github.ma1uta.matrix.client.model.sync;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Joined room.
 */
@ApiModel(description = "Joined room.")
public class JoinedRoom {

    /**
     * Updates to the state, between the time indicated by the since parameter, and the start of the timeline (or all state up to the
     * start of the timeline, if since is not given, or full_state is true).
     */
    @ApiModelProperty(
        value = "Updates to the state, between the time indicated by the since parameter, and the start of the "
            + "timeline (or all state up to the start of the timeline, if since is not given, or full_state is true)."
    )
    private State state;

    /**
     * The timeline of messages and state changes in the room.
     */
    @ApiModelProperty(
        value = "The timeline of messages and state changes in the room."
    )
    private Timeline timeline;

    /**
     * The ephemeral events in the room that aren't recorded in the timeline or state of the room. e.g. typing.
     */
    @ApiModelProperty(
        value = "The ephemeral events in the room that aren't recorded in the timeline or state of the room. e.g. typing."
    )
    private Ephemeral ephemeral;

    /**
     * The private data that this user has attached to this room.
     */
    @ApiModelProperty(
        name = "account_data",
        value = "The private data that this user has attached to this room."
    )
    @JsonProperty("account_data")
    private AccountData accountData;

    /**
     * Counts of unread notifications for this room.
     */
    @ApiModelProperty(
        name = "unread_notifications",
        value = "Counts of unread notifications for this room."
    )
    @JsonProperty("unread_notifications")
    private UnreadNotificationCounts unreadNotifications;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Timeline getTimeline() {
        return timeline;
    }

    public void setTimeline(Timeline timeline) {
        this.timeline = timeline;
    }

    public Ephemeral getEphemeral() {
        return ephemeral;
    }

    public void setEphemeral(Ephemeral ephemeral) {
        this.ephemeral = ephemeral;
    }

    public AccountData getAccountData() {
        return accountData;
    }

    public void setAccountData(AccountData accountData) {
        this.accountData = accountData;
    }

    public UnreadNotificationCounts getUnreadNotifications() {
        return unreadNotifications;
    }

    public void setUnreadNotifications(UnreadNotificationCounts unreadNotifications) {
        this.unreadNotifications = unreadNotifications;
    }
}
