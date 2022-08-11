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
 * Bind 3pid information.
 */
@Schema(
    description = "Bind 3pid information"
)
public class Bind3PidRequest {

    /**
     * The client secret used in the session with the identity server.
     */
    @Schema(
        name = "client_secret",
        description = "The client secret used in the session with the identity server.",
        required = true
    )
    @JsonbProperty("client_secret")
    private String clientSecret;

    /**
     * An access token previously registered with the identity server.
     */
    @Schema(
        name = "id_access_token",
        description = "An access token previously registered with the identity server.",
        required = true
    )
    @JsonbProperty("id_access_token")
    private String idAccessToken;

    /**
     * The identity server to use.
     */
    @Schema(
        name = "id_server",
        description = "The identity server to use.",
        required = true
    )
    @JsonbProperty("id_server")
    private String idServer;

    /**
     * The session identifier given by the identity server.
     */
    @Schema(
        description = "The session identifier given by the identity server.",
        required = true
    )
    private String sid;

    @JsonProperty("client_secret")
    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    @JsonProperty("id_access_token")
    public String getIdAccessToken() {
        return idAccessToken;
    }

    public void setIdAccessToken(String idAccessToken) {
        this.idAccessToken = idAccessToken;
    }

    @JsonProperty("id_server")
    public String getIdServer() {
        return idServer;
    }

    public void setIdServer(String idServer) {
        this.idServer = idServer;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
}
