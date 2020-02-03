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

import java.util.Map;
import javax.json.bind.annotation.JsonbProperty;

/**
 * Encrypted file.
 */
@Schema(
    description = "Encrypted file."
)
public class EncryptedFile {

    /**
     * Required. The URL to the file.
     */
    @Schema(
        description = "the URL to the file.",
        required = true
    )
    private String url;

    /**
     * Required. A JSON Web Key object.
     */
    @Schema(
        description = "A JSON Web Key object.",
        required = true
    )
    private JWK key;

    /**
     * Required. The Initialisation Vector used by AES-CTR, encoded as unpadded base64.
     */
    @Schema(
        description = " The Initialisation Vector used by AES-CTR, encoded as unpadded base64.",
        required = true
    )
    private String iv;

    /**
     * Required. A map from an algorithm name to a hash of the ciphertext, encoded as unpadded base64.
     * Clients should support the SHA-256 hash, which uses the key sha256.
     */
    @Schema(
        description = "A map from an algorithm name to a hash of the ciphertext, encoded as unpadded base64."
            + " Clients should support the SHA-256 hash, which uses the key sha256."
    )
    private Map<String, String> hashes;

    /**
     * Required. Version of the encrypted attachments protocol. Must be v2.
     */
    @Schema(
        name = "v",
        description = "Version of the encrypted attachments protocol. Must be v2.",
        required = true
    )
    @JsonbProperty("v")
    private String version;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public JWK getKey() {
        return key;
    }

    public void setKey(JWK key) {
        this.key = key;
    }

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }

    public Map<String, String> getHashes() {
        return hashes;
    }

    public void setHashes(Map<String, String> hashes) {
        this.hashes = hashes;
    }

    @JsonProperty("v")
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
