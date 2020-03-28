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

package io.github.ma1uta.matrix;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.Map;

/**
 * User-interactive response.
 */
@Schema(
    description = "User-interactive response."
)
public class UserInteractiveResponse implements ExceptionResponse {

    /**
     * Completed stages.
     */
    @Schema(
        description = "Completed stages."
    )
    private List<String> completed;

    /**
     * Stages.
     */
    @Schema(
        description = "stages."
    )
    private List<AuthenticationStage> flows;

    /**
     * Stage parameters.
     */
    @Schema(
        description = "Stage parameters."
    )
    private Map<String, Object> params;

    /**
     * User-interactive session.
     */
    @Schema(
        description = "User-interactive session."
    )
    private String session;

    public List<String> getCompleted() {
        return completed;
    }

    public void setCompleted(List<String> completed) {
        this.completed = completed;
    }

    public List<AuthenticationStage> getFlows() {
        return flows;
    }

    public void setFlows(List<AuthenticationStage> flows) {
        this.flows = flows;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }
}
