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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.github.ma1uta.matrix.jackson.StrippedDeserializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Provides information on a subset of state events such as the room name.
 */
@ApiModel(description = "Provides information on a subset of state events such as the room name.")
@JsonDeserialize(using = StrippedDeserializer.class)
public class StrippedState {

    /**
     * Required. The content for the event.
     */
    @ApiModelProperty(value = "The content for the event.", required = true)
    private EventContent content;

    /**
     * Required. The state_key for the event.
     */
    @ApiModelProperty(name = "state_key", value = "The state_key for the event.", required = true)
    @JsonProperty("state_key")
    private String stateKey;

    /**
     * Required. The type for the event.
     */
    @ApiModelProperty(value = "The type for the event.", required = true)
    private String type;

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
}
