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

package io.github.ma1uta.matrix.event.content;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ma1uta.matrix.event.nested.PublicKeys;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import javax.json.bind.annotation.JsonbProperty;

/**
 * Acts as an m.room.member invite event, where there isn't a target user_id to invite. This event contains a token and a
 * public key whose private key must be used to sign the token. Any user who can present that signature may use this invitation
 * to join the target room.
 */
@Schema(
    description = "Acts as an m.room.member invite event, where there isn't a target user_id to invite."
        + " This event contains a token and a public key whose private key must be used to sign the token."
        + " Any user who can present that signature may use this invitation to join the target room."
)
public class RoomThirdPartyInviteContent implements EventContent {

    /**
     * Required. A user-readable string which represents the user who has been invited. This should not contain the user's
     * third party ID, as otherwise when the invite is accepted it would leak the association between the matrix ID and the third party ID.
     */
    @Schema(
        name = "display_name",
        description = "A user-readable string which represents the user who has been invited."
            + " This should not contain the user's third party ID, as otherwise when the invite is accepted it would leak the association"
            + " between the matrix ID and the third party ID.",
        required = true
    )
    @JsonbProperty("display_name")
    private String displayName;

    /**
     * Required. A URL which can be fetched, with querystring public_key=public_key, to validate whether the key has been revoked.
     * The URL must return a JSON object containing a boolean property named 'valid'.
     */
    @Schema(
        name = "key_validity_url",
        description = "A URL which can be fetched, with querystring public_key=public_key, to"
            + " validate whether the key has been revoked. The URL must return a JSON object containing a boolean property named 'valid'.",
        required = true
    )
    @JsonbProperty("key_validity_url")
    private String keyValidityUrl;

    /**
     * Required. A base64-encoded ed25519 key with which token must be signed (though a signature from any entry in public_keys is
     * also sufficient). This exists for backwards compatibility.
     */
    @Schema(
        name = "public_key",
        description = "A base64-encoded ed25519 key with which token must be signed"
            + " (though a signature from any entry in public_keys is also sufficient). This exists for backwards compatibility.",
        required = true
    )
    @JsonbProperty("public_key")
    private String publicKey;

    /**
     * Keys with which the token may be signed.
     */
    @Schema(
        name = "public_keys",
        description = "Keys with which the token may be signed."
    )
    @JsonbProperty("public_keys")
    private List<PublicKeys> publicKeys;

    @JsonProperty("display_name")
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @JsonProperty("key_validity_url")
    public String getKeyValidityUrl() {
        return keyValidityUrl;
    }

    public void setKeyValidityUrl(String keyValidityUrl) {
        this.keyValidityUrl = keyValidityUrl;
    }

    @JsonProperty("public_key")
    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    @JsonProperty("public_keys")
    public List<PublicKeys> getPublicKeys() {
        return publicKeys;
    }

    public void setPublicKeys(List<PublicKeys> publicKeys) {
        this.publicKeys = publicKeys;
    }
}
