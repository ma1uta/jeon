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
 * Request for register for an account on this homeserver.
 */
@Schema(
    description = "Request for register for an account on this homeserver."
)
public class RegisterRequest {

    /**
     * Additional authentication information for the user-interactive authentication API. Note that this information is not used
     * to define how the registered user should be authenticated, but is instead used to authenticate the register call itself.
     * It should be left empty, or omitted, unless an earlier call returned an response with status code 401.
     */
    @Schema(
        description = "Additional authentication information for the user-interactive authentication API."
            + " Note that this information is not used to define how the registered user should be authenticated, but"
            + " is instead used to authenticate the register call itself. It should be left empty, or omitted, unless"
            + " an earlier call returned an response with status code 401."
    )
    private AuthenticationData auth;

    /**
     * If true, the server binds the email used for authentication to the Matrix ID with the ID Server.
     */
    @Schema(
        description = "If true, the server binds the email used for authentication to the Matrix ID with the ID Server."
    )
    @JsonbProperty("bind_email")
    private Boolean bindEmail;

    /**
     * If true, the server binds the phone number used for authentication to the Matrix ID with the identity server.
     */
    @Schema(
        description = "If true, the server binds the phone number used for authentication to the Matrix ID with the identity server."
    )
    @JsonbProperty("bind_msisdn")
    private Boolean bindMsisdn;

    /**
     * The basis for the localpart of the desired Matrix ID. If omitted, the homeserver MUST generate a Matrix ID local part.
     */
    @Schema(
        description = "The basis for the localpart of the desired Matrix ID. If omitted, the homeserver MUST generate a"
            + " Matrix ID local part."
    )
    private String username;

    /**
     * The desired password for the account.
     */
    @Schema(
        description = "The desired password for the account."
    )
    private char[] password;

    /**
     * ID of the client device. If this does not correspond to a known client device, a new device will be created.
     * The server will auto-generate a device_id if this is not specified.
     */
    @Schema(
        description = "ID of the client device. If this does not correspond to a known client device, a new device will be created."
            + " The server will auto-generate a device_id if this is not specified."
    )
    @JsonbProperty("device_id")
    private String deviceId;

    /**
     * A display name to assign to the newly-created device. Ignored if device_id corresponds to a known device.
     */
    @Schema(
        description = "A display name to assign to the newly-created device. Ignored if device_id corresponds to a known device."
    )
    @JsonbProperty("initial_device_display_name")
    private String initialDeviceDisplayName;

    /**
     * If true, an access_token and device_id should not be returned from this call, therefore preventing an automatic login.
     * Defaults to false.
     */
    @Schema(
        description = "If true, an access_token and device_id should not be returned from this call, therefore preventing"
            + " an automatic login."
    )
    @JsonbProperty("inhibit_login")
    private Boolean inhibitLogin;

    public AuthenticationData getAuth() {
        return auth;
    }

    public void setAuth(AuthenticationData auth) {
        this.auth = auth;
    }

    @JsonProperty("bind_email")
    public Boolean getBindEmail() {
        return bindEmail;
    }

    public void setBindEmail(Boolean bindEmail) {
        this.bindEmail = bindEmail;
    }

    @JsonProperty("bind_msisdn")
    public Boolean getBindMsisdn() {
        return bindMsisdn;
    }

    public void setBindMsisdn(Boolean bindMsisdn) {
        this.bindMsisdn = bindMsisdn;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    @JsonProperty("device_id")
    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @JsonProperty("initial_device_display_name")
    public String getInitialDeviceDisplayName() {
        return initialDeviceDisplayName;
    }

    public void setInitialDeviceDisplayName(String initialDeviceDisplayName) {
        this.initialDeviceDisplayName = initialDeviceDisplayName;
    }

    @JsonProperty("inhibit_login")
    public Boolean getInhibitLogin() {
        return inhibitLogin;
    }

    public void setInhibitLogin(Boolean inhibitLogin) {
        this.inhibitLogin = inhibitLogin;
    }
}
