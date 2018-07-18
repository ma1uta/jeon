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

package io.github.ma1uta.matrix.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ma1uta.matrix.EventContent;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This is the first event in a room and cannot be changed. It acts as the root of all other events.
 */
@ApiModel(description = "This is the first event in a room and cannot be changed. It acts as the root of all other events.")
public class RoomCreate implements EventContent {

    /**
     * Required. The user_id of the room creator. This is set by the homeserver.
     */
    @ApiModelProperty(value = "The user_id of the room creator. This is set by the homeserver.", required = true)
    private String creator;

    /**
     * Whether users on other servers can join this room. Defaults to ``true`` if key does not exist.
     */
    @ApiModelProperty(name = "m.federate", value = "Whether users on other servers can join this room. Defaults to ``true`` if key"
        + " does not exist.")
    @JsonProperty("m.federate")
    private Boolean federate;

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Boolean getFederate() {
        return federate;
    }

    public void setFederate(Boolean federate) {
        this.federate = federate;
    }
}
