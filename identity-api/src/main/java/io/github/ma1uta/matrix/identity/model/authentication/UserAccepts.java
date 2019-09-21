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

package io.github.ma1uta.matrix.identity.model.authentication;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * User accepts.
 */
@Schema(
    description = "User accepts."
)
public class UserAccepts {

    /**
     * Required. The URLs the user is accepting in this request.
     */
    @Schema(
        description = "The URLs the user is accepting in this request.",
        required = true
    )
    private List<String> accepts;

    public List<String> getAccepts() {
        return accepts;
    }

    public void setAccepts(List<String> accepts) {
        this.accepts = accepts;
    }
}
