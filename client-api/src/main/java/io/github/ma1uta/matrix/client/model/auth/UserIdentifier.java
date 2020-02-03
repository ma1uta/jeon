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

package io.github.ma1uta.matrix.client.model.auth;

import io.github.ma1uta.matrix.client.api.AuthApi;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * The user is identified by their Matrix ID.
 */
@Schema(
    description = "The user is identified by their Matrix ID."
)
public class UserIdentifier extends Identifier {

    /**
     * A client can identify a user using their Matrix ID. This can either be the fully qualified Matrix user ID,
     * or just the localpart of the user ID.
     */
    @Schema(
        description = "A client can identify a user using their Matrix ID. This can either be the fully qualified Matrix user ID,"
            + " or just the localpart of the user ID."
    )
    private String user;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String getType() {
        return AuthApi.IdentifierType.USER;
    }
}
