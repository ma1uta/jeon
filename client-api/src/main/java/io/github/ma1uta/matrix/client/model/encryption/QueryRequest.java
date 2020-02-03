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

import java.util.List;
import java.util.Map;
import javax.json.bind.annotation.JsonbProperty;

/**
 * JSON body parameters for Returns the current devices and identity keys for the given users.
 */
@Schema(
    description = "JSON body parameters for Returns the current devices and identity keys for the given users."
)
public class QueryRequest {

    /**
     * The time (in milliseconds) to wait when downloading keys from remote servers. 10 seconds is the recommended default.
     */
    @Schema(
        description = "The time (in milliseconds) to wait when downloading keys from remote servers. 10 seconds is the recommended default."
    )
    private Long timeout;

    /**
     * Required. The keys to be downloaded. A map from user ID, to a list of device IDs, or to an empty list to indicate all devices
     * for the corresponding user.
     */
    @Schema(
        description = "The keys to be downloaded. A map from user ID, to a list of device IDs, "
            + "or to an empty list to indicate all devices for the corresponding user."
    )
    @JsonbProperty("device_keys")
    private Map<String, List<String>> deviceKeys;

    /**
     * If the client is fetching keys as a result of a device update received in a sync request, this should be the 'since' token
     * of that sync request, or any later sync token. This allows the server to ensure its response contains the keys advertised
     * by the notification in that sync.
     */
    @Schema(
        description = "If the client is fetching keys as a result of a device update received in a sync request, this should "
            + "be the 'since' token of that sync request, or any later sync token. This allows the server to ensure its response "
            + "contains the keys advertised by the notification in that sync."
    )
    private String token;

    public Long getTimeout() {
        return timeout;
    }

    public void setTimeout(Long timeout) {
        this.timeout = timeout;
    }

    @JsonProperty("device_keys")
    public Map<String, List<String>> getDeviceKeys() {
        return deviceKeys;
    }

    public void setDeviceKeys(Map<String, List<String>> deviceKeys) {
        this.deviceKeys = deviceKeys;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
