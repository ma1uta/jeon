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

package io.github.ma1uta.matrix.event.nested;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import javax.json.bind.annotation.JsonbProperty;

/**
 * Push rule.
 */
@Schema(
    description = "Push rule."
)
public class PushRule {

    /**
     * Required. The actions to perform when this rule is matched.
     */
    @Schema(
        description = "The actions to perform when this rule is matched."
    )
    private List<Object> actions;

    /**
     * Required. Whether this is a default rule, or has been set explicitly.
     */
    @Schema(
        description = "Whether this is a default rule, or has been set explicitly."
    )
    @JsonbProperty("default")
    private Boolean defaultRule;

    /**
     * Required. Whether the push rule is enabled or not.
     */
    @Schema(
        description = "Whether the push rule is enabled or not."
    )
    private Boolean enabled;

    /**
     * Required. The ID of this rule.
     */
    @Schema(
        description = "The ID of this rule."
    )
    @JsonbProperty("rule_id")
    private String ruleId;

    /**
     * The conditions that must hold true for an event in order for a rule to be applied to an event. A rule with no
     * conditions always matches. Only applicable to underride and override rules.
     */
    @Schema(
        description = "The conditions that must hold true for an event in order for a rule to be applied to an event. A rule with no "
            + "conditions always matches. Only applicable to underride and override rules."
    )
    private List<PushCondition> conditions;

    /**
     * The glob-style pattern to match against. Only applicable to content rules.
     */
    @Schema(
        description = "The glob-style pattern to match against. Only applicable to content rules."
    )
    private String pattern;

    public List<Object> getActions() {
        return actions;
    }

    public void setActions(List<Object> actions) {
        this.actions = actions;
    }

    @JsonProperty("default")
    public Boolean getDefaultRule() {
        return defaultRule;
    }

    public void setDefaultRule(Boolean defaultRule) {
        this.defaultRule = defaultRule;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @JsonProperty("rule_id")
    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
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
