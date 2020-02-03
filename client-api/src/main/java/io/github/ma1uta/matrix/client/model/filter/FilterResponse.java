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

package io.github.ma1uta.matrix.client.model.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;

/**
 * JSON body response.
 */
@Schema(
    description = "JSON body response."
)
public class FilterResponse {

    /**
     * Required. The ID of the filter that was created. Cannot start with a { as this character is used to determine if the filter
     * provided is inline JSON or a previously declared filter by homeservers on some APIs.
     */
    @Schema(
        description = "The ID of the filter that was created. Cannot start with a { as this character is used to determine if the filter"
            + " provided is inline JSON or a previously declared filter by homeservers on some APIs."
    )
    @JsonbProperty("filter_id")
    private String filterId;

    @JsonProperty("filter_id")
    public String getFilterId() {
        return filterId;
    }

    public void setFilterId(String filterId) {
        this.filterId = filterId;
    }
}
