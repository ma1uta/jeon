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

package io.github.ma1uta.matrix.client.model.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Event context.
 */
@ApiModel(description = "Event context.")
public class EventContext {

    /**
     * How many events before the result are returned.
     */
    @ApiModelProperty(
        name = "before_limit",
        value = "How many events before the result are returned."
    )
    @JsonProperty("before_limit")
    private Long beforeLimit;

    /**
     * How many events after the result are returned.
     */
    @ApiModelProperty(
        name = "after_limit",
        value = "How many events after the result are returned."
    )
    @JsonProperty("after_limit")
    private Long afterLimit;

    /**
     * Requests that the server returns the historic profile information for the users that sent the events that were returned.
     */
    @ApiModelProperty(
        name = "include_profile",
        value = "Requests that the server returns the historic profile information for the users that sent the events that were returned."
    )
    @JsonProperty("include_profile")
    private Boolean includeProfile;

    public Long getBeforeLimit() {
        return beforeLimit;
    }

    public void setBeforeLimit(Long beforeLimit) {
        this.beforeLimit = beforeLimit;
    }

    public Long getAfterLimit() {
        return afterLimit;
    }

    public void setAfterLimit(Long afterLimit) {
        this.afterLimit = afterLimit;
    }

    public Boolean getIncludeProfile() {
        return includeProfile;
    }

    public void setIncludeProfile(Boolean includeProfile) {
        this.includeProfile = includeProfile;
    }
}
