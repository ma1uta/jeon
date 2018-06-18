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

package io.github.ma1uta.matrix.client.model.presence;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * JSON body request for presence api.
 */
@ApiModel(description = "SON body request for presence api.")
public class PresenceStatus {

    /**
     * Presence types.
     */
    public static class PresenceType {

        protected PresenceType() {
            //singleton
        }

        /**
         * Online status.
         */
        public static final String ONLINE = "online";

        /**
         * Offline status.
         */
        public static final String OFFLINE = "offline";

        /**
         * Unavailable status.
         */
        public static final String UNAVAILABLE = "unavailable";
    }

    /**
     * Required. The new presence state. One of: ["online", "offline", "unavailable"].
     */
    @ApiModelProperty(value = "The new presence state.", required = true, allowableValues = "['online', 'offline', 'unavailable']")
    private String presence;

    /**
     * The status message to attach to this state.
     */
    @ApiModelProperty(name = "status_msg", value = "The status message to attach to this state.")
    @JsonProperty("status_msg")
    private String statusMsg;

    /**
     * The length of time in milliseconds since an action was performed by this user.
     */
    @ApiModelProperty(name = "last_active_ago", value = "The length of time in milliseconds since an action was performed by this user.")
    @JsonProperty("last_active_ago")
    private Long lastActiveAgo;

    /**
     * Whether the user is currently active.
     */
    @ApiModelProperty(name = "currently_active", value = "Whether the user is currently active.")
    @JsonProperty("currently_active")
    private Boolean currentlyActive;

    public String getPresence() {
        return presence;
    }

    public void setPresence(String presence) {
        this.presence = presence;
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }

    public Long getLastActiveAgo() {
        return lastActiveAgo;
    }

    public void setLastActiveAgo(Long lastActiveAgo) {
        this.lastActiveAgo = lastActiveAgo;
    }

    public Boolean getCurrentlyActive() {
        return currentlyActive;
    }

    public void setCurrentlyActive(Boolean currentlyActive) {
        this.currentlyActive = currentlyActive;
    }
}
