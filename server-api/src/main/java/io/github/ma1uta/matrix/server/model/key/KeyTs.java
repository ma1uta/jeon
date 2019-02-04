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

package io.github.ma1uta.matrix.server.model.key;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;

/**
 * Server key and its valid timestamp.
 */
@Schema(
    description = "Server key and its valid timestamp."
)
public class KeyTs {

    /**
     * Key valid untils this timestamp.
     */
    @Schema(
        name = "minimum_valid_until_ts",
        description = "Server key and its valid timestamp."
    )
    @JsonbProperty("minimum_valid_until_ts")
    private Long minimumValidUntilTs;

    @JsonProperty("minimum_valid_until_ts")
    public Long getMinimumValidUntilTs() {
        return minimumValidUntilTs;
    }

    public void setMinimumValidUntilTs(Long minimumValidUntilTs) {
        this.minimumValidUntilTs = minimumValidUntilTs;
    }
}
