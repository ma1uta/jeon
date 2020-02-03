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

package io.github.ma1uta.matrix.event.content;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ma1uta.matrix.event.nested.NotificationPowerLevel;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;
import javax.json.bind.annotation.JsonbProperty;

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
public class RoomPowerLevelsContent implements EventContent {

    /**
     * Default max level if unspecified.
     */
    public static final byte DEFAULT_HIGH_LEVEL = 100;

    /**
     * Default medium level if unspecified.
     */
    public static final byte DEFAULT_MEDIUM_LEVEL = 50;

    /**
     * Default lowest level if unspecified.
     */
    public static final byte DEFAULT_LOW_LEVEL = 0;

    /**
     * The level required to ban a user. Defaults to 50 if unspecified.
     */
    @Schema(
        description = "The level required to ban a user.",
        defaultValue = "50"
    )
    private Byte ban = DEFAULT_MEDIUM_LEVEL;

    /**
     * The level required to send specific event types. This is a mapping from event type to power level required.
     */
    @Schema(
        description = "The level required to send specific event types. This is a mapping from event type to power level required."
    )
    private Map<String, Byte> events;

    /**
     * The default level required to send message events. Can be overridden by the events key. Defaults to 0 if unspecified.
     */
    @Schema(
        name = "events_default",
        description = "The default level required to send message events. Can be overridden by the events key.",
        defaultValue = "0"
    )
    @JsonbProperty("events_default")
    private Byte eventsDefault = DEFAULT_LOW_LEVEL;

    /**
     * The level required to invite a user. Defaults to 50 if unspecified.
     */
    @Schema(
        description = "The level required to invite a user.",
        defaultValue = "50"
    )
    private Byte invite = DEFAULT_MEDIUM_LEVEL;

    /**
     * The level required to kick a user. Defaults to 50 if unspecified.
     */
    @Schema(
        description = "The level required to kick a user.",
        defaultValue = "50"
    )
    private Byte kick = DEFAULT_MEDIUM_LEVEL;

    /**
     * The level required to redact an event. Defaults to 50 if unspecified.
     */
    @Schema(
        description = "The level required to redact an event.",
        defaultValue = "50"
    )
    private Byte redact = DEFAULT_MEDIUM_LEVEL;

    /**
     * The default level required to send state events. Can be overridden by the events key. Defaults to 50 if unspecified,
     * but 0 if there is no m.room.power_levels event at all.
     */
    @Schema(
        name = "state_default",
        description = "The default level required to send state events. Can be overridden"
            + " by the events key. Defaults to 50 if unspecified, but 0 if there is no m.room.power_levels event at all."
    )
    @JsonbProperty("state_default")
    private Byte stateDefault;

    /**
     * The power levels for specific users. This is a mapping from user_id to power level for that user.
     */
    @Schema(
        description = "The power levels for specific users. This is a mapping from user_id to power level for that user."
    )
    private Map<String, Byte> users;

    /**
     * The default power level for every user in the room, unless their user_id is mentioned in the users key. Defaults to 0 if unspecified.
     */
    @Schema(
        name = "users_default",
        description = "The default power level for every user in the room, unless their user_id is mentioned in the users key.",
        defaultValue = "0"
    )
    @JsonbProperty("users_default")
    private Byte usersDefault = DEFAULT_LOW_LEVEL;

    /**
     * The power level requirements for specific notification types. This is a mapping from key to power level for that notifications key.
     */
    @Schema(
        description = "The power level requirements for specific notification types. This is a mapping from key to power level for that"
            + " notifications key."
    )
    private NotificationPowerLevel notifications;

    public Byte getBan() {
        return ban;
    }

    public void setBan(Byte ban) {
        this.ban = ban;
    }

    public Map<String, Byte> getEvents() {
        return events;
    }

    public void setEvents(Map<String, Byte> events) {
        this.events = events;
    }

    @JsonProperty("events_default")
    public Byte getEventsDefault() {
        return eventsDefault;
    }

    public void setEventsDefault(Byte eventsDefault) {
        this.eventsDefault = eventsDefault;
    }

    public Byte getInvite() {
        return invite;
    }

    public void setInvite(Byte invite) {
        this.invite = invite;
    }

    public Byte getKick() {
        return kick;
    }

    public void setKick(Byte kick) {
        this.kick = kick;
    }

    public Byte getRedact() {
        return redact;
    }

    public void setRedact(Byte redact) {
        this.redact = redact;
    }

    @JsonProperty("state_default")
    public Byte getStateDefault() {
        return stateDefault;
    }

    public void setStateDefault(Byte stateDefault) {
        this.stateDefault = stateDefault;
    }

    public Map<String, Byte> getUsers() {
        return users;
    }

    public void setUsers(Map<String, Byte> users) {
        this.users = users;
    }

    @JsonProperty("users_default")
    public Byte getUsersDefault() {
        return usersDefault;
    }

    public void setUsersDefault(Byte usersDefault) {
        this.usersDefault = usersDefault;
    }

    public NotificationPowerLevel getNotifications() {
        return notifications;
    }

    public void setNotifications(NotificationPowerLevel notifications) {
        this.notifications = notifications;
    }
}
