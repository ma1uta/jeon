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
 * 3Pid credentials.
 */
@Schema(
    description = "3pid credentials"
)
public class ThreePidCred {

    /**
     * Required. The session identifier given by the identity server.
     */
    @Schema(
        description = "The session identifier given by the identity server.",
        required = true
    )
    private String sid;

    /**
     * Required. The client secret used in the session with the identity server.
     */
    @Schema(
        description = "The client secret used in the session with the identity server.",
        required = true
    )
    @JsonbProperty("client_secret")
    private String clientSecret;

    /**
     * The identity server to use.
     */
    @Schema(
        description = "The identity server to use.",
        required = true
    )
    @JsonbProperty("id_server")
    private String idServer;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    @JsonProperty("client_secret")
    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    @JsonProperty("id_server")
    public String getIdServer() {
        return idServer;
    }

    public void setIdServer(String idServer) {
        this.idServer = idServer;
    }
}
