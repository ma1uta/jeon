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

package io.github.ma1uta.matrix.server.model.federation;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * User info of the OpenID request.
 */
@Schema(
    description = "User info of the OpenID request."
)
public class UserInfoResponse {

    /**
     * Required. The Matrix User ID who generated the token.
     */
    @Schema(
        description = "The Matrix User ID who generated the token.",
        required = true
    )
    private String sub;

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }
}