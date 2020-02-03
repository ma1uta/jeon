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

import io.github.ma1uta.matrix.event.nested.PushCondition;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * JSON body request for push update api.
 */
@Schema(
    description = "JSON body request for push update api."
)
public class PushUpdateRequest {

    /**
     * Required. The action(s) to perform when the conditions for this rule are met. One of: ["notify", "dont_notify", "coalesce",
     * "set_tweak"].
     */
    @Schema(
        description = "The action(s) to perform when the conditions for this rule are met."
    )
    private List<String> actions;

    /**
     * The conditions that must hold true for an event in order for a rule to be applied to an event. A rule with no conditions
     * always matches. Only applicable to underride and override rules.
     */
    @Schema(
        description = "The conditions that must hold true for an event in order for a rule to be applied to an event. "
            + "A rule with no conditions always matches. Only applicable to underride and override rules."
    )
    private List<PushCondition> conditions;

    /**
     * Only applicable to content rules. The glob- style pattern to match against.
     */
    @Schema(
        description = "Only applicable to content rules. The glob- style pattern to match against."
    )
    private String pattern;

    public List<String> getActions() {
        return actions;
    }

    public void setActions(List<String> actions) {
        this.actions = actions;
    }

    public List<PushCondition> getConditions() {
        return conditions;
    }

    public void setConditions(List<PushCondition> conditions) {
        this.conditions = conditions;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
}
