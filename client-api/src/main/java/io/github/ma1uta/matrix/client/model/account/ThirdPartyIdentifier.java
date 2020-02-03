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

package io.github.ma1uta.matrix.client.model.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;

/**
 * Request for gets a list of the third party identifiers that the homeserver has associated with the user's account.
 */
@Schema(
    description = "Request for gets a list of the third party identifiers that the homeserver has associated with the user's "
        + "account."
)
public class ThirdPartyIdentifier {

    /**
     * Required. The medium of the third party identifier. One of: ['email', 'msisdn'].
     */
    @Schema(
        description = "The medium of the third party identifier. Must be 'email'"
    )
    private String medium;

    /**
     * Required. The third party identifier address.
     */
    @Schema(
        description = "The third party identifier address"
    )
    private String address;

    /**
     * Required. The timestamp, in milliseconds, when the identifier was validated by the identity server.
     */
    @Schema(
        description = "The timestamp, in milliseconds, when the identifier was validated by the identity server."
    )
    @JsonbProperty("validated_at")
    private Long validatedAt;

    /**
     * Required. The timestamp, in milliseconds, when the homeserver associated the third party identifier with the user.
     */
    @Schema(
        description = "The timestamp, in milliseconds, when the homeserver associated the third party identifier with the user."
    )
    @JsonbProperty("added_at")
    private Long addedAt;

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @JsonProperty("validated_at")
    public Long getValidatedAt() {
        return validatedAt;
    }

    public void setValidatedAt(Long validatedAt) {
        this.validatedAt = validatedAt;
    }

    @JsonProperty("added_at")
    public Long getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(Long addedAt) {
        this.addedAt = addedAt;
    }
}
