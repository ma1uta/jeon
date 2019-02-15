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
import java.util.Map;
import javax.json.bind.annotation.JsonbProperty;

/**
 * Query auth request.
 */
@Schema(
    description = "Query auth."
)
public class QueryAuth {

    /**
     * Required. The auth chain (the "remote auth"). Note that events have a different format depending on the room version - check
     * the room version specification for precise event formats.
     */
    @Schema(
        name = "auth_chain",
        description = "The auth chain (the \"remote auth\"). Note that events have a different format depending on the room version - check"
            + " the room version specification for precise event formats.",
        required = true
    )
    @JsonbProperty("auth_chain")
    private List<PersistedDataUnit> authChain;

    /**
     * A list of event IDs that the sender thinks the receiver is missing.
     */
    @Schema(
        description = "A list of event IDs that the sender thinks the receiver is missing."
    )
    private List<String> missing;

    /**
     * The set of events that the sending server has rejected from the provided auth chain.
     * <br>
     * The string key is the event ID that was rejected.
     */
    @Schema(
        description = "The set of events that the sending server has rejected from the provided auth chain."
    )
    private Map<String, RejectReason> rejects;

    @JsonProperty("auth_chain")
    public List<PersistedDataUnit> getAuthChain() {
        return authChain;
    }

    public void setAuthChain(List<PersistedDataUnit> authChain) {
        this.authChain = authChain;
    }

    public List<String> getMissing() {
        return missing;
    }

    public void setMissing(List<String> missing) {
        this.missing = missing;
    }

    public Map<String, RejectReason> getRejects() {
        return rejects;
    }

    public void setRejects(Map<String, RejectReason> rejects) {
        this.rejects = rejects;
    }
}
