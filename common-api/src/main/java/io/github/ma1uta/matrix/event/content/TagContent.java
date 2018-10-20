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

package io.github.ma1uta.matrix.event.content;

import static java.util.stream.Collectors.toMap;

import io.github.ma1uta.matrix.event.nested.TagInfo;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

/**
 * Informs the client of tags on a room.
 */
@Schema(
    description = "Informs the client of tags on a room."
)
public class TagContent implements EventContent {

    /**
     * The tags on the room and their contents.
     */
    @Schema(
        description = "The tags on the room and their contents."
    )
    private Map<String, TagInfo> tags;

    public TagContent() {
    }

    @SuppressWarnings("unchecked")
    public TagContent(Map props) {
        this.tags = (Map<String, TagInfo>) props.entrySet().parallelStream().collect(toMap(
            entry -> (String) ((Map.Entry) entry).getKey(),
            entry -> new TagInfo((Map) ((Map.Entry) entry).getValue())
        ));
    }

    public Map<String, TagInfo> getTags() {
        return tags;
    }

    public void setTags(Map<String, TagInfo> tags) {
        this.tags = tags;
    }
}
