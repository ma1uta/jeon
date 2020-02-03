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

import io.github.ma1uta.matrix.client.model.account.AuthenticationData;

/**
 * In the REST API described in this specification, authentication works by the client and server exchanging JSON dictionaries.
 * The server indicates what authentication data it requires via the body of an HTTP 401 response, and the client submits that
 * authentication data via the auth request parameter.
 * <br>
 * Indicate that this object need user interactive session.
 */
public abstract class UserInteractiveData {

    /**
     * Additional authentication information for the user-interactive authentication API.
     * Note that this information is not used to define how the registered user should be authenticated,
     * but is instead used to authenticate the register call itself.
     * It should be left empty, or omitted, unless an earlier call returned an response with status code 401.
     */
    private AuthenticationData auth;

    public AuthenticationData getAuth() {
        return auth;
    }

    public void setAuth(AuthenticationData auth) {
        this.auth = auth;
    }
}
