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

package io.github.ma1uta.matrix.client.model.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Request for gets a list of the third party identifiers that the homeserver has associated with the user's account.
 */
@ApiModel(description = "Request for gets a list of the third party identifiers that the homeserver has associated with the user's "
    + "account.")
public class ThirdPartyIdentifier {

    /**
     * Required. The medium of the third party identifier. One of: ['email', 'msisdn'].
     */
    @ApiModelProperty(
        value = "The medium of the third party identifier. Must be 'email'",
        required = true,
        allowableValues = "email, msisdn"
    )
    private String medium;

    /**
     * Required. The third party identifier address.
     */
    @ApiModelProperty(
        value = "The third party identifier address",
        required = true
    )
    private String address;

    /**
     * Required. The timestamp, in milliseconds, when the identifier was validated by the identity server.
     */
    @ApiModelProperty(
        notes = "validated_at",
        value = "The timestamp, in milliseconds, when the identifier was validated by the identity server.",
        required = true
    )
    @JsonProperty("validated_at")
    private Long validatedAt;

    /**
     * Required. The timestamp, in milliseconds, when the homeserver associated the third party identifier with the user.
     */
    @ApiModelProperty(
        name = "added_at",
        value = "The timestamp, in milliseconds, when the homeserver associated the third party identifier with the user.",
        required = true
    )
    @JsonProperty("added_at")
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

    public Long getValidatedAt() {
        return validatedAt;
    }

    public void setValidatedAt(Long validatedAt) {
        this.validatedAt = validatedAt;
    }

    public Long getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(Long addedAt) {
        this.addedAt = addedAt;
    }
}
