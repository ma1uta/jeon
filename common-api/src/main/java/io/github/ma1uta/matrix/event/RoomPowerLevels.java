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

import io.github.ma1uta.matrix.event.content.RoomPowerLevelsContent;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * This event specifies the minimum level a user must have in order to perform a certain action. It also specifies the levels of
 * each user in the room.
 * <p>
 * If a user_id is in the users list, then that user_id has the associated power level. Otherwise they have the default level
 * users_default. If users_default is not supplied, it is assumed to be 0. If the room contains no m.room.power_levels event,
 * the room's creator has a power level of 100, and all other users have a power level of 0.
 * </p>
 * <p>
 * The level required to send a certain event is governed by events, state_default and events_default. If an event type is
 * specified in events, then the user must have at least the level specified in order to send that event. If the event type
 * is not supplied, it defaults to events_default for Message Events and state_default for State Events.
 * </p>
 * <p>
 * If there is no state_default in the m.room.power_levels event, the state_default is 50. If there is no events_default
 * in the m.room.power_levels event, the events_default is 0. If the room contains no m.room.power_levels event, both the
 * state_default and events_default are 0.
 * </p>
 * <p>
 * The power level required to invite a user to the room, kick a user from the room, ban a user from the room, or redact an
 * event, is defined by invite, kick, ban, and redact, respectively. Each of these levels defaults to 50 if they are not
 * specified in the m.room.power_levels event, or if the room contains no m.room.power_levels event.
 * </p>
 */
@Schema(
    description = "This event specifies the minimum level a user must have in order to perform a certain action."
        + " It also specifies the levels of each user in the room. If a user_id is in the users list, then that user_id has"
        + " the associated power level. Otherwise they have the default level users_default. If users_default is not supplied,"
        + " it is assumed to be 0. If the room contains no m.room.power_levels event, the room's creator has a power level of"
        + " 100, and all other users have a power level of 0. The level required to send a certain event is governed by events,"
        + " state_default and events_default. If an event type is specified in events, then the user must have at least the level"
        + " specified in order to send that event. If the event type is not supplied, it defaults to events_default for Message"
        + " Events and state_default for State Events. If there is no state_default in the m.room.power_levels event,"
        + " the state_default is 50. If there is no events_default in the m.room.power_levels event, the events_default is 0."
        + " If the room contains no m.room.power_levels event, both the state_default and events_default are 0."
        + " The power level required to invite a user to the room, kick a user from the room, ban a user from the room, or redact an"
        + " event, is defined by invite, kick, ban, and redact, respectively. Each of these levels defaults to 50 if they are not"
        + " specified in the m.room.power_levels event, or if the room contains no m.room.power_levels event."
)
public class RoomPowerLevels extends StateEvent<RoomPowerLevelsContent> {

    /**
     * This event specifies the minimum level a user must have in order to perform a certain action. It also specifies the
     * levels of each user in the room.
     * <br>
     * If a user_id is in the users list, then that user_id has the associated power level. Otherwise they have the default
     * level users_default. If users_default is not supplied, it is assumed to be 0. If the room contains no m.room.power_levels
     * event, the room's creator has a power level of 100, and all other users have a power level of 0.
     * <br>
     * The level required to send a certain event is governed by events, state_default and events_default. If an event type is
     * specified in events, then the user must have at least the level specified in order to send that event. If the event type
     * is not supplied, it defaults to events_default for Message Events and state_default for State Events.
     * <br>
     * If there is no state_default in the m.room.power_levels event, the state_default is 50. If there is no events_default
     * in the m.room.power_levels event, the events_default is 0. If the room contains no m.room.power_levels event, both the
     * state_default and events_default are 0.
     * <br>
     * The power level required to invite a user to the room, kick a user from the room, ban a user from the room, or redact
     * an event, is defined by invite, kick, ban, and redact, respectively. Each of these levels defaults to 50 if they are
     * not specified in the m.room.power_levels event, or if the room contains no m.room.power_levels event.
     */
    public static final String TYPE = "m.room.power_levels";

    @Override
    public String getType() {
        return TYPE;
    }
}
