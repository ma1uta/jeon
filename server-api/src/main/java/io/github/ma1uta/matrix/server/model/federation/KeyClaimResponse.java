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
 * Key claim response.
 */
@Schema(
    description = "Key claim response."
)
public class KeyClaimResponse {

    /**
     * Required. One-time keys for the queried devices. A map from user ID, to a map from devices to a map from
     * &lt;algorithm&gt;:&lt;key_id&gt; to the key object.
     */
    @Schema(
        name = "one_time_keys",
        description = "One-time keys for the queried devices. A map from user ID, to a map from devices to a map from <algorithm>:<key_id>"
            + " to the key object.",
        required = true
    )
    @JsonbProperty("one_time_keys")
    private Map<String, Map<String, Object>> oneTimeKeys;

    @JsonProperty("one_time_keys")
    public Map<String, Map<String, Object>> getOneTimeKeys() {
        return oneTimeKeys;
    }

    public void setOneTimeKeys(Map<String, Map<String, Object>> oneTimeKeys) {
        this.oneTimeKeys = oneTimeKeys;
    }
}
