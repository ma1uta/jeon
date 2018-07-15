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
import io.github.ma1uta.matrix.Event;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.Map;

/**
 * Room events results.
 */
@ApiModel(description = "Room events results.")
public class RoomEventResults {

    /**
     * An approximate count of the total number of results found.
     */
    @ApiModelProperty("An approximate count of the total number of results found.")
    private Long count;

    /**
     * List of words which should be highlighted, useful for stemming which may change the query terms.
     */
    @ApiModelProperty("List of words which should be highlighted, useful for stemming which may change the query terms.")
    private List<String> highlights;

    /**
     * List of results in the requested order.
     */
    @ApiModelProperty("List of results in the requested order.")
    private List<Result> results;

    /**
     * The current state for every room in the results. This is included if the request had the include_state key set with a value of true.
     * <p/>
     * The string key is the room ID for which the State Event array belongs to.
     */
    @ApiModelProperty("The current state for every room in the results. This is included if the request had the include_state "
        + "key set with a value of true.")
    private Map<String, List<Event>> state;

    /**
     * Any groups that were requested.
     * <p/>
     * The outer string key is the group key requested (eg: room_id or sender). The inner string key is the grouped value
     * (eg: a room's ID or a user's ID).
     */
    @ApiModelProperty("Any groups that were requested.")
    private Map<String, Map<String, GroupValue>> groups;

    /**
     * Token that can be used to get the next batch of results, by passing as the next_batch parameter to the next call. If this field
     * is absent, there are no more results.
     */
    @ApiModelProperty(name = "next_batch", value = "Token that can be used to get the next batch of results, by passing as the "
        + "next_batch parameter to the next call. If this field is absent, there are no more results.")
    @JsonProperty("next_batch")
    private String nextBatch;

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public List<String> getHighlights() {
        return highlights;
    }

    public void setHighlights(List<String> highlights) {
        this.highlights = highlights;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public Map<String, List<Event>> getState() {
        return state;
    }

    public void setState(Map<String, List<Event>> state) {
        this.state = state;
    }

    public Map<String, Map<String, GroupValue>> getGroups() {
        return groups;
    }

    public void setGroups(
        Map<String, Map<String, GroupValue>> groups) {
        this.groups = groups;
    }

    public String getNextBatch() {
        return nextBatch;
    }

    public void setNextBatch(String nextBatch) {
        this.nextBatch = nextBatch;
    }
}
