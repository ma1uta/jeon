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

package io.github.ma1uta.matrix;

import io.github.ma1uta.matrix.event.Event;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * Pagination.
 *
 * @param <T> pagination item.
 */
@Schema(
    description = "Pagination."
)
public class Page<T> {

    /**
     * Query parameters.
     */
    public static class Query {

        protected Query() {
        }

        /**
         * The opaque token to start streaming from.
         */
        public static final String FROM = "from";

        /**
         * The opaque token to end streaming at. Typically, clients will not know the item of data to end at, so this will usually
         * be omitted.
         */
        public static final String TO = "to";

        /**
         * An integer representing the maximum number of items to return.
         */
        public static final String LIMIT = "limit";

        /**
         * The direction to return events in. Typically this is b to paginate backwards in time.
         */
        public static final String DIR = "dir";
    }

    /**
     * The token the pagination starts from. If dir=b this will be the token supplied in from.
     */
    @Schema(
        description = "The token the pagination starts from. If dir=b this will be the token supplied in from."
    )
    private String start;

    /**
     * The token the pagination ends at. If dir=b this token should be used again to request even earlier events.
     */
    @Schema(
        description = "The token the pagination ends at. If dir=b this token should be used again to request even earlier events."
    )
    private String end;

    /**
     * A list of room events.
     */
    @Schema(
        description = "A list of room events."
    )
    private List<T> chunk;

    /**
     * A list of state events relevant to showing the chunk.
     * For example, if lazy_load_members is enabled in the filter then this may contain the membership events for the senders
     * of events in the chunk.
     * <p/>
     * Unless include_redundant_members is true, the server may remove membership events which would have already been sent
     * to the client in prior calls to this endpoint, assuming the membership of those members has not changed.
     */
    @Schema(
        description = "A list of state events relevant to showing the chunk."
            + " For example, if lazy_load_members is enabled in the filter then this may contain the membership events for the senders"
            + " of events in the chunk. Unless include_redundant_members is true, the server may remove membership events which would"
            + " have already been sent to the client in prior calls to this endpoint, assuming the membership of those members"
            + " has not changed."
    )
    private List<Event> state;

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public List<T> getChunk() {
        return chunk;
    }

    public void setChunk(List<T> chunk) {
        this.chunk = chunk;
    }

    public List<Event> getState() {
        return state;
    }

    public void setState(List<Event> state) {
        this.state = state;
    }
}
