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

package io.github.ma1uta.matrix.client.model.serverdiscovery;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;

/**
 * Server discovery information.
 */
@Schema(
    description = "Server discovery information."
)
public class ServerDiscoveryResponse {

    /**
     * Required. Information about the homeserver to connect to.
     */
    @Schema(
        description = "Information about the homeserver to connect to."
    )
    @JsonbProperty("m.homeserver")
    private HomeserverInfo homeserver;

    /**
     * Optional. Information about the identity server to connect to.
     */
    @Schema(
        description = "Information about the identity server to connect to."
    )
    @JsonbProperty("m.identity_server")
    private IdentityServerInfo identityServer;

    @JsonProperty("m.homeserver")
    public HomeserverInfo getHomeserver() {
        return homeserver;
    }

    public void setHomeserver(HomeserverInfo homeserver) {
        this.homeserver = homeserver;
    }

    @JsonProperty("m.identity_server")
    public IdentityServerInfo getIdentityServer() {
        return identityServer;
    }

    public void setIdentityServer(IdentityServerInfo identityServer) {
        this.identityServer = identityServer;
    }
}
