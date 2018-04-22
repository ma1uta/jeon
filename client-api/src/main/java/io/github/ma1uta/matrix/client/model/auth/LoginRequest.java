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

/**
 * Authenticates the user, and issues an access token they can use to authorize themself in subsequent requests.
 *
 * @author ma1uta
 */
public class LoginRequest {

    /**
     * Required. The login type being used. One of: ["m.login.password", "m.login.token"].
     */
    private String type;

    /**
     * The fully qualified user ID or just local part of the user ID, to log in.
     */
    private String user;

    /**
     * When logging in using a third party identifier, the medium of the identifier. Must be 'email'.
     */
    private String medium;

    /**
     * Third party identifier for the user.
     */
    private String address;

    /**
     * Required when type is m.login.password. The user's password.
     */
    private CharSequence password;

    /**
     * Required when type is m.login.token. The login token.
     */
    private String token;

    /**
     * ID of the client device. If this does not correspond to a known client device, a new device will be created.
     * The server will auto-generate a device_id if this is not specified.
     */
    private String deviceId;

    /**
     * A display name to assign to the newly-created device. Ignored if device_id corresponds to a known device.
     */
    private String initialDeviceDisplayName;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public CharSequence getPassword() {
        return password;
    }

    public void setPassword(CharSequence password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getInitialDeviceDisplayName() {
        return initialDeviceDisplayName;
    }

    public void setInitialDeviceDisplayName(String initialDeviceDisplayName) {
        this.initialDeviceDisplayName = initialDeviceDisplayName;
    }
}
