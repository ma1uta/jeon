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

package io.github.ma1uta.matrix.client.model.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ma1uta.matrix.client.model.serverdiscovery.ServerDiscoveryResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;

/**
 * Response for the login request.
 */
@Schema(
    description = "JSON body response for the login api."
)
public class LoginResponse {

    /**
     * The fully-qualified Matrix ID that has been registered.
     */
    @Schema(
        description = "The fully-qualified Matrix ID that has been registered."
    )
    @JsonbProperty("user_id")
    private String userId;

    /**
     * An access token for the account. This access token can then be used to authorize other requests.
     */
    @Schema(
        description = "An access token for the account. This access token can then be used to authorize other requests."
    )
    @JsonbProperty("access_token")
    private String accessToken;

    /**
     * The hostname of the homeserver on which the account has been registered.
     *
     * @deprecated clients should extract the server_name from user_id (by splitting at the first colon) if they require it.
     *      Note also that homeserver is not spelt this way.
     */
    @Schema(
        description = "The hostname of the homeserver on which the account has been registered."
    )
    @Deprecated
    @JsonbProperty("home_server")
    private String homeServer;

    /**
     * Optional client configuration provided by the server. If present, clients SHOULD use the provided object to reconfigure themselves,
     * optionally validating the URLs within. This object takes the same form as the one returned from .well-known autodiscovery.
     */
    @Schema(
        name = "well_known",
        description = "Optional client configuration provided by the server. If present, clients SHOULD use the provided object"
            + " to reconfigure themselves, optionally validating the URLs within. This object takes the same form as the one returned"
            + " from .well-known autodiscovery."
    )
    @JsonbProperty("well_know")
    private ServerDiscoveryResponse wellKnown;

    /**
     * ID of the logged-in device. Will be the same as the corresponding parameter in the request, if one was specified.
     */
    @Schema(
        description = "ID of the logged-in device. Will be the same as the corresponding parameter in the request, if one was specified."
    )
    @JsonbProperty("device_id")
    private String deviceId;

    @JsonProperty("user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @JsonProperty("access_token")
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @JsonProperty("home_server")
    public String getHomeServer() {
        return homeServer;
    }

    public void setHomeServer(String homeServer) {
        this.homeServer = homeServer;
    }

    @JsonProperty("device_id")
    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @JsonProperty("well_known")
    public ServerDiscoveryResponse getWellKnown() {
        return wellKnown;
    }

    public void setWellKnown(ServerDiscoveryResponse wellKnown) {
        this.wellKnown = wellKnown;
    }
}
