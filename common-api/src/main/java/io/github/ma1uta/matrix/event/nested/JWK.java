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

import java.util.List;
import javax.json.bind.annotation.JsonbProperty;

/**
 * JSON Web Key.
 */
@Schema(
    description = "JSON Web Key."
)
public class JWK {

    /**
     * Required. Key type. Must be oct.
     */
    @Schema(
        description = "Key type. Must be oct.",
        required = true
    )
    private String key;

    /**
     * Required. Key operations. Must at least contain encrypt and decrypt.
     */
    @Schema(
        name = "key_opts",
        description = "Key operations, Must at least contain encrypt and decrypt.",
        required = true
    )
    @JsonbProperty("key_opts")
    private List<String> keyOpts;

    /**
     * Required. Algorithm. Must be A256CTR.
     */
    @Schema(
        description = "Algorithm, Must be A256CTR.",
        required = true
    )
    private String alg;

    /**
     * Required. The key, encoded as urlsafe unpadded base64.
     */
    @Schema(
        name = "k",
        description = "The key, encoded as urlsafe unpadded base64.",
        required = true
    )
    @JsonbProperty("k")
    private String encodedKey;

    /**
     * Required. Extractable. Must be true. This is a W3C extension.
     */
    @Schema(
        description = "Extractable. Must be true. This is a W3C extension.",
        required = true
    )
    private Boolean ext;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @JsonProperty("key_opts")
    public List<String> getKeyOpts() {
        return keyOpts;
    }

    public void setKeyOpts(List<String> keyOpts) {
        this.keyOpts = keyOpts;
    }

    public String getAlg() {
        return alg;
    }

    public void setAlg(String alg) {
        this.alg = alg;
    }

    @JsonProperty("k")
    public String getEncodedKey() {
        return encodedKey;
    }

    public void setEncodedKey(String encodedKey) {
        this.encodedKey = encodedKey;
    }

    public Boolean getExt() {
        return ext;
    }

    public void setExt(Boolean ext) {
        this.ext = ext;
    }
}
