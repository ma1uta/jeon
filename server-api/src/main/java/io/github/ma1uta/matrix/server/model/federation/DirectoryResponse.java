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

package io.github.ma1uta.matrix.server.model.federation;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import javax.json.bind.annotation.JsonbProperty;

/**
 * Directory response.
 */
@Schema(
    description = "Directory response."
)
public class DirectoryResponse {

    /**
     * Required. The room ID mapped to the queried room alias.
     */
    @Schema(
        name = "room_id",
        description = "The room ID mapped to the queried room alias.",
        required = true
    )
    @JsonbProperty("room_id")
    private String roomId;

    /**
     * Required. An array of server names that are likely to hold the given room. This list may or may not include the server answering
     * the query.
     */
    @Schema(
        description = "An array of server names that are likely to hold the given room. This list may or may not include the server"
            + " answering the query.",
        required = true
    )
    private List<String> servers;

    @JsonProperty("room_id")
    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public List<String> getServers() {
        return servers;
    }

    public void setServers(List<String> servers) {
        this.servers = servers;
    }
}
