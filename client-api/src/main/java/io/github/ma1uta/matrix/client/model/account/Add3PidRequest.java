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
 * Add 3pid information.
 */
@Schema(
    description = "Add 3pid information"
)
public class Add3PidRequest {

    /**
     * Additional authentication information for the user-interactive authentication API.
     */
    @Schema(
        description = "Additional authentication information for the user-interactive authentication API."
    )
    private AuthenticationData auth;

    /**
     * The client secret used in the session with the homeserver.
     */
    @Schema(
        name = "client_secret",
        description = "The client secret used in the session with the homeserver.",
        required = true
    )
    @JsonbProperty("client_secret")
    private String clientSecret;

    /**
     * The session identifier given by the homeserver.
     */
    @Schema(
        description = "The session identifier given by the homeserver.",
        required = true
    )
    private String sid;

    public AuthenticationData getAuth() {
        return auth;
    }

    public void setAuth(AuthenticationData auth) {
        this.auth = auth;
    }

    @JsonProperty("client_secret")
    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
}
