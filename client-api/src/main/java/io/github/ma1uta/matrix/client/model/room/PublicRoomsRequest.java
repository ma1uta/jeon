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

package io.github.ma1uta.matrix.client.model.room;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * JSON body request.
 */
@ApiModel(description = "JSON body request.")
public class PublicRoomsRequest {

    /**
     * Limit the number of results returned.
     */
    @ApiModelProperty("Limit the number of results returned.")
    private Long limit;

    /**
     * A pagination token from a previous request, allowing clients to get the next (or previous) batch of rooms.
     * The direction of pagination is specified solely by which token is supplied, rather than via an explicit flag.
     */
    @ApiModelProperty("A pagination token from a previous request, allowing clients to get the next (or previous) batch of rooms. "
        + "lThe direction of pagination is specified solely by which token is supplied, rather than via an explicit flag.")
    private String since;

    /**
     * Filter to apply to the results.
     */
    @ApiModelProperty("Filter to apply to the results.")
    private PublicRoomsFilter filter;

    public Long getLimit() {
        return limit;
    }

    public void setLimit(Long limit) {
        this.limit = limit;
    }

    public String getSince() {
        return since;
    }

    public void setSince(String since) {
        this.since = since;
    }

    public PublicRoomsFilter getFilter() {
        return filter;
    }

    public void setFilter(PublicRoomsFilter filter) {
        this.filter = filter;
    }
}
