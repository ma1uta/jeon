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

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * The user is identified by their Matrix ID.
 */
@ApiModel(description = "The user is identified by their Matrix ID.")
public class UserIdentifier extends Identifier {

    /**
     * A client can identify a user using their Matrix ID. This can either be the fully qualified Matrix user ID,
     * or just the localpart of the user ID.
     */
    @ApiModelProperty(
        value = "A client can identify a user using their Matrix ID. This can either be the fully qualified Matrix user ID,"
            + " or just the localpart of the user ID.",
        required = true
    )
    private String user;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
