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

package io.github.ma1uta.matrix.identity.model.invitation;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import javax.json.bind.annotation.JsonbProperty;

/**
 * Result of the pending invitation.
 */
@Schema(
    description = "Result of the pending invitation."
)
public class InvitationResponse {

    /**
     * Required. The generated token. Must be a string consisting of the characters [0-9a-zA-Z.=_-].
     * Its length must not exceed 255 characters and it must not be empty.
     */
    @Schema(
        description = "The generated token. Must be a string consisting of the characters [0-9a-zA-Z.=_-]."
            + " Its length must not exceed 255 characters and it must not be empty.",
        required = true
    )
    private String token;

    /**
     * Required. A list of [server's long-term public key, generated ephemeral public key].
     */
    @Schema(
        name = "public_keys",
        description = "A list of [server's long-term public key, generated ephemeral public key].",
        required = true
    )
    @JsonbProperty("public_keys")
    private List<String> publicKeys;

    /**
     * Required. The generated (redacted) display_name.
     */
    @Schema(
        name = "display_name",
        description = "The generated (redacted) display_name.",
        required = true
    )
    @JsonbProperty("display_name")
    private String displayName;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @JsonProperty("public_keys")
    public List<String> getPublicKeys() {
        return publicKeys;
    }

    public void setPublicKeys(List<String> publicKeys) {
        this.publicKeys = publicKeys;
    }

    @JsonProperty("display_name")
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
