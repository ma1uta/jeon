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
import io.swagger.v3.oas.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;

/**
 * Authenticates the user, and issues an access token they can use to authorize themself in subsequent requests.
 */
@Schema(
    description = "Authenticates the user, and issues an access token they can use to authorize themself in subsequent requests"
)
public class LoginRequest {

    /**
     * Required. The login type being used. One of: ["m.login.password", "m.login.token"].
     */
    @Schema(
        description = "The login type being used"
    )
    private String type;

    /**
     * Identification information for the user.
     */
    @Schema(
        description = "Identification information for the user."
    )
    private Identifier identifier;

    /**
     * The fully qualified user ID or just local part of the user ID, to log in.
     * <br>
     * @deprecated in favour of {@link LoginRequest#identifier}.
     */
    @Schema(
        description = "he fully qualified user ID or just local part of the user ID, to log in."
    )
    @Deprecated
    private String user;

    /**
     * When logging in using a third party identifier, the medium of the identifier. Must be 'email'.
     * <br>
     * @deprecated in favour of {@link LoginRequest#identifier}.
     */
    @Schema(
        description = "When logging in using a third party identifier, the medium of the identifier. Must be 'email'."
    )
    @Deprecated
    private String medium;

    /**
     * Third party identifier for the user.
     * <br>
     * @deprecated in favour of {@link LoginRequest#identifier}.
     */
    @Schema(
        description = "Third party identifier for the user"
    )
    @Deprecated
    private String address;

    /**
     * Required when type is m.login.password. The user's password.
     */
    @Schema(
        description = "Required when type is m.login.password. The user's password."
    )
    private char[] password;

    /**
     * Required when type is m.login.token. The login token.
     */
    @Schema(
        description = "Required when type is m.login.token. The login token"
    )
    private String token;

    /**
     * ID of the client device. If this does not correspond to a known client device, a new device will be created.
     * The server will auto-generate a device_id if this is not specified.
     */
    @Schema(
        description = "ID of the client device. If this does not correspond to a known client device, a "
            + "new device will be created. The server will auto-generate a device_id if this is not specified"
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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
}
