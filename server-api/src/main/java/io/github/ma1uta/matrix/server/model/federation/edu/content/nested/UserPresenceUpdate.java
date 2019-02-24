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

package io.github.ma1uta.matrix.server.model.federation.edu.content.nested;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ma1uta.matrix.Id;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;

/**
 * User presence update.
 */
@Schema(
    description = "User presence update."
)
public class UserPresenceUpdate {

    /**
     * Presence statuses.
     */
    public static class Presence {
        protected Presence() {
            //singleton
        }

        /**
         * Offline.
         */
        public static final String OFFLINE = "offline";

        /**
         * Unavailable.
         */
        public static final String UNAVAILABLE = "unavailable";

        /**
         * Online.
         */
        public static final String ONLINE = "online";
    }

    /**
     * Required. The user ID this presence EDU is for.
     */
    @Schema(
        name = "user_id",
        description = "The user ID this presence EDU is for.",
        required = true
    )
    @JsonbProperty("user_id")
    private Id userId;

    /**
     * Required. The presence of the user. One of: ["offline", "unavailable", "online"]
     */
    @Schema(
        description = "The presence of the user.",
        required = true,
        allowableValues = {"offline", "unavailable", "online"}
    )
    private String presence;

    /**
     * An optional description to accompany the presence.
     */
    @Schema(
        name = "status_msg",
        description = "An optional description to accompany the presence."
    )
    @JsonbProperty("status_msg")
    private String statusMsg;

    /**
     * Required. The number of milliseconds that have elapsed since the user last did something.
     */
    @Schema(
        name = "last_active_ago",
        description = "The number of milliseconds that have elapsed since the user last did something.",
        required = true
    )
    @JsonbProperty("last_active_ago")
    private Long lastActiveAgo;

    /**
     * True if the user is likely to be interacting with their client. This may be indicated by the user having a last_active_ago
     * within the last few minutes. Defaults to false.
     */
    @Schema(
        name = "currently_active",
        description = "True if the user is likely to be interacting with their client. This may be indicated by the user having"
            + " a last_active_ago within the last few minutes.",
        defaultValue = "false"
    )
    @JsonbProperty("currently_active")
    private Boolean currentlyActive;

    @JsonProperty("user_id")
    public Id getUserId() {
        return userId;
    }

    public void setUserId(Id userId) {
        this.userId = userId;
    }

    public String getPresence() {
        return presence;
    }

    public void setPresence(String presence) {
        this.presence = presence;
    }

    @JsonProperty("status_msg")
    public String getStatusMsg() {
        return statusMsg;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }

    @JsonProperty("last_active_ago")
    public Long getLastActiveAgo() {
        return lastActiveAgo;
    }

    public void setLastActiveAgo(Long lastActiveAgo) {
        this.lastActiveAgo = lastActiveAgo;
    }

    @JsonProperty("currently_active")
    public Boolean getCurrentlyActive() {
        return currentlyActive;
    }

    public void setCurrentlyActive(Boolean currentlyActive) {
        this.currentlyActive = currentlyActive;
    }
}
