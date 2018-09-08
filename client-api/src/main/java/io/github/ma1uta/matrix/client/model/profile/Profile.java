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

package io.github.ma1uta.matrix.client.model.profile;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * JSON body for profile api (profile).
 */
@ApiModel(description = "JSON body for profile api (profile).")
public class Profile {

    /**
     * The user's avatar URL if they have set one, otherwise not present.
     */
    @ApiModelProperty(
        name = "avatar_url",
        value = "The user's avatar URL if they have set one, otherwise not present."
    )
    @JsonProperty("avatar_url")
    private String avatarUrl;

    /**
     * The user's display name if they have set one, otherwise not present.
     */
    @ApiModelProperty(
        name = "displayname",
        value = "The user's display name if they have set one, otherwise not present."
    )
    @JsonProperty("displayname")
    private String displayName;

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
