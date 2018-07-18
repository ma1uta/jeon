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

package io.github.ma1uta.matrix.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ma1uta.matrix.EventContent;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Informs the client of a user's presence state change.
 */
@ApiModel(description = "Informs the client of a user's presence state change.")
public class Presence implements EventContent {

    /**
     * The current avatar URL for this user, if any.
     */
    @ApiModelProperty(name = "avatar_url", value = "The current avatar URL for this user, if any.")
    @JsonProperty("avatar_url")
    private String avatarUrl;

    /**
     * The current display name for this user, if any.
     */
    @ApiModelProperty("The current display name for this user, if any.")
    private String displayname;

    /**
     * The last time since this used performed some action, in milliseconds.
     */
    @ApiModelProperty(name = "last_active_ago", value = "The last time since this used performed some action, in milliseconds.")
    @JsonProperty("last_active_ago")
    private Long lastActiveAgo;

    /**
     * Required. The presence state for this user. One of: ["online", "offline", "unavailable"].
     */
    @ApiModelProperty(value = "The presence state for this user.", required = true, allowableValues = "online, offline, unavailable")
    private String presence;

    /**
     * Whether the user is currently active.
     */
    @ApiModelProperty(name = "currently_active", value = "Whether the user is currently active.")
    @JsonProperty("currently_active")
    private Boolean currentlyActive;

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public Long getLastActiveAgo() {
        return lastActiveAgo;
    }

    public void setLastActiveAgo(Long lastActiveAgo) {
        this.lastActiveAgo = lastActiveAgo;
    }

    public String getPresence() {
        return presence;
    }

    public void setPresence(String presence) {
        this.presence = presence;
    }

    public Boolean getCurrentlyActive() {
        return currentlyActive;
    }

    public void setCurrentlyActive(Boolean currentlyActive) {
        this.currentlyActive = currentlyActive;
    }
}
