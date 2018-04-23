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

package io.github.ma1uta.matrix.client.model.account;

/**
 * Request for changes the password for an account on this homeserver.
 */
public class PasswordRequest {

    /**
     * Required. The new password for the account.
     */
    private String newPassword;

    /**
     * Additional authentication information for the user-interactive authentication API.
     */
    private AuthenticationData auth;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public AuthenticationData getAuth() {
        return auth;
    }

    public void setAuth(AuthenticationData auth) {
        this.auth = auth;
    }
}
