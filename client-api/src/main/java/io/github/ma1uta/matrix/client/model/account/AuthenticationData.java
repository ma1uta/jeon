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

import java.util.List;
import javax.json.bind.annotation.JsonbProperty;

/**
 * Authentication Data.
 */
@Schema(
    description = "Authentication Data."
)
public class AuthenticationData {

    /**
     * AuthType.
     */
    @Schema(
        description = "Auth type."
    )
    private String type;

    /**
     * Session.
     */
    @Schema(
        description = "Session."
    )
    private String session;

    /**
     * User id.
     */
    @Schema(
        description = "user id."
    )
    private String user;

    /**
     * Password.
     */
    @Schema(
        description = "password."
    )
    private char[] password;

    /**
     * 3Pid address type.
     */
    @Schema(
        description = "3pid address type."
    )
    private String medium;

    /**
     * 3Pid address.
     */
    @Schema(
        description = "3pid address."
    )
    private String address;

    /**
     * Capthca response.
     */
    @Schema(
        description = "Captcha response."
    )
    private String response;

    /**
     * Auth token.
     */
    @Schema(
        description = "Auth token."
    )
    private String token;

    /**
     * Transaction id.
     */
    @Schema(
        description = "transaction id."
    )
    @JsonbProperty("txn_id")
    private String txnId;

    /**
     * OAuth uri.
     */
    @Schema(
        description = "OAuth uri."
    )
    private String uri;

    /**
     * 3Pids credentials.
     */
    @Schema(
        description = "3pids credentials."
    )
    private List<ThreePidCred> threepidCreds;

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

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
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

    @JsonProperty("txn_id")
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

    public List<ThreePidCred> getThreepidCreds() {
        return threepidCreds;
    }

    public void setThreepidCreds(List<ThreePidCred> threepidCreds) {
        this.threepidCreds = threepidCreds;
    }
}
