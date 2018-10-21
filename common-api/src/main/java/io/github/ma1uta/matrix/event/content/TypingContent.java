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

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ma1uta.matrix.support.DeserializerUtil;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import javax.json.bind.annotation.JsonbProperty;

/**
 * Informs the client of the list of users currently typing.
 */
@Schema(
    description = "Informs the client of the list of users currently typing."
)
public class TypingContent implements EventContent {

    /**
     * Required. The list of user IDs typing in this room, if any.
     */
    @Schema(
        name = "user_ids",
        description = "The list of user IDs typing in this room, if any.",
        required = true
    )
    @JsonbProperty("user_ids")
    private List<String> userIds;

    public TypingContent() {
    }

    public TypingContent(Map props) {
        this.userIds = DeserializerUtil.toList(props, "user_ids", Function.identity());
    }

    @JsonProperty("user_ids")
    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }
}
