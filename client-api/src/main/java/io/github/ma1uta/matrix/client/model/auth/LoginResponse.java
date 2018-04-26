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

package io.github.ma1uta.matrix.client.model.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response for the login request.
 *
 * @author ma1uta
 */
public class LoginResponse {

    /**
     * The fully-qualified Matrix ID that has been registered.
     */
    @JsonProperty("user_id")
    private String userId;

    /**
     * An access token for the account. This access token can then be used to authorize other requests.
     */
    @JsonProperty("access_token")
    private String accessToken;

    /**
     * The hostname of the homeserver on which the account has been registered.
     */
    @JsonProperty("home_server")
    private String homeServer;

    /**
     * ID of the logged-in device. Will be the same as the corresponding parameter in the request, if one was specified.
     */
    @JsonProperty("device_id")
    private String deviceId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getHomeServer() {
        return homeServer;
    }

    public void setHomeServer(String homeServer) {
        this.homeServer = homeServer;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
