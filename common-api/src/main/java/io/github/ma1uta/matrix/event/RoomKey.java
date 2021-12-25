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

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ma1uta.matrix.event.content.RoomKeyContent;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;
import java.util.Map;

/**
 * This event type is used to exchange keys for end-to-end encryption. Typically it is encrypted as an m.room.encrypted event,
 * then sent as a to-device event.
 */
@Schema(
    description = "This event type is used to exchange keys for end-to-end encryption."
        + " Typically it is encrypted as an m.room.encrypted event, then sent as a to-device event."
)
public class RoomKey extends Event<RoomKeyContent> {

    /**
     * This event type is used to exchange keys for end-to-end encryption. Typically it is encrypted as an m.room.encrypted event,
     * then sent as a to-device event.
     */
    public static final String TYPE = "m.room_key";

    /**
     * Sender.
     */
    @Schema(
        description = "Sender."
    )
    private String sender;

    /**
     * Recipient.
     */
    @Schema(
        description = "Recipient."
    )
    private String recipient;

    /**
     * Recipient keys.
     */
    @Schema(
        description = "Recipient keys."
    )
    @JsonbProperty("recipient_keys")
    private Map<String, String> recipientKeys;

    /**
     * Keys.
     */
    @Schema(
        description = "Keys."
    )
    private Map<String, String> keys;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    @JsonProperty("recipient_keys")
    public Map<String, String> getRecipientKeys() {
        return recipientKeys;
    }

    public void setRecipientKeys(Map<String, String> recipientKeys) {
        this.recipientKeys = recipientKeys;
    }

    public Map<String, String> getKeys() {
        return keys;
    }

    public void setKeys(Map<String, String> keys) {
        this.keys = keys;
    }

    @Override
    public String getType() {
        return TYPE;
    }
}
