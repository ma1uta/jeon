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

package io.github.ma1uta.matrix.client.model.auth;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;
import static com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;
import static io.github.ma1uta.matrix.client.api.AuthApi.IdentifierType.PHONE;
import static io.github.ma1uta.matrix.client.api.AuthApi.IdentifierType.THIRD_PARTY;
import static io.github.ma1uta.matrix.client.api.AuthApi.IdentifierType.USER;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Some authentication mechanisms use a user identifier object to identify a user. The user identifier object has a type field
 * to indicate the type of identifier being used, and depending on the type, has other fields giving the information required
 * to identify the user.
 */
@JsonTypeInfo(
    use = NAME,
    property = "type"
)
@JsonSubTypes( {
    @Type(name = USER, value = UserIdentifier.class),
    @Type(name = THIRD_PARTY, value = ThirdpartyIdentifier.class),
    @Type(name = PHONE, value = PhoneIdentifier.class)
})
@Schema(
    description = "Identifier types."
)
public abstract class Identifier {

    /**
     * The identifier type.
     *
     * @return The identifier type.
     */
    @Schema(
        description = "Identifier type."
    )
    @JsonProperty(value = "type", access = READ_ONLY)
    public abstract String getType();
}
