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
 * Response format.
 */
@ApiModel(description = "Response format.")
public class UploadResponse {

    /**
     * Required. For each key algorithm, the number of unclaimed one-time keys of that type currently held on the server for this device.
     */
    @ApiModelProperty(name = "one_time_key_counts", value = "For each key algorithm, the number of unclaimed one-time keys of that "
        + "type currently held on the server for this device.", required = true)
    @JsonProperty("one_time_key_counts")
    private Map<String, Long> oneTimeKeyCounts;

    public Map<String, Long> getOneTimeKeyCounts() {
        return oneTimeKeyCounts;
    }

    public void setOneTimeKeyCounts(Map<String, Long> oneTimeKeyCounts) {
        this.oneTimeKeyCounts = oneTimeKeyCounts;
    }
}
