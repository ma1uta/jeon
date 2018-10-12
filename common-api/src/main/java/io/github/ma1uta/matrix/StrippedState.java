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

import io.github.ma1uta.matrix.event.content.EventContent;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;

/**
 * Provides information on a subset of state events such as the room name.
 */
@Schema(
    description = "Provides information on a subset of state events such as the room name."
)
public class StrippedState {

    /**
     * Required. The content for the event.
     */
    @Schema(
        description = "The content for the event.",
        required = true
    )
    private EventContent content;

    /**
     * Required. The state_key for the event.
     */
    @Schema(
        name = "state_key",
        description = "The state_key for the event.",
        required = true
    )
    @JsonbProperty("state_key")
    private String stateKey;

    /**
     * Required. The type for the event.
     */
    @Schema(
        description = "The type for the event.",
        required = true
    )
    private String type;

    /**
     * Required. The sender for the event.
     */
    @Schema(
        description = "The sender for the event.",
        required = true
    )
    private String sender;

    public EventContent getContent() {
        return content;
    }

    public void setContent(EventContent content) {
        this.content = content;
    }

    public String getStateKey() {
        return stateKey;
    }

    public void setStateKey(String stateKey) {
        this.stateKey = stateKey;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
