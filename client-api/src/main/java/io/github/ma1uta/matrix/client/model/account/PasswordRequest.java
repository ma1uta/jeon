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
 * Request for changes the password for an account on this homeserver.
 */
@Schema(
    description = "Request for changes the password for an account on this homeserver."
)
public class PasswordRequest {

    /**
     * Required. The new password for the account.
     */
    @Schema(
        description = "The new password for the account.",
        required = true
    )
    @JsonbProperty("new_password")
    private char[] newPassword;

    /**
     * Whether the other access tokens, and their associated devices, for the user should be revoked if the request succeeds.
     * Defaults to true.
     */
    @Schema(
        description = "Whether the other access tokens, and their associated devices,"
            + " for the user should be revoked if the request succeeds.",
        defaultValue = "true"
    )
    @JsonbProperty("logout_devices")
    private boolean logoutDevices = true;

    /**
     * Additional authentication information for the user-interactive authentication API.
     */
    @Schema(
        description = "Additional authentication information for the user-interactive authentication API."
    )
    private AuthenticationData auth;

    @JsonProperty("new_password")
    public char[] getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(char[] newPassword) {
        this.newPassword = newPassword;
    }

    @JsonProperty("logout_devices")
    public boolean isLogoutDevices() {
        return logoutDevices;
    }

    public void setLogoutDevices(boolean logoutDevices) {
        this.logoutDevices = logoutDevices;
    }

    public AuthenticationData getAuth() {
        return auth;
    }

    public void setAuth(AuthenticationData auth) {
        this.auth = auth;
    }
}
