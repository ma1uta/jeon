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

package io.github.ma1uta.matrix.events;

import io.github.ma1uta.matrix.EventContent;
import io.github.ma1uta.matrix.events.messages.Audio;
import io.github.ma1uta.matrix.events.messages.Emote;
import io.github.ma1uta.matrix.events.messages.File;
import io.github.ma1uta.matrix.events.messages.Image;
import io.github.ma1uta.matrix.events.messages.Location;
import io.github.ma1uta.matrix.events.messages.Notice;
import io.github.ma1uta.matrix.events.messages.Text;
import io.github.ma1uta.matrix.events.messages.Video;
import io.github.ma1uta.matrix.events.nested.Relates;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;

/**
 * This event is used when sending messages in a room. Messages are not limited to be text. The ``msgtype`` key outlines the type
 * of message, e.g. text, audio, image, video, etc. The ``body`` key is text and MUST be used with every kind of ``msgtype`` as a
 * fallback mechanism for when a client cannot render a message. This allows clients to display *something* even if it is just plain text.
 */
@Schema(
    description = "This event is used when sending messages in a room. Messages are not limited to be text."
        + " The ``msgtype`` key outlines the type of message, e.g. text, audio, image, video, etc. The ``body`` key is text and MUST"
        + " be used with every kind of ``msgtype`` as a fallback mechanism for when a client cannot render a message."
        + " This allows clients to display *something* even if it is just plain text.",
    subTypes = {
        Audio.class,
        Emote.class,
        File.class,
        Image.class,
        Location.class,
        Notice.class,
        Text.class,
        Video.class
    }
)
public abstract class RoomMessage implements EventContent {

    /**
     * Message type.
     * <br>
     * Read-only.
     *
     * @return message type.
     */
    @Schema(
        description = "Message type.",
        readOnly = true
    )
    @JsonbProperty(value = "msgtype")
    public abstract String getMsgtype();

    /**
     * The textual representation of this message.
     */
    @Schema(
        description = "The textual representation of this message."
    )
    private String body;

    /**
     * Relates (reply, ...).
     */
    @Schema(
        name = "m.relates_to",
        description = "Relates (reply, ...)."
    )
    @JsonbProperty("m.relates_to")
    private Relates relatesTo;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Relates getRelatesTo() {
        return relatesTo;
    }

    public void setRelatesTo(Relates relatesTo) {
        this.relatesTo = relatesTo;
    }
}
