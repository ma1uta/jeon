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
 * Request for the proxies the identity server API validate/email/requestToken.
 */
public class RequestToken {
    /**
     * The ID server to send the onward request to as a hostname with an appended colon and port number if the port is not the default.
     */
    private String idServer;
    /**
     * Required. Client-generated secret string used to protect this session.
     */
    private String clientSecret;
    /**
     * Required. The email address.
     */
    private String email;
    /**
     * Required. Used to distinguish protocol level retries from requests to re-send the email.
     */
    private String sendAttempt;

    public String getIdServer() {
        return idServer;
    }

    public void setIdServer(String idServer) {
        this.idServer = idServer;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSendAttempt() {
        return sendAttempt;
    }

    public void setSendAttempt(String sendAttempt) {
        this.sendAttempt = sendAttempt;
    }
}
