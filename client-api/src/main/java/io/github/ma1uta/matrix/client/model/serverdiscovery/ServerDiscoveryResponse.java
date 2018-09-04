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

package io.github.ma1uta.matrix.client.model.serverdiscovery;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Server discovery information.
 */
@ApiModel(description = "Server discovery information.")
public class ServerDiscoveryResponse {

    /**
     * Required. Information about the homeserver to connect to.
     */
    @ApiModelProperty(
        name = "m.homeserver",
        value = "INformation about the homeserver to connect to.",
        required = true
    )
    @JsonProperty("m.homeserver")
    private HomeserverInfo homeserver;

    /**
     * Optional. Information about the identity server to connect to.
     */
    @ApiModelProperty(
        name = "m.identity_server",
        value = "Information about the identity server to connect to."
    )
    @JsonProperty("m.identity_server")
    private IdentityServerInfo dentityServer;

    public HomeserverInfo getHomeserver() {
        return homeserver;
    }

    public void setHomeserver(HomeserverInfo homeserver) {
        this.homeserver = homeserver;
    }

    public IdentityServerInfo getDentityServer() {
        return dentityServer;
    }

    public void setDentityServer(IdentityServerInfo dentityServer) {
        this.dentityServer = dentityServer;
    }
}
