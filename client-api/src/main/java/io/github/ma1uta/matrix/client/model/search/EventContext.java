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

package io.github.ma1uta.matrix.client.model.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;

/**
 * Event context.
 */
@Schema(
    description = "Event context."
)
public class EventContext {

    /**
     * How many events before the result are returned.
     */
    @Schema(
        description = "How many events before the result are returned."
    )
    @JsonbProperty("before_limit")
    private Long beforeLimit;

    /**
     * How many events after the result are returned.
     */
    @Schema(
        description = "How many events after the result are returned."
    )
    @JsonbProperty("after_limit")
    private Long afterLimit;

    /**
     * Requests that the server returns the historic profile information for the users that sent the events that were returned.
     */
    @Schema(
        description = "Requests that the server returns the historic profile information for the users that sent the events that were"
            + " returned."
    )
    @JsonbProperty("include_profile")
    private Boolean includeProfile;

    @JsonProperty("before_limit")
    public Long getBeforeLimit() {
        return beforeLimit;
    }

    public void setBeforeLimit(Long beforeLimit) {
        this.beforeLimit = beforeLimit;
    }

    @JsonProperty("after_limit")
    public Long getAfterLimit() {
        return afterLimit;
    }

    public void setAfterLimit(Long afterLimit) {
        this.afterLimit = afterLimit;
    }

    @JsonProperty("include_profile")
    public Boolean getIncludeProfile() {
        return includeProfile;
    }

    public void setIncludeProfile(Boolean includeProfile) {
        this.includeProfile = includeProfile;
    }
}
