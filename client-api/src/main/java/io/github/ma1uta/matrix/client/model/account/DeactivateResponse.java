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

package io.github.ma1uta.matrix.client.model.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;

/**
 * Deactivate response.
 */
@Schema(
    description = "Deactivate response."
)
public class DeactivateResponse {

    /**
     * Id server unbind results.
     */
    public static class UnbindResult {

        protected UnbindResult() {
            // singleton
        }

        /**
         * Success.
         */
        public static final String SUCCESS = "success";

        /**
         * No support.
         */
        public static final String NO_SUPPORT = "no-support";
    }

    /**
     * Required. An indicator as to whether or not the homeserver was able to unbind the user's 3PIDs from the identity server(s).
     * Success indicates that all identifiers have been unbound from the identity server while no-support indicates that one or more
     * identifiers failed to unbind due to the identity server refusing the request or the homeserver being unable to determine
     * an identity server to unbind from. This must be success if the homeserver has no identifiers to unbind for the user.
     * One of: ["success", "no-support"]
     */
    @Schema(
        description = "An indicator as to whether or not the homeserver was able to unbind the user's 3PIDs from the identity server(s)."
            + " success indicates that all identifiers have been unbound from the identity server while no-support indicates that"
            + " one or more identifiers failed to unbind due to the identity server refusing the request or the homeserver being"
            + " unable to determine an identity server to unbind from. This must be success if the homeserver has no identifiers"
            + " to unbind for the user.",
        required = true,
        allowableValues = {"success", "no-support"}
    )
    @JsonbProperty("id_server_unbind_result")
    private String idServerUnbindResult;

    @JsonProperty("id_server_unding_result")
    public String getIdServerUnbindResult() {
        return idServerUnbindResult;
    }

    public void setIdServerUnbindResult(String idServerUnbindResult) {
        this.idServerUnbindResult = idServerUnbindResult;
    }
}
