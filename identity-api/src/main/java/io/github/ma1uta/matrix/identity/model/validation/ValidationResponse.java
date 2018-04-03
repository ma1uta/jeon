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

/**
 * Validation result.
 *
 * @author ma1uta
 */
public class ValidationResponse {

    /**
     * The literal string "email" or "msisdn".
     */
    private String medium;

    /**
     * Validation timestamp.
     */
    @JsonProperty("validated_at")
    private Long validatedAt;

    /**
     * The 3pid address of the user being validated.
     */
    private String address;

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

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
