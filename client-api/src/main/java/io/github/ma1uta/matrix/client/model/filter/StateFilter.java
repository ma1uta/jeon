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
import io.swagger.v3.oas.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;

/**
 * State filter.
 */
@Schema(
    description = "State filter."
)
public class StateFilter extends RoomEventFilter {

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
}
