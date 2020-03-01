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

package io.github.ma1uta.matrix.event.content;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * A moderation policy rule which affects room IDs and room aliases.
 */
@Schema(
    description = "A moderation policy rule which affects room IDs and room aliases."
)
public class PolicyRuleRoomContent implements EventContent {

    /**
     * Required. The entity affected by this rule. Glob characters * and ? can be used to match zero or more and
     * one or more characters respectively.
     */
    @Schema(
        description = "The entity affected by this rule. Glob characters * and ? can be used to match zero or more and one or more"
            + " characters respectively.",
        required = true
    )
    private String entity;

    /**
     * Required. The suggested action to take. Currently only m.ban is specified.
     */
    @Schema(
        description = "The suggested action to take. Currently only m.ban is specified.",
        required = true
    )
    private String recommendation;

    /**
     * Required. The human-readable description for the recommendation.
     */
    @Schema(
        description = "The human-readable description for the recommendation.",
        required = true
    )
    private String reason;

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
