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

package io.github.ma1uta.matrix.events.nested;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Map;

/**
 * Encrypted file.
 */
@ApiModel(description = "Encrypted file.")
public class EncryptedFile {

    /**
     * Required. The URL to the file.
     */
    @ApiModelProperty(
        value = "the URL to the file.",
        required = true
    )
    private String url;

    /**
     * Required. A JSON Web Key object.
     */
    @ApiModelProperty(
        value = "A JSON Web Key object.",
        required = true
    )
    private JWK key;

    /**
     * Required. The Initialisation Vector used by AES-CTR, encoded as unpadded base64.
     */
    @ApiModelProperty(
        value = " The Initialisation Vector used by AES-CTR, encoded as unpadded base64.",
        required = true
    )
    private String iv;

    /**
     * Required. A map from an algorithm name to a hash of the ciphertext, encoded as unpadded base64.
     * Clients should support the SHA-256 hash, which uses the key sha256.
     */
    @ApiModelProperty(
        value = "A map from an algorithm name to a hash of the ciphertext, encoded as unpadded base64."
            + " Clients should support the SHA-256 hash, which uses the key sha256."
    )
    private Map<String, String> hashes;

    /**
     * Required. Version of the encrypted attachments protocol. Must be v2.
     */
    @ApiModelProperty(
        name = "v",
        value = "Version of the encrypted attachments protocol. Must be v2.",
        required = true
    )
    @JsonProperty("v")
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
