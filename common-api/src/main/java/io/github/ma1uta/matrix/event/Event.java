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
import io.github.ma1uta.matrix.event.content.EventContent;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;

/**
 * Event.
 *
 * @param <C> type of the event content.
 */
@Schema(
    description = "Event.",
    subTypes = {
        RoomEvent.class,
        Direct.class,
        Dummy.class,
        ForwardedRoomKey.class,
        FullyRead.class,
        IgnoredUserList.class,
        KeyVerificationAccept.class,
        KeyVerificationCancel.class,
        KeyVerificationKey.class,
        KeyVerificationMac.class,
        KeyVerificationStart.class,
        Presence.class,
        PushRules.class,
        RawEvent.class,
        Receipt.class,
        RoomKey.class,
        RoomKeyRequest.class,
        Tag.class,
        Typing.class
    }
)
public abstract class Event<C extends EventContent> {

    /**
     * The fields in this object will vary depending on the type of event. When interacting with the REST API, this is the HTTP body.
     */
    @Schema(
        description = "The fields in this object will vary depending on the type of event. When interacting with the REST API, this "
            + "is the HTTP body.",
        implementation = EventContent.class
    )
    private C content;

    public C getContent() {
        return content;
    }

    public void setContent(C content) {
        this.content = content;
    }

    /**
     * Required. The type of event. This SHOULD be namespaced similar to Java package naming conventions e.g.
     * 'com.example.subdomain.event.type'.
     *
     * @return The type of the event.
     */
    @Schema(
        description = "The type of event. This SHOULD be namespaced similar to Java package naming conventions.",
        required = true
    )
    @JsonbProperty("type")
    @JsonProperty(value = "type", access = JsonProperty.Access.READ_ONLY)
    public abstract String getType();
}
