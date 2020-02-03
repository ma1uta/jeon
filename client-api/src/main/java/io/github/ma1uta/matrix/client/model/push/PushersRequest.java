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

package io.github.ma1uta.matrix.client.model.push;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * JSON body request to create a new pusher.
 */
@Schema(
    description = "JSON body request to create a new pusher."
)
public class PushersRequest extends Pusher {

    /**
     * If true, the homeserver should add another pusher with the given pushkey and App ID in addition to any others with different
     * user IDs. Otherwise, the homeserver must remove any other pushers with the same App ID and pushkey for different users.
     * The default is false.
     */
    @Schema(
        description = "If true, the homeserver should add another pusher with the given pushkey and App ID in addition to any "
            + "others with different user IDs. Otherwise, the homeserver must remove any other pushers with the same App ID and "
            + "pushkey for different users. The default is false."
    )
    private Boolean append;

    public Boolean getAppend() {
        return append;
    }

    public void setAppend(Boolean append) {
        this.append = append;
    }
}
