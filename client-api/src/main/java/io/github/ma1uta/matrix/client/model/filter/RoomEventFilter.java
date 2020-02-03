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

import java.util.List;
import javax.json.bind.annotation.JsonbProperty;

/**
 * Room event filter.
 */
@Schema(
    description = "Room event filter."
)
public class RoomEventFilter {

    /**
     * The maximum number of events to return.
     */
    @Schema(
        description = "The maximum number of events to return."
    )
    private Long limit;

    /**
     * A list of sender IDs to exclude. If this list is absent then no senders are excluded. A matching sender will be excluded even
     * if it is listed in the 'senders' filter.
     */
    @Schema(
        description = "A list of sender IDs to exclude. If this list is absent then no senders are "
            + "excluded. A matching sender will be excluded even if it is listed in the 'senders' filter."
    )
    @JsonbProperty("not_senders")
    private List<String> notSenders;

    /**
     * A list of event types to exclude. If this list is absent then no event types are excluded. A matching type will be excluded even
     * if it is listed in the 'types' filter. A '*' can be used as a wildcard to match any sequence of characters.
     */
    @Schema(
        description = "A list of event types to exclude. If this list is absent then no event types "
            + "are excluded. A matching type will be excluded even if it is listed in the 'types' filter. A '*' can be used as a "
            + "wildcard to match any sequence of characters."
    )
    @JsonbProperty("not_types")
    private List<String> notTypes;

    /**
     * A list of senders IDs to include. If this list is absent then all senders are included.
     */
    @Schema(
        description = "A list of senders IDs to include. If this list is absent then all senders are included."
    )
    private List<String> senders;

    /**
     * A list of event types to include. If this list is absent then all event types are included. A '*' can be used as a wildcard to
     * match any sequence of characters.
     */
    @Schema(
        description = "A list of event types to include. If this list is absent then all event types are included. A '*' can "
            + "be used as a wildcard to match any sequence of characters."
    )
    private List<String> types;

    /**
     * If true, the only m.room.member events returned in the state section of the /sync response are those which are definitely
     * necessary for a client to display the sender of the timeline events in that response. If false, m.room.member events are
     * not filtered. By default, servers should suppress duplicate redundant lazy-loaded m.room.member events from being sent to
     * a given client across multiple calls to /sync, given that most clients cache membership events (see include_redundant_members
     * to change this behaviour).
     */
    @Schema(
        description = "If true, the only m.room.member events returned in the state section of the /sync response are those which"
            + " are definitely necessary for a client to display the sender of the timeline events in that response."
            + " If false, m.room.member events are not filtered. By default, servers should suppress duplicate redundant lazy-loaded"
            + " m.room.member events from being sent to a given client across multiple calls to /sync, given that most clients cache"
            + " membership events (see include_redundant_members to change this behaviour)."
    )
    @JsonbProperty("lazy_load_members")
    private Boolean lazyLoadMembers;

    /**
     * If true, the state section of the /sync response will always contain the m.room.member events required to display the sender
     * of the timeline events in that response, assuming lazy_load_members is enabled. This means that redundant duplicate member
     * events may be returned across multiple calls to /sync. This is useful for naive clients who never track membership data.
     * If false, duplicate m.room.member events may be suppressed by the server across multiple calls to /sync.
     * If lazy_load_members is false this field is ignored.
     */
    @Schema(
        description = "If true, the state section of the /sync response will always contain the m.room.member events required"
            + " to display the sender of the timeline events in that response, assuming lazy_load_members is enabled."
            + " This means that redundant duplicate member events may be returned across multiple calls to /sync."
            + " This is useful for naive clients who never track membership data. If false, duplicate m.room.member events"
            + " may be suppressed by the server across multiple calls to /sync. If lazy_load_members is false this field is ignored."
    )
    @JsonbProperty("include_redundant_members")
    private Boolean includeRedundantMembers;

    /**
     * A list of room IDs to exclude. If this list is absent then no rooms are excluded. A matching room will be excluded even if it is
     * listed in the 'rooms' filter.
     */
    @Schema(
        description = "A list of room IDs to exclude. If this list is absent then no rooms are excluded. "
            + "A matching room will be excluded even if it is listed in the 'rooms' filter."
    )
    @JsonbProperty("not_rooms")
    private List<String> notRooms;

    /**
     * A list of room IDs to include. If this list is absent then all rooms are included.
     */
    @Schema(
        description = "A list of room IDs to include. If this list is absent then all rooms are included."
    )
    private List<String> rooms;

    /**
     * If true, includes only events with a url key in their content. If false, excludes those events.
     */
    @Schema(
        description = "If true, includes only events with a url key in their content. If false, excludes those events."
    )
    @JsonbProperty("contains_url")
    private Boolean containsUrl;

    public Long getLimit() {
        return limit;
    }

    public void setLimit(Long limit) {
        this.limit = limit;
    }

    @JsonProperty("not_senders")
    public List<String> getNotSenders() {
        return notSenders;
    }

    public void setNotSenders(List<String> notSenders) {
        this.notSenders = notSenders;
    }

    @JsonProperty("not_types")
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

    @JsonProperty("lazy_load_members")
    public Boolean getLazyLoadMembers() {
        return lazyLoadMembers;
    }

    public void setLazyLoadMembers(Boolean lazyLoadMembers) {
        this.lazyLoadMembers = lazyLoadMembers;
    }

    @JsonProperty("include_redundant_members")
    public Boolean getIncludeRedundantMembers() {
        return includeRedundantMembers;
    }

    public void setIncludeRedundantMembers(Boolean includeRedundantMembers) {
        this.includeRedundantMembers = includeRedundantMembers;
    }

    @JsonProperty("not_rooms")
    public List<String> getNotRooms() {
        return notRooms;
    }

    public void setNotRooms(List<String> notRooms) {
        this.notRooms = notRooms;
    }

    public List<String> getRooms() {
        return rooms;
    }

    public void setRooms(List<String> rooms) {
        this.rooms = rooms;
    }

    @JsonProperty("contains_url")
    public Boolean getContainsUrl() {
        return containsUrl;
    }

    public void setContainsUrl(Boolean containsUrl) {
        this.containsUrl = containsUrl;
    }
}
