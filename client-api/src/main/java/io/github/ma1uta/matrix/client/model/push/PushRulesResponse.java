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

import io.github.ma1uta.matrix.event.nested.Ruleset;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * JSON body response for push api.
 */
@Schema(
    description = "JSON body response for push api."
)
public class PushRulesResponse {

    /**
     * Required. The global ruleset.
     */
    @Schema(
        description = "The global ruleset."
    )
    private Ruleset global;

    public Ruleset getGlobal() {
        return global;
    }

    public void setGlobal(Ruleset global) {
        this.global = global;
    }
}
