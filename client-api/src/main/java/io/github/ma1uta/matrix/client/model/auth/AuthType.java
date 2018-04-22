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
 * Authentication types.
 * <p/>
 * <a href="https://matrix.org/docs/spec/client_server/unstable.html#id100>See more</a>.
 *
 * @author ma1uta
 */
public final class AuthType {

    private AuthType() {
        // singleton.
    }

    /**
     * The client submits a username and secret password, both sent in plain-text.
     */
    public static final String PASSWORD = "m.login.password";

    /**
     * The user completes a Google ReCaptcha 2.0 challenge.
     */
    public static final String RECAPTCHA = "m.login.recaptcha";

    /**
     * Authentication is supported via OAuth2 URLs. This login consists of multiple requests.
     */
    public static final String OAUTH2 = "m.login.oauth2";

    /**
     * Authentication is supported by authorising an email address with an identity server.
     */
    public static final String EMAIL_IDENTITY = "m.login.email.identity";

    /**
     * The client submits a login token.
     */
    public static final String TOKEN = "m.login.token";

    /**
     * Dummy authentication always succeeds and requires no extra parameters. Its purpose is to allow servers to not require any
     * form of User-Interactive Authentication to perform a request.
     */
    public static final String DUMMY = "m.login.dummy";
}
