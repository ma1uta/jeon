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

package io.github.ma1uta.matrix.event.encrypted;

import io.github.ma1uta.matrix.event.Event;
import io.github.ma1uta.matrix.event.content.RoomEncryptedContent;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

/**
 * Megolm encrypted message.
 */
@Schema(
    description = "Megolm encrypted message."
)
public class MegolmEncryptedContent extends RoomEncryptedContent {

    /**
     * Required. The encrypted content of the event. The encrypted payload itself.
     */
    @Schema(
        description = "The encrypted content of the event. The encrypted payload itself.",
        required = true
    )
    private String ciphertext;

    public MegolmEncryptedContent() {
    }

    public MegolmEncryptedContent(Map props) {
        super(props);
        this.ciphertext = (String) props.get("ciphertext");
    }

    public String getCiphertext() {
        return ciphertext;
    }

    public void setCiphertext(String ciphertext) {
        this.ciphertext = ciphertext;
    }

    @Override
    public String getAlgorithm() {
        return Event.Encryption.MEGOLM;
    }
}
