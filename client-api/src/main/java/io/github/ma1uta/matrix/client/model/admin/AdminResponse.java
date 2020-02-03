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

package io.github.ma1uta.matrix.client.model.admin;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;
import javax.json.bind.annotation.JsonbProperty;

/**
 * Response for gets information about a particular user.
 */
@Schema(
    description = "Response for gets information about a particular user."
)
public class AdminResponse {

    /**
     * The Matrix user ID of the user.
     */
    @Schema(
        description = "he Matrix user ID of the user."
    )
    @JsonbProperty("user_id")
    private String userId;

    /**
     * Each key is an identitfier for one of the user's devices.
     */
    @Schema(
        description = "Each key is an identitfier for one of the user's devices"
    )
    private Map<String, DeviceInfo> devices;

    @JsonProperty("user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Map<String, DeviceInfo> getDevices() {
        return devices;
    }

    public void setDevices(Map<String, DeviceInfo> devices) {
        this.devices = devices;
    }
}
