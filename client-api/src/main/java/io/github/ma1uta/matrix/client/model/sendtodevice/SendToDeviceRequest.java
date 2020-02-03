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

package io.github.ma1uta.matrix.client.model.sendtodevice;

import io.github.ma1uta.matrix.event.content.EventContent;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

/**
 * Sent to device body request.
 */
@Schema(
    description = "Sent to device body request."
)
public class SendToDeviceRequest {

    /**
     * The messages to send. A map from user ID, to a map from device ID to message body. The device ID may also be *, meaning all
     * known devices for the user.
     */
    @Schema(
        description = "The messages to send. A map from user ID, to a map from device ID to message body. The device ID may also "
            + "be *, meaning all known devices for the user."
    )
    private Map<String, Map<String, EventContent>> messages;

    public Map<String, Map<String, EventContent>> getMessages() {
        return messages;
    }

    public void setMessages(Map<String, Map<String, EventContent>> messages) {
        this.messages = messages;
    }
}
