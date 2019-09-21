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

package io.github.ma1uta.matrix.identity.model.terms;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

/**
 * All the terms of service offer by the server.
 */
@Schema(
    description = "All the terms of service offer by the server"
)
public class TermsResponse {

    /**
     * Policies.
     */
    @Schema(
        description = "Policies."
    )
    private Map<String, Map<String, Object>> policies;

    public Map<String, Map<String, Object>> getPolicies() {
        return policies;
    }

    public void setPolicies(Map<String, Map<String, Object>> policies) {
        this.policies = policies;
    }
}
