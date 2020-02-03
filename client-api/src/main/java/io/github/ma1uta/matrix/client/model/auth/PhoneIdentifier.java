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

package io.github.ma1uta.matrix.client.model.auth;

import io.github.ma1uta.matrix.client.api.AuthApi;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * The user is identified by a phone number.
 */
@Schema(
    description = "The user is identified by a phone number."
)
public class PhoneIdentifier extends Identifier {

    /**
     * The country that the phone number is from.
     */
    @Schema(
        description = "The country that the phone number is from."
    )
    private String country;

    /**
     * The phone number.
     */
    @Schema(
        description = "The phone number."
    )
    private String phone;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    @Override
    public String getType() {
        return AuthApi.IdentifierType.PHONE;
    }
}
