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

package io.github.ma1uta.matrix.client.model.report;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * JSON Body for report request.
 */
@Schema(
    description = "JSON Body for report request."
)
public class ReportRequest {

    /**
     * Required. The score to rate this content as where -100 is most offensive and 0 is inoffensive.
     */
    @Schema(
        description = "The score to rate this content as where -100 is most offensive and 0 is inoffensive."
    )
    private Integer score;

    /**
     * Required. The reason the content is being reported. May be blank.
     */
    @Schema(
        description = "The reason the content is being reported. May be blank."
    )
    private String reason;

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
