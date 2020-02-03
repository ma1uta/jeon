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

package io.github.ma1uta.matrix.client.model.userdirectory;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;

/**
 * JSON body request for user directory api (search).
 */
@Schema(
    description = "JSON body request for user directory api (search)."
)
public class SearchRequest {

    /**
     * Required. The term to search for.
     */
    @Schema(
        description = "The term to search for."
    )
    @JsonbProperty("search_term")
    private String searchTerm;

    /**
     * The maximum number of results to return (Defaults to 10).
     */
    @Schema(
        description = "The maximum number of results to return (Defaults to 10)."
    )
    private Long limit;

    @JsonProperty("search_term")
    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public Long getLimit() {
        return limit;
    }

    public void setLimit(Long limit) {
        this.limit = limit;
    }
}
