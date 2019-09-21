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

package io.github.ma1uta.matrix.identity.model.lookup;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

/**
 * JSON body response.
 */
@Schema(
    description = "Look up response the Matrix user ID for a 3pid."
)
public class LookupResponse {

    /**
     * Required. Any applicable mappings of addresses to Matrix User IDs. Addresses which do not have associations will not be included,
     * which can make this property be an empty object.
     */
    @Schema(
        description = "Any applicable mappings of addresses to Matrix User IDs. "
            + " Addresses which do not have associations will not be included, which can make this property be an empty object.",
        required = true
    )
    private Map<String, String> mappings;

    public Map<String, String> getMappings() {
        return mappings;
    }

    public void setMappings(Map<String, String> mappings) {
        this.mappings = mappings;
    }
}
