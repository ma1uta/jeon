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

import java.util.Map;

/**
 * Left room.
 */
public class LeftRoom {

    /**
     * The state updates for the room up to the start of the timeline.
     */
    private State state;

    /**
     * The timeline of messages and state changes in the room up to the point when the user left.
     */
    private Timeline timeline;

    /**
     * !!! Not described in spec.
     */
    @JsonProperty("account_data")
    private Map<String, Object> accountData;

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

    public Map<String, Object> getAccountData() {
        return accountData;
    }

    public void setAccountData(Map<String, Object> accountData) {
        this.accountData = accountData;
    }
}
