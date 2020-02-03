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
 * Request for deactivate the user's account, removing all ability for the user to login again.
 */
@Schema(
    description = "Request for deactivate the user's account, removing all ability for the user to login again."
)
public class DeactivateRequest {

    /**
     * Additional authentication information for the user-interactive authentication API.
     */
    @Schema(
        description = "Additional authentication information for the user-interactive authentication API."
    )
    private AuthenticationData auth;

    /**
     * The identity server to unbind all of the user's 3PIDs from. If not provided, the homeserver MUST use the id_server that was
     * originally use to bind each identifier. If the homeserver does not know which id_server that was, it must return
     * an id_server_unbind_result of no-support.
     */
    @Schema(
        description = "tThe identity server to unbind all of the user's 3PIDs from. If not provided, the homeserver MUST use"
            + " the id_server that was originally use to bind each identifier. If the homeserver does not know which id_server"
            + " that was, it must return an id_server_unbind_result of no-support."
    )
    @JsonbProperty("id_server")
    private String idServer;

    public AuthenticationData getAuth() {
        return auth;
    }

    public void setAuth(AuthenticationData auth) {
        this.auth = auth;
    }

    @JsonProperty("id_server")
    public String getIdServer() {
        return idServer;
    }

    public void setIdServer(String idServer) {
        this.idServer = idServer;
    }
}
