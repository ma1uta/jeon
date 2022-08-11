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

package io.github.ma1uta.matrix.client.model.capability;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Capability to indicate if the user is able to add, remove or change 3PID associations on their account.
 */
@Schema(
    description = "Capability to indicate if the user is able to add, remove or change 3PID associations on their account."
)
public class ThreePidChangeCapability {

    /**
     * Required. True if the user is able to add, remove or change 3PID associations on their account.
     */
    @Schema(
        description = "True if the user is able to add, remove or change 3PID associations on their account.",
        required = true
    )
    private Boolean enabled;

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
