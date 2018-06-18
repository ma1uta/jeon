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

package io.github.ma1uta.matrix.client.model.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Room member.
 */
@ApiModel(description = "Room member.")
public class RoomMember {

    /**
     * The display name of the user this object is representing.
     */
    @ApiModelProperty(name = "display_name", value = "The display name of the user this object is representing.")
    @JsonProperty("display_name")
    private String displayName;

    /**
     * The mxc avatar url of the user this object is representing.
     */
    @ApiModelProperty(name = "avatar_url", value = "The mxc avatar url of the user this object is representing.")
    @JsonProperty("avatar_url")
    private String avatarUrl;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
