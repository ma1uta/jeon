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
 * Room state response.
 */
@Schema(
    description = "Room state response."
)
public class RoomStateResponse {

    /**
     * Required. The resident server's DNS name.
     */
    @Schema(
        description = "The resident server's DNS name.",
        required = true
    )
    private String origin;

    /**
     * Required. The auth chain. Note that events have a different format depending on the room version - check the room version
     * specification for precise event formats.
     */
    @Schema(
        name = "auth_chain",
        description = "The auth chain. Note that events have a different format depending on the room version - check the room version"
            + " specification for precise event formats.",
        required = true
    )
    @JsonbProperty("auth_chain")
    private List<PersistedDataUnit> authChain;

    /**
     * Required. The room state. The event format varies depending on the room version - check the room version specification for precise
     * event formats.
     */
    @Schema(
        description = "The room state. The event format varies depending on the room version - check the room version specification"
            + " for precise event formats.",
        required = true
    )
    private List<PersistedDataUnit> state;

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @JsonProperty("auth_chain")
    public List<PersistedDataUnit> getAuthChain() {
        return authChain;
    }

    public void setAuthChain(List<PersistedDataUnit> authChain) {
        this.authChain = authChain;
    }

    public List<PersistedDataUnit> getState() {
        return state;
    }

    public void setState(List<PersistedDataUnit> state) {
        this.state = state;
    }
}
