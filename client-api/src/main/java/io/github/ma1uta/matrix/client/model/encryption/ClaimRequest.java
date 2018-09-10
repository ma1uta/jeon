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

package io.github.ma1uta.matrix.client.model.encryption;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Map;

/**
 * JSON body parameters for Claims one-time keys for use in pre-key messages.
 */
@ApiModel(description = "JSON body parameters for Claims one-time keys for use in pre-key messages.")
public class ClaimRequest {

    /**
     * The time (in milliseconds) to wait when downloading keys from remote servers. 10 seconds is the recommended default.
     */
    @ApiModelProperty(
        value = "The time (in milliseconds) to wait when downloading keys from remote servers. 10 seconds is the recommended default."
    )
    private Long timeout;

    /**
     * Required. The keys to be claimed. A map from user ID, to a map from device ID to algorithm name.
     */
    @ApiModelProperty(
        name = "one_time_keys",
        value = "The keys to be claimed. A map from user ID, to a map from device ID to algorithm name.",
        required = true
    )
    @JsonProperty("one_time_keys")
    private Map<String, Map<String, String>> oneTimeKeys;

    public Long getTimeout() {
        return timeout;
    }

    public void setTimeout(Long timeout) {
        this.timeout = timeout;
    }

    public Map<String, Map<String, String>> getOneTimeKeys() {
        return oneTimeKeys;
    }

    public void setOneTimeKeys(Map<String, Map<String, String>> oneTimeKeys) {
        this.oneTimeKeys = oneTimeKeys;
    }
}
