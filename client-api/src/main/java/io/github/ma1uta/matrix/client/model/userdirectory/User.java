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

package io.github.ma1uta.matrix.client.model.userdirectory;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;

/**
 * JSON body response for user directory api (User).
 */
@Schema(
    description = "JSON body response for user directory api (User)."
)
public class User {

    /**
     * Required. The user's matrix user ID.
     */
    @Schema(
        description = "The user's matrix user ID."
    )
    @JsonbProperty("user_id")
    private String userId;

    /**
     * The display name of the user, if one exists.
     */
    @Schema(
        description = "The display name of the user, if one exists."
    )
    @JsonbProperty("display_name")
    private String displayName;

    /**
     * The avatar url, as an MXC, if one exists.
     */
    @Schema(
        description = "The avatar url, as an MXC, if one exists."
    )
    @JsonbProperty("avatar_url")
    private String avatarUrl;

    @JsonProperty("user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @JsonProperty("display_name")
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @JsonProperty("avatar_url")
    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
