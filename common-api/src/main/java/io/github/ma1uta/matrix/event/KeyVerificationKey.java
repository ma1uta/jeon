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

package io.github.ma1uta.matrix.event;

import io.github.ma1uta.matrix.event.content.KeyVerificationKeyContent;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Sends the ephemeral public key for a device to the partner device. Typically sent as a to-device event.
 */
@Schema(
    description = "Sends the ephemeral public key for a device to the partner device. Typically sent as a to-device event."
)
public class KeyVerificationKey extends Event<KeyVerificationKeyContent> {

    /**
     * Event type.
     */
    public static final String TYPE = "m.key.verification.key";

    @Override
    public String getType() {
        return TYPE;
    }
}
