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

import io.github.ma1uta.matrix.event.content.DummyContent;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * This event type is used to indicate new Olm sessions for end-to-end encryption.
 * Typically it is encrypted as an m.room.encrypted event, then sent as a to-device event.
 * <p/>
 * The event does not have any content associated with it.
 * The sending client is expected to send a key share request shortly after this message, causing the receiving client to process
 * this m.dummy event as the most recent event and using the keyshare request to set up the session.
 * The keyshare request and m.dummy combination should result in the original sending client receiving keys over
 * the newly established session.
 */
@Schema(
    description = "This event type is used to indicate new Olm sessions for end-to-end encryption."
        + " Typically it is encrypted as an m.room.encrypted event, then sent as a to-device event."
)
public class Dummy extends Event<DummyContent> {

    /**
     * Event type.
     */
    public static final String TYPE = "m.dummy";

    @Override
    public String getType() {
        return TYPE;
    }
}
