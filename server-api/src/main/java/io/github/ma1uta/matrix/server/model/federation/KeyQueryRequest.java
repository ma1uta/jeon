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

import java.util.List;
import java.util.Map;
import javax.json.bind.annotation.JsonbProperty;

/**
 * Key query request.
 */
@Schema(
    description = "Key query request."
)
public class KeyQueryRequest {

    /**
     * Required. The keys to be downloaded. A map from user ID, to a list of device IDs, or to an empty list to indicate all devices
     * for the corresponding user.
     */
    @Schema(
        name = "device_keys",
        description = "The keys to be downloaded. A map from user ID, to a list of device IDs, or to an empty list to indicate all devices"
            + " for the corresponding user.",
        required = true
    )
    @JsonbProperty("device_keys")
    private Map<String, List<String>> deviceKeys;

    @JsonProperty("device_keys")
    public Map<String, List<String>> getDeviceKeys() {
        return deviceKeys;
    }

    public void setDeviceKeys(Map<String, List<String>> deviceKeys) {
        this.deviceKeys = deviceKeys;
    }
}
