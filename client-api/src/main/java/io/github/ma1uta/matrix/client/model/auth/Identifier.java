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

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Some authentication mechanisms use a user identifier object to identify a user. The user identifier object has a type field
 * to indicate the type of identifier being used, and depending on the type, has other fields giving the information required
 * to identify the user.
 */
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    property = "type"
)
@JsonSubTypes( {
    @JsonSubTypes.Type(name = "m.id.user", value = UserIdentifier.class),
    @JsonSubTypes.Type(name = "m.id.thirdparty", value = ThirdpartyIdentifier.class),
    @JsonSubTypes.Type(name = "m.id.phone", value = PhoneIdentifier.class)
})
@ApiModel(description = "Identifier types.",
    subTypes = {
        UserIdentifier.class,
        ThirdpartyIdentifier.class,
        PhoneIdentifier.class
    }
)
public abstract class Identifier {

    /**
     * Identifier type.
     */
    @ApiModelProperty(
        value = "Identifier type.",
        required = true
    )
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
