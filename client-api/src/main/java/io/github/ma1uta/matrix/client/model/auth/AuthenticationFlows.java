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

import java.util.Map;

/**
 * Authentication flow.
 */
public class AuthenticationFlows {
    /**
     * Error code.
     */
    private String errcode;
    /**
     * Error message.
     */
    private String error;
    /**
     * Completed stages.
     */
    private String[] completed;
    /**
     * Stages.
     */
    private AuthenticationStage[] flows;
    /**
     * AuthType.
     */
    private Map<String, Map<String, String>> params;
    private String session;

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String[] getCompleted() {
        return completed;
    }

    public void setCompleted(String[] completed) {
        this.completed = completed;
    }

    public AuthenticationStage[] getFlows() {
        return flows;
    }

    public void setFlows(AuthenticationStage[] flows) {
        this.flows = flows;
    }

    public Map<String, Map<String, String>> getParams() {
        return params;
    }

    public void setParams(Map<String, Map<String, String>> params) {
        this.params = params;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }
}
