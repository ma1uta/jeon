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

package io.github.ma1uta.matrix;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Pagination.
 *
 * @param <T> pagination item.
 */
@ApiModel(description = "Pagination.")
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
    @ApiModelProperty(
        value = "The token the pagination starts from. If dir=b this will be the token supplied in from."
    )
    private String start;

    /**
     * The token the pagination ends at. If dir=b this token should be used again to request even earlier events.
     */
    @ApiModelProperty(
        value = "The token the pagination ends at. If dir=b this token should be used again to request even earlier events."
    )
    private String end;

    /**
     * A list of room events.
     */
    @ApiModelProperty(
        value = "A list of room events."
    )
    private List<T> chunk;

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
}
