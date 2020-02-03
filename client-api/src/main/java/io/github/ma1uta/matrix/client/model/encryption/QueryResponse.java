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

package io.github.ma1uta.matrix.client.model.encryption;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;
import javax.json.bind.annotation.JsonbProperty;

/**
 * Response format.
 */
@Schema(
    description = "Query response."
)
public class QueryResponse {

    /**
     * If any remote homeservers could not be reached, they are recorded here. The names of the properties are the names of
     * the unreachable servers.
     * <p/>
     * If the homeserver could be reached, but the user or device was unknown, no failure is recorded. Instead, the corresponding
     * user or device is missing from the device_keys result.
     */
    @Schema(
        description = "If any remote homeservers could not be reached, they are recorded here. The names of the properties are "
            + "the names of the unreachable servers."
    )
    private Map<String, Object> failures;

    /**
     * Information on the queried devices. A map from user ID, to a map from device ID to device information. For each device,
     * the information returned will be the same as uploaded via /keys/upload, with the addition of an unsigned property.
     */
    @Schema(
        description = "Information on the queried devices. A map from user ID, to a map from "
            + "device ID to device information. For each device, the information returned will be the same as uploaded via "
            + "/keys/upload, with the addition of an unsigned property."
    )
    @JsonbProperty("device_keys")
    private Map<String, Map<String, DeviceKeys>> deviceKeys;

    public Map<String, Object> getFailures() {
        return failures;
    }

    public void setFailures(Map<String, Object> failures) {
        this.failures = failures;
    }

    @JsonProperty("device_keys")
    public Map<String, Map<String, DeviceKeys>> getDeviceKeys() {
        return deviceKeys;
    }

    public void setDeviceKeys(Map<String, Map<String, DeviceKeys>> deviceKeys) {
        this.deviceKeys = deviceKeys;
    }
}
