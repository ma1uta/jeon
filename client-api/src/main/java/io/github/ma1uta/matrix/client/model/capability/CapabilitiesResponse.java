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

package io.github.ma1uta.matrix.client.model.capability;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;

/**
 * Capabilities response.
 */
@Schema(
    description = "Capabilities response."
)
public class CapabilitiesResponse {

    /**
     * Capability to indicate if the user can change their password.
     */
    @Schema(
        name = "m.change_password",
        description = "Capability to indicate if the user can change their password."
    )
    @JsonbProperty("m.change_password")
    private ChangePasswordCapability changePassword;

    /**
     * The room versions the server supports.
     */
    @Schema(
        name = "m.room_versions",
        description = "The room versions the server supports."
    )
    @JsonbProperty("m.room_versions")
    private RoomVersionsCapability roomVersions;

    @JsonProperty("m.change_password")
    public ChangePasswordCapability getChangePassword() {
        return changePassword;
    }

    public void setChangePassword(ChangePasswordCapability changePassword) {
        this.changePassword = changePassword;
    }

    @JsonProperty("m.room_versions")
    public RoomVersionsCapability getRoomVersions() {
        return roomVersions;
    }

    public void setRoomVersions(RoomVersionsCapability roomVersions) {
        this.roomVersions = roomVersions;
    }
}
