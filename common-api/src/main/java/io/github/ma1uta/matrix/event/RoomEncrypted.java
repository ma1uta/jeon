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

package io.github.ma1uta.matrix.event;

import io.github.ma1uta.matrix.event.content.RoomEncryptedContent;
import io.github.ma1uta.matrix.event.encrypted.MegolmEncryptedContent;
import io.github.ma1uta.matrix.event.encrypted.OlmEncryptedContent;
import io.github.ma1uta.matrix.support.DeserializerUtil;
import io.github.ma1uta.matrix.support.RoomEncryptedDeserializer;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

/**
 * This event type is used when sending encrypted events. It can be used either within a room
 * (in which case it will have all of the Room Event fields), or as a to-device event.
 */
@Schema(
    description = "This event type is used when sending encrypted events. It can be used either within a room"
        + " (in which case it will have all of the Room Event fields), or as a to-device event.",
    subTypes = {
        MegolmEncryptedContent.class,
        OlmEncryptedContent.class
    }
)
public class RoomEncrypted extends RoomEvent<RoomEncryptedContent> {

    public RoomEncrypted() {
    }

    public RoomEncrypted(Map props) {
        super(props);
        setContent(DeserializerUtil.toObject(props, "content", RoomEncryptedDeserializer.getInstance()::deserialize));
    }

    @Override
    public String getType() {
        return EventType.ROOM_ENCRYPTED;
    }
}
