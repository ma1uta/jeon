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

package io.github.ma1uta.matrix.client.model.device;

import io.github.ma1uta.matrix.client.model.account.AuthenticationData;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Additional authentication information for the user-interactive authentication API.
 */
@Schema(
    description = "Additional authentication information for the user-interactive authentication API."
)
public class DeviceDeleteRequest {

    /**
     * Authentication information.
     */
    @Schema(
        description = "Authentication information."
    )
    private AuthenticationData auth;

    public AuthenticationData getAuth() {
        return auth;
    }

    public void setAuth(AuthenticationData auth) {
        this.auth = auth;
    }
}
