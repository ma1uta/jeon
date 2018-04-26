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

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Authentication Data.
 */
public class AuthenticationData {

    /**
     * AuthType.
     */
    private String type;

    /**
     * Session.
     */
    private String session;

    /**
     * User id.
     */
    private String user;

    /**
     * Password.
     */
    private String password;

    /**
     * 3Pid address type.
     */
    private String medium;

    /**
     * 3Pid address.
     */
    private String address;

    /**
     * Capthca response.
     */
    private String response;

    /**
     * Auth token.
     */
    private String token;

    /**
     * Transaction id.
     */
    @JsonProperty("txn_id")
    private String txnId;

    /**
     * OAuth uri.
     */
    private String uri;

    /**
     * 3Pids credentials.
     */
    private ThreePidCred[] threepidCreds;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTxnId() {
        return txnId;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public ThreePidCred[] getThreepidCreds() {
        return threepidCreds;
    }

    public void setThreepidCreds(ThreePidCred[] threepidCreds) {
        this.threepidCreds = threepidCreds;
    }
}
