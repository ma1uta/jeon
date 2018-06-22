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

package io.github.ma1uta.matrix.client.model.presence;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * JSON body request for presence api.
 */
@ApiModel(description = "JSON body request for presence api.")
public class PresenceList {

    /**
     * A list of user IDs to add to the list.
     */
    @ApiModelProperty("A list of user IDs to add to the list.")
    private List<String> invite;

    /**
     * A list of user IDs to remove from the list.
     */
    @ApiModelProperty("A list of user IDs to remove from the list.")
    private List<String> drop;

    public List<String> getInvite() {
        return invite;
    }

    public void setInvite(List<String> invite) {
        this.invite = invite;
    }

    public List<String> getDrop() {
        return drop;
    }

    public void setDrop(List<String> drop) {
        this.drop = drop;
    }
}
