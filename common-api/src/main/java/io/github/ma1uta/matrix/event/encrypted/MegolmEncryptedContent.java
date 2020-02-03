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

package io.github.ma1uta.matrix.event.encrypted;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ma1uta.matrix.event.content.RoomEncryptedContent;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;

/**
 * Megolm encrypted message.
 */
@Schema(
    description = "Megolm encrypted message."
)
public class MegolmEncryptedContent extends RoomEncryptedContent {

    /**
     * Megolm algorithm version.
     */
    public static final String ALGORITHM = "m.megolm.v1.aes-sha2";

    /**
     * Required. The encrypted content of the event. The encrypted payload itself.
     */
    @Schema(
        description = "The encrypted content of the event. The encrypted payload itself.",
        required = true
    )
    private String ciphertext;

    public String getCiphertext() {
        return ciphertext;
    }

    public void setCiphertext(String ciphertext) {
        this.ciphertext = ciphertext;
    }

    @JsonbProperty("algorithm")
    @JsonProperty(value = "algorithm", access = JsonProperty.Access.READ_ONLY)
    @Override
    public String getAlgorithm() {
        return ALGORITHM;
    }
}
