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

import io.github.ma1uta.matrix.Id;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * JSON body request for presence api.
 */
@Schema(
    description = "JSON body request for presence api."
)
public class PresenceList {

    /**
     * A list of user IDs to add to the list.
     */
    @Schema(
        description = "A list of user IDs to add to the list."
    )
    private List<Id> invite;

    /**
     * A list of user IDs to remove from the list.
     */
    @Schema(
        description = "A list of user IDs to remove from the list."
    )
    private List<Id> drop;

    public List<Id> getInvite() {
        return invite;
    }

    public void setInvite(List<Id> invite) {
        this.invite = invite;
    }

    public List<Id> getDrop() {
        return drop;
    }

    public void setDrop(List<Id> drop) {
        this.drop = drop;
    }
}
