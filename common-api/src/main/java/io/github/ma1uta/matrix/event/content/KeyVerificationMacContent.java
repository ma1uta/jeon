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

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

/**
 * Sends the MAC of a device's key to the partner device. Typically sent as a to-device event.
 */
@Schema(
    description = "Sends the MAC of a device's key to the partner device. Typically sent as a to-device event."
)
public class KeyVerificationMacContent extends AbstractKeyVerificationRelatesContent {

    /**
     * Required. A map of the key ID to the MAC of the key, using the algorithm in the verification process.
     * The MAC is encoded as unpadded base64.
     */
    @Schema(
        description = "A map of the key ID to the MAC of the key, using the algorithm in the verification process."
            + " The MAC is encoded as unpadded base64.",
        required = true
    )
    private Map<String, String> mac;

    /**
     * Required. The MAC of the comma-separated, sorted, list of key IDs given in the mac property, encoded as unpadded base64.
     */
    @Schema(
        description = "The MAC of the comma-separated, sorted, list of key IDs given in the mac property, encoded as unpadded base64.",
        required = true
    )
    private String keys;

    public Map<String, String> getMac() {
        return mac;
    }

    public void setMac(Map<String, String> mac) {
        this.mac = mac;
    }

    public String getKeys() {
        return keys;
    }

    public void setKeys(String keys) {
        this.keys = keys;
    }
}
