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

package io.github.ma1uta.matrix.client.model.room;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;

/**
 * Kick JSON body request.
 */
@Schema(
    description = "Kick JSON body request."
)
public class KickRequest {

    /**
     * Required. The fully qualified user ID of the user being kicked.
     */
    @Schema(
        description = "The fully qualified user ID of the user being kicked."
    )
    @JsonbProperty("user_id")
    private String userId;

    /**
     * The reason the user has been kicked. This will be supplied as the ``reason`` on the target's updated `m.room.member`_ event.
     */
    @Schema(
        description = "The reason the user has been kicked."
    )
    private String reason;

    @JsonProperty("user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
