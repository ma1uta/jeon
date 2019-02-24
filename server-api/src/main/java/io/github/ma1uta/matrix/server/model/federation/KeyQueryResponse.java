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

import java.util.Map;
import javax.json.bind.annotation.JsonbProperty;

/**
 * Key query response.
 */
@Schema(
    description = "Key query response."
)
public class KeyQueryResponse {

    /**
     * Required. Information on the queried devices. A map from user ID, to a map from device ID to device information.
     * For each device, the information returned will be the same as uploaded via /keys/upload, with the addition of an unsigned property.
     */
    @Schema(
        name = "device_keys",
        description = "Information on the queried devices. A map from user ID, to a map from device ID to device information."
            + " For each device, the information returned will be the same as uploaded via /keys/upload, with the addition of"
            + " an unsigned property.",
        required = true
    )
    @JsonbProperty("device_keys")
    private Map<String, Map<String, DeviceKeysWithUnsigned>> deviceKeys;

    @JsonProperty("device_keys")
    public Map<String, Map<String, DeviceKeysWithUnsigned>> getDeviceKeys() {
        return deviceKeys;
    }

    public void setDeviceKeys(
        Map<String, Map<String, DeviceKeysWithUnsigned>> deviceKeys) {
        this.deviceKeys = deviceKeys;
    }
}
