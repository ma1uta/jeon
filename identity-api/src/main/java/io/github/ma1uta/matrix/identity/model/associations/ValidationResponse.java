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

package io.github.ma1uta.matrix.identity.model.associations;

import io.github.ma1uta.matrix.support.DeserializerUtil;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

/**
 * Result of the 3pid validation.
 */
@Schema(
    description = "Result of the 3pid validation."
)
public class ValidationResponse {

    /**
     * Required. Whether the validation was successful or not.
     */
    @Schema(
        description = "Whether the validation was successful or not.",
        required = true
    )
    private Boolean validated;

    public ValidationResponse() {
    }

    public ValidationResponse(Map props) {
        this.validated = DeserializerUtil.toBoolean(props, "validated");
    }

    public Boolean getValidated() {
        return validated;
    }

    public void setValidated(Boolean validated) {
        this.validated = validated;
    }
}
