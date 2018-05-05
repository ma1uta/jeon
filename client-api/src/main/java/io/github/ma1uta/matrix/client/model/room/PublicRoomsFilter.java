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

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Search filter.
 */
public class PublicRoomsFilter {

    /**
     * A string to search for in the room metadata, e.g. name, topic, canonical alias etc. (Optional).
     */
    @JsonProperty("generic_search_term")
    private String genericSearchTerm;

    public String getGenericSearchTerm() {
        return genericSearchTerm;
    }

    public void setGenericSearchTerm(String genericSearchTerm) {
        this.genericSearchTerm = genericSearchTerm;
    }
}
