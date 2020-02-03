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

import io.github.ma1uta.matrix.event.content.CallHangupContent;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Message Event.
 * <p>
 * Sent by either party to signal their termination of the call. This can be sent either once the call has has been
 * established or before to abort the call.
 * </p>
 */
@Schema(
    description = "Sent by either party to signal their termination of the call. This can be sent either once the call has has been"
        + " established or before to abort the call."
)
public class CallHangup extends RoomEvent<CallHangupContent> {

    /**
     * Sent by either party to signal their termination of the call. This can be sent either once the call has has been
     * established or before to abort the call.
     */
    public static final String TYPE = "m.call.hangup";

    @Override
    public String getType() {
        return TYPE;
    }
}
