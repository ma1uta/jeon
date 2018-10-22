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

package io.github.ma1uta.matrix.identity.model.validation;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ma1uta.matrix.support.DeserializerUtil;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;
import javax.json.bind.annotation.JsonbProperty;

/**
 * Validation result.
 */
@Schema(
    description = "Validation result."
)
public class ValidationResponse {

    /**
     * Required. The medium type of the 3pid.
     */
    @Schema(
        description = "The medium type of the 3pid.",
        required = true
    )
    private String medium;

    /**
     * Required. The address of the 3pid being looked up.
     */
    @Schema(
        description = "The address of the 3pid being looked up.",
        required = true
    )
    private String address;

    /**
     * Required. Timestamp, in milliseconds, indicating the time that the 3pid was validated.
     */
    @Schema(
        name = "validated_at",
        description = "Timestamp, in milliseconds, indicating the time that the 3pid was validated.",
        required = true
    )
    @JsonbProperty("validated_at")
    private Long validatedAt;

    public ValidationResponse() {
    }

    public ValidationResponse(Map props) {
        this.medium = DeserializerUtil.toString(props, "medium");
        this.address = DeserializerUtil.toString(props, "address");
        this.validatedAt = DeserializerUtil.toLong(props, "validated_at");
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    @JsonProperty("validated_at")
    public Long getValidatedAt() {
        return validatedAt;
    }

    public void setValidatedAt(Long validatedAt) {
        this.validatedAt = validatedAt;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
