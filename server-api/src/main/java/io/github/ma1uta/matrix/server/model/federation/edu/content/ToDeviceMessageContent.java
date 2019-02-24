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

package io.github.ma1uta.matrix.server.model.federation.edu.content;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ma1uta.matrix.Id;
import io.github.ma1uta.matrix.event.content.RoomMessageContent;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;
import javax.json.bind.annotation.JsonbProperty;

/**
 * Direct device message content.
 */
@Schema(
    description = "Direct device message content."
)
public class ToDeviceMessageContent implements EphemeralDataUnitContent {

    /**
     * Required. User ID of the sender.
     */
    @Schema(
        description = "User ID of the sender.",
        required = true
    )
    private Id sender;

    /**
     * Required. Event type for the message.
     */
    @Schema(
        description = "Event type for the message.",
        required = true
    )
    private String type;

    /**
     * Required. Unique ID for the message, used for idempotence. Arbitrary utf8 string, of maximum length 32 codepoints.
     */
    @Schema(
        name = "message_id",
        description = "Unique ID for the message, used for idempotence. Arbitrary utf8 string, of maximum length 32 codepoints.",
        required = true
    )
    @JsonbProperty("message_id")
    private String messageId;

    /**
     * Required. The contents of the messages to be sent. These are arranged in a map of user IDs to a map of device IDs to message bodies.
     * The device ID may also be *, meaning all known devices for the user.
     */
    @Schema(
        description = "The contents of the messages to be sent. These are arranged in a map of user IDs to a map of device IDs"
            + " to message bodies. The device ID may also be *, meaning all known devices for the user.",
        required = true
    )
    private Map<Id, Map<String, RoomMessageContent>> messages;

    public Id getSender() {
        return sender;
    }

    public void setSender(Id sender) {
        this.sender = sender;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("message_id")
    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public Map<Id, Map<String, RoomMessageContent>> getMessages() {
        return messages;
    }

    public void setMessages(
        Map<Id, Map<String, RoomMessageContent>> messages) {
        this.messages = messages;
    }
}
