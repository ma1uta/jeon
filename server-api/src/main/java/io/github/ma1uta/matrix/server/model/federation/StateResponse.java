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

package io.github.ma1uta.matrix.server.model.federation;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import javax.json.bind.annotation.JsonbProperty;

/**
 * Response of the state methods.
 */
@Schema(
    description = "Response of the state methods."
)
public class StateResponse {

    /**
     * Required. The full set of authorization events that make up the state of the room, and their authorization events, recursively.
     * Note that events have a different format depending on the room version - check the room version specification for precise event
     * formats.
     */
    @Schema(
        name = "auth_chain",
        description = "The full set of authorization events that make up the state of the room, and their authorization events,"
            + " recursively. Note that events have a different format depending on the room version - check the room version specification"
            + " for precise event formats.",
        required = true
    )
    @JsonbProperty("auth_chain")
    private List<PersistedDataUnit> authChain;

    /**
     * Required. The fully resolved state of the room at the given event. Note that events have a different format depending on
     * the room version - check the room version specification for precise event formats.
     */
    @Schema(
        description = "The fully resolved state of the room at the given event. Note that events have a different format depending on"
            + " the room version - check the room version specification for precise event formats.",
        required = true
    )
    private List<PersistedDataUnit> pdus;

    @JsonProperty("auth_chain")
    public List<PersistedDataUnit> getAuthChain() {
        return authChain;
    }

    public void setAuthChain(List<PersistedDataUnit> authChain) {
        this.authChain = authChain;
    }

    public List<PersistedDataUnit> getPdus() {
        return pdus;
    }

    public void setPdus(List<PersistedDataUnit> pdus) {
        this.pdus = pdus;
    }
}
