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

package io.github.ma1uta.matrix.event.nested;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Notifications.
 */
@Schema(
    description = "Notifications."
)
public class NotificationPowerLevel {

    /**
     * The level required to trigger an @room notification. Defaults to 50 if unspecified.
     */
    @Schema(
        description = "The level required to trigger an @room notification. Defaults to 50 if unspecified."
    )
    private Byte room;

    public Byte getRoom() {
        return room;
    }

    public void setRoom(Byte room) {
        this.room = room;
    }
}
