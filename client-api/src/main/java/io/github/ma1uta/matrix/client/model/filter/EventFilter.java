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

package io.github.ma1uta.matrix.client.model.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Filter.
 */
@ApiModel(description = "Filter.")
public class EventFilter {

    /**
     * The maximum number of events to return.
     */
    @ApiModelProperty(
        value = "The maximum number of events to return."
    )
    private Long limit;

    /**
     * A list of sender IDs to exclude. If this list is absent then no senders are excluded. A matching sender will be excluded even
     * if it is listed in the 'senders' filter.
     */
    @ApiModelProperty(
        name = "not_senders",
        value = "A list of sender IDs to exclude. If this list is absent then no senders are "
            + "excluded. A matching sender will be excluded even if it is listed in the 'senders' filter."
    )
    @JsonProperty("not_senders")
    private List<String> notSenders;

    /**
     * A list of event types to exclude. If this list is absent then no event types are excluded. A matching type will be excluded
     * even if it is listed in the 'types' filter. A '*' can be used as a wildcard to match any sequence of characters.
     */
    @ApiModelProperty(
        name = "not_types",
        value = "A list of event types to exclude. If this list is absent then no event types "
            + "are excluded. A matching type will be excluded even if it is listed in the 'types' filter. A '*' can be used as a "
            + "wildcard to match any sequence of characters."
    )
    @JsonProperty("not_types")
    private List<String> notTypes;

    /**
     * A list of senders IDs to include. If this list is absent then all senders are included.
     */
    @ApiModelProperty(
        value = "A list of senders IDs to include. If this list is absent then all senders are included."
    )
    private List<String> senders;

    /**
     * A list of event types to include. If this list is absent then all event types are included. A '*' can be used as a wildcard to
     * match any sequence of characters.
     */
    @ApiModelProperty(
        value = "A list of event types to include. If this list is absent then all event types are included. A '*' can "
            + "be used as a wildcard to match any sequence of characters."
    )
    private List<String> types;

    public Long getLimit() {
        return limit;
    }

    public void setLimit(Long limit) {
        this.limit = limit;
    }

    public List<String> getNotSenders() {
        return notSenders;
    }

    public void setNotSenders(List<String> notSenders) {
        this.notSenders = notSenders;
    }

    public List<String> getNotTypes() {
        return notTypes;
    }

    public void setNotTypes(List<String> notTypes) {
        this.notTypes = notTypes;
    }

    public List<String> getSenders() {
        return senders;
    }

    public void setSenders(List<String> senders) {
        this.senders = senders;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }
}