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

import io.github.ma1uta.matrix.event.content.KeyVerificationRequestContent;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Requests a key verification with another user's devices. Typically sent as a to-device event.
 */
@Schema(
    description = "Requests a key verification with another user's devices. Typically sent as a to-device event."
)
public class KeyVerificationRequest extends Event<KeyVerificationRequestContent> {

    /**
     * Requests a key verification with another user's devices. Typically sent as a to-device event.
     */
    public static final String TYPE = "m.key.verification.request";

    @Override
    public String getType() {
        return TYPE;
    }
}
