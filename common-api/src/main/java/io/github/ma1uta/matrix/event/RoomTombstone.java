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

import io.github.ma1uta.matrix.event.content.TombstoneContent;

/**
 * A state event signifying that a room has been upgraded to a different room version, and that clients should go there.
 */
public class RoomTombstone extends StateEvent<TombstoneContent> {

    /**
     * A state event signifying that a room has been upgraded to a different room version, and that clients should go there.
     */
    public static final String TYPE = "m.room.tombstone";

    @Override
    public String getType() {
        return TYPE;
    }
}
