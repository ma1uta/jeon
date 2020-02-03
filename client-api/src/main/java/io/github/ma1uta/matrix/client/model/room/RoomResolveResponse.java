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

package io.github.ma1uta.matrix.client.model.room;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * JSON body response of the room response api.
 */
@Schema(
    description = "JSON body response of the room response api."
)
public class RoomResolveResponse extends RoomId {

    /**
     * A list of servers that are aware of this room alias.
     */
    @Schema(
        description = "A list of servers that are aware of this room alias."
    )
    private List<String> servers;

    public List<String> getServers() {
        return servers;
    }

    public void setServers(List<String> servers) {
        this.servers = servers;
    }
}
