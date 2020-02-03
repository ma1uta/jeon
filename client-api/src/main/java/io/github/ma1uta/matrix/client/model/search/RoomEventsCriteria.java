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

import java.util.List;
import javax.json.bind.annotation.JsonbProperty;

/**
 * Room events.
 */
@Schema(
    description = "Room events."
)
public class RoomEventsCriteria {

    /**
     * The keys to search. Possible values.
     */
    public static class Key {

        protected Key() {
        }

        /**
         * Body.
         */
        public static final String CONTENT_BODY = "content.body";

        /**
         * Name.
         */
        public static final String CONTENT_NAME = "content.name";

        /**
         * Topic.
         */
        public static final String CONTENT_TOPIC = "content.topic";
    }

    /**
     * Order.
     */
    public static class Order {

        protected Order() {
        }

        /**
         * Recent.
         */
        public static final String RECENT = "recent";

        /**
         * Rank.
         */
        public static final String rank = "rank";
    }

    /**
     * Required. The string to search events for.
     */
    @Schema(
        description = "The string to search events for."
    )
    @JsonbProperty("search_term")
    private String searchTerm;

    /**
     * The keys to search. Defaults to all. One of: ["content.body", "content.name", "content.topic"]
     */
    @Schema(
        description = "The keys to search."
    )
    private List<String> keys;

    /**
     * This takes a filter.
     */
    @Schema(
        description = "This takes a filter."
    )
    private Filter filter;

    /**
     * The order in which to search for results. One of: ["recent", "rank"]
     */
    @Schema(
        description = "The order in which to search for results."
    )
    @JsonbProperty("order_by")
    private String orderBy;

    /**
     * Configures whether any context for the events returned are included in the response.
     */
    @Schema(
        description = "Configures whether any context for the events returned are included in the response."
    )
    @JsonbProperty("event_context")
    private EventContext eventContext;

    /**
     * Requests the server return the current state for each room returned.
     */
    @Schema(
        description = "Requests the server return the current state for each room returned."
    )
    @JsonbProperty("include_state")
    private Boolean includeState;

    /**
     * Requests that the server partitions the result set based on the provided list of keys.
     */
    @Schema(
        description = "Requests that the server partitions the result set based on the provided list of keys."
    )
    private Groupings groupings;

    @JsonProperty("search_term")
    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public List<String> getKeys() {
        return keys;
    }

    public void setKeys(List<String> keys) {
        this.keys = keys;
    }

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    @JsonProperty("order_by")
    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    @JsonProperty("event_context")
    public EventContext getEventContext() {
        return eventContext;
    }

    public void setEventContext(EventContext eventContext) {
        this.eventContext = eventContext;
    }

    @JsonProperty("include_state")
    public Boolean getIncludeState() {
        return includeState;
    }

    public void setIncludeState(Boolean includeState) {
        this.includeState = includeState;
    }

    public Groupings getGroupings() {
        return groupings;
    }

    public void setGroupings(Groupings groupings) {
        this.groupings = groupings;
    }
}
