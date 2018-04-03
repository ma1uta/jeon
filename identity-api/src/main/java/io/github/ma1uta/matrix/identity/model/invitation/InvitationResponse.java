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

import java.util.List;

/**
 * Result of the pending invitation.
 *
 * @author ma1uta
 */
public class InvitationResponse {

    /**
     * The generated token.
     */
    private String token;

    /**
     * A list of [server's long-term public key, generated ephemeral public key].
     */
    @JsonProperty("public_keys")
    private List<String> publicKeys;

    /**
     * The generated display_name, which is a redacted version of address which does not leak the full contents of the address.
     */
    @JsonProperty("display_name")
    private String displayName;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<String> getPublicKeys() {
        return publicKeys;
    }

    public void setPublicKeys(List<String> publicKeys) {
        this.publicKeys = publicKeys;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
