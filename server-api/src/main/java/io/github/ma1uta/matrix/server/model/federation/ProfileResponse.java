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

package io.github.ma1uta.matrix.server.model.federation;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;

/**
 * Profile.
 */
@Schema(
    description = "Profile."
)
public class ProfileResponse {

    /**
     * The avatar URL for the user's avatar. May be omitted if the user does not have an avatar set.
     */
    @Schema(
        description = "The avatar URL for the user's avatar. May be omitted if the user does not have an avatar set."
    )
    @JsonbProperty("avatar_url")
    private String avatarUrl;

    /**
     * The display name of the user. May be omitted if the user does not have a display name set.
     */
    @Schema(
        description = "The display name of the user. May be omitted if the user does not have a display name set."
    )
    @JsonbProperty("displayname")
    private String displayName;

    @JsonProperty("avatar_url")
    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @JsonProperty("display_name")
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
