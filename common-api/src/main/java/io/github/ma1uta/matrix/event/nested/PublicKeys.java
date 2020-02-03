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

package io.github.ma1uta.matrix.event.nested;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;

/**
 * Public keys.
 */
@Schema(
    description = "Public keys."
)
public class PublicKeys {

    /**
     * An optional URL which can be fetched, with querystring public_key=public_key, to validate whether the key has been revoked.
     * The URL must return a JSON object containing a boolean property named 'valid'. If this URL is absent, the key must be
     * considered valid indefinitely.
     */
    @Schema(
        name = "key_validity_url",
        description = "An optional URL which can be fetched, with querystring public_key=public_key,"
            + " to validate whether the key has been revoked. The URL must return a JSON object containing a boolean property"
            + " named 'valid'. If this URL is absent, the key must be considered valid indefinitely."
    )
    @JsonbProperty("key_validity_url")
    private String keyValidityUrl;

    /**
     * Required. A base-64 encoded ed25519 key with which token may be signed.
     */
    @Schema(
        description = "A base-64 encoded ed25519 key with which token may be signed.",
        required = true
    )
    @JsonbProperty("public_key")
    private String publicKey;

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
}
