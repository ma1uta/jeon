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
 * Request for adds contact information to the user's account.
 */
@Schema(
    description = "Request for adds contact information to the user's account"
)
public class ThreePidRequest {

    /**
     * Required. The third party credentials to associate with the account.
     */
    @Schema(
        description = "The third party credentials to associate with the account"
    )
    @JsonbProperty("three_pid_creds")
    private ThreePidCred threePidCreds;

    /**
     * Whether the homeserver should also bind this third party identifier to the account's Matrix ID with the passed identity server.
     * Default: false.
     */
    @Schema(
        description = "Whether the homeserver should also bind this third party identifier to the account's Matrix ID with the passed"
            + " identity server"
    )
    private Boolean bind;

    @JsonProperty("three_pid_creds")
    public ThreePidCred getThreePidCreds() {
        return threePidCreds;
    }

    public void setThreePidCreds(ThreePidCred threePidCreds) {
        this.threePidCreds = threePidCreds;
    }

    public Boolean getBind() {
        return bind;
    }

    public void setBind(Boolean bind) {
        this.bind = bind;
    }
}
