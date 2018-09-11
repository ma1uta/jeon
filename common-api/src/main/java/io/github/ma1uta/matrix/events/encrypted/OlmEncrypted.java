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

package io.github.ma1uta.matrix.events.encrypted;

import io.github.ma1uta.matrix.Event;
import io.github.ma1uta.matrix.events.RoomEncrypted;
import io.github.ma1uta.matrix.events.nested.CiphertextInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Map;

/**
 * Olm encrypted message.
 */
@ApiModel(description = "Olm encrypted message.")
public class OlmEncrypted extends RoomEncrypted {

    /**
     * Required. The encrypted content of the event. A map from the recipient Curve25519 identity key to ciphertext information.
     * For more details, see Messaging Algorithms.
     */
    @ApiModelProperty(
        value = "The encrypted content of the event. A map from the recipient Curve25519 identity key to ciphertext information." +
            " For more details, see Messaging Algorithms.",
        required = true
    )
    private Map<String, CiphertextInfo> ciphertext;

    public Map<String, CiphertextInfo> getCiphertext() {
        return ciphertext;
    }

    public void setCiphertext(Map<String, CiphertextInfo> ciphertext) {
        this.ciphertext = ciphertext;
    }

    public String getAlgorithm() {
        return Event.Enctyption.OLM;
    }
}