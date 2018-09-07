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
 * Left room.
 */
@ApiModel(description = "Left room.")
public class LeftRoom {

    /**
     * The state updates for the room up to the start of the timeline.
     */
    @ApiModelProperty(
        value = "The state updates for the room up to the start of the timeline."
    )
    private State state;

    /**
     * The timeline of messages and state changes in the room up to the point when the user left.
     */
    @ApiModelProperty(
        value = "The timeline of messages and state changes in the room up to the point when the user left."
    )
    private Timeline timeline;

    /**
     * The private data that this user has attached to this room.
     */
    @ApiModelProperty(
        name = "account_data",
        value = "The private data that this user has attached to this room."
    )
    @JsonProperty("account_data")
    private AccountData accountData;

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

    public AccountData getAccountData() {
        return accountData;
    }

    public void setAccountData(AccountData accountData) {
        this.accountData = accountData;
    }
}
