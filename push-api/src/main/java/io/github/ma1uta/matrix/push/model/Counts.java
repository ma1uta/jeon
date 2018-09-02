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

package io.github.ma1uta.matrix.push.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Counts.
 */
@ApiModel(description = "Counts.")
public class Counts {

    /**
     * The number of unread messages a user has across all of the rooms they are a member of.
     */
    @ApiModelProperty(
        value = "The number of unread messages a user has across all of the rooms they are a member of."
    )
    private Long unread;

    /**
     * The number of unacknowledged missed calls a user has across all rooms of which they are a member.
     */
    @ApiModelProperty(
        name = "missed_calls",
        value = "The number of unacknowledged missed calls a user has across all rooms of which they are a member."
    )
    @JsonProperty("missed_calls")
    private Long missedCalls;

    public Long getUnread() {
        return unread;
    }

    public void setUnread(Long unread) {
        this.unread = unread;
    }

    public Long getMissedCalls() {
        return missedCalls;
    }

    public void setMissedCalls(Long missedCalls) {
        this.missedCalls = missedCalls;
    }
}
