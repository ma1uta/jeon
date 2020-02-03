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

package io.github.ma1uta.matrix.client.model.room;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ma1uta.matrix.event.Event;
import io.github.ma1uta.matrix.event.content.RoomCreateContent;
import io.github.ma1uta.matrix.event.content.RoomPowerLevelsContent;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import javax.json.bind.annotation.JsonbProperty;

/**
 * JSON body request for creation api.
 */
@Schema(
    description = "JSON body request for creation api."
)
public class CreateRoomRequest {

    /**
     * A public visibility indicates that the room will be shown in the published room list. A private visibility will hide the room
     * from the published room list. Rooms default to private visibility if this key is not included. NB: This should not be confused
     * with join_rules which also uses the word public. One of: ["public", "private"]
     */
    @Schema(
        description = "A public visibility indicates that the room will be shown in the published room list. A private visibility "
            + "will hide the room from the published room list. Rooms default to private visibility if this key is not included. "
            + "NB: This should not be confused with join_rules which also uses the word public.",
        allowableValues = {"public", "private"}
    )
    private String visibility;

    /**
     * The desired room alias local part. If this is included, a room alias will be created and mapped to the newly created room.
     * The alias will belong on the same homeserver which created the room. For example, if this was set to "foo" and sent to the
     * homeserver "example.com". The complete room alias would be #foo:example.com.
     */
    @Schema(
        description = "The desired room alias local part. If this is included, a room alias "
            + "will be created and mapped to the newly created room. The alias will belong on the same homeserver which created the "
            + "room. For example, if this was set to \"foo\" and sent to the homeserver \"example.com\". The complete room alias would "
            + "be #foo:example.com."
    )
    @JsonbProperty("room_alias_name")
    private String roomAliasName;

    /**
     * If this is included, an m.room.name event will be sent into the room to indicate the name of the room. See Room Events for
     * more information on m.room.name.
     */
    @Schema(
        description = "If this is included, an m.room.name event will be sent into the room to indicate the name of the room. "
            + "See Room Events for more information on m.room.name."
    )
    private String name;

    /**
     * If this is included, an m.room.topic event will be sent into the room to indicate the topic for the room. See Room Events for
     * more information on m.room.topic.
     */
    @Schema(
        description = "If this is included, an m.room.topic event will be sent into the room to indicate the topic for the room. "
            + "See Room Events for more information on m.room.topic."
    )
    private String topic;

    /**
     * A list of user IDs to invite to the room. This will tell the server to invite everyone in the list to the newly created room.
     */
    @Schema(
        description = "A list of user IDs to invite to the room. This will tell the server to invite everyone in the list "
            + "to the newly created room."
    )
    private List<String> invite;

    /**
     * A list of objects representing third party IDs to invite into the room.
     */
    @Schema(
        description = "A list of objects representing third party IDs to invite into the room."
    )
    @JsonbProperty("invite_3pid")
    private List<Invite3pid> invite3pid;

    /**
     * The room version to set for the room. If not provided, the homeserver is to use its configured default.
     * If provided, the homeserver will return a 400 error with the errcode M_UNSUPPORTED_ROOM_VERSION if it does not support
     * the room version.
     */
    @Schema(
        description = "The room version to set for the room. If not provided, the homeserver is to use its configured default."
            + " If provided, the homeserver will return a 400 error with the errcode M_UNSUPPORTED_ROOM_VERSION if it does not"
            + " support the room version."
    )
    @JsonbProperty("room_version")
    private String roomVersion;

    /**
     * Extra keys to be added to the content of the m.room.create. The server will clobber the following keys: creator.
     * Future versions of the specification may allow the server to clobber other keys.
     */
    @Schema(
        description = "Extra keys to be added to the content of the m.room.create. "
            + "The server will clobber the following keys: creator. Future versions of the specification may allow the server "
            + "to clobber other keys."
    )
    @JsonbProperty("creation_content")
    private RoomCreateContent creationContent;

    /**
     * A list of state events to set in the new room. This allows the user to override the default state events set in the new room.
     * The expected format of the state events are an object with type, state_key and content keys set.
     * <p/>
     * Takes precedence over events set by preset, but gets overriden by name and topic keys.
     */
    @Schema(
        description = "A list of state events to set in the new room. This allows the user to "
            + "override the default state events set in the new room. The expected format of the state events are an object with "
            + "type, state_key and content keys set."
    )
    @JsonbProperty("initial_event")
    private List<Event> initialEvent;

    /**
     * Convenience parameter for setting various default state events based on a preset.
     */
    @Schema(
        description = "Convenience parameter for setting various default state events based on a preset."
    )
    private String preset;

    /**
     * This flag makes the server set the is_direct flag on the m.room.member events sent to the users in invite and invite_3pid.
     * See DirectContent Messaging for more information.
     */
    @Schema(
        description = "This flag makes the server set the is_direct flag on the m.room.member events "
            + "sent to the users in invite and invite_3pid. See DirectContent Messaging for more information."
    )
    @JsonbProperty("is_direct")
    private Boolean direct;

    /**
     * The power level content to override in the default power level event. This object is applied on top of the generated
     * m.room.power_levels event content prior to it being sent to the room. Defaults to overriding nothing.
     */
    @Schema(
        description = "The power level content to override in the default power level event. This object is applied on top of"
            + " the generated m.room.power_levels event content prior to it being sent to the room. Defaults to overriding nothing."
    )
    @JsonbProperty("power_level_content_override")
    private RoomPowerLevelsContent powerLevelContentOverride;

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    @JsonProperty("room_alias_name")
    public String getRoomAliasName() {
        return roomAliasName;
    }

    public void setRoomAliasName(String roomAliasName) {
        this.roomAliasName = roomAliasName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public List<String> getInvite() {
        return invite;
    }

    public void setInvite(List<String> invite) {
        this.invite = invite;
    }

    @JsonProperty("invite_3pid")
    public List<Invite3pid> getInvite3pid() {
        return invite3pid;
    }

    public void setInvite3pid(List<Invite3pid> invite3pid) {
        this.invite3pid = invite3pid;
    }

    @JsonProperty("room_version")
    public String getRoomVersion() {
        return roomVersion;
    }

    public void setRoomVersion(String roomVersion) {
        this.roomVersion = roomVersion;
    }

    @JsonProperty("creation_content")
    public RoomCreateContent getCreationContent() {
        return creationContent;
    }

    public void setCreationContent(RoomCreateContent creationContent) {
        this.creationContent = creationContent;
    }

    @JsonProperty("initial_event")
    public List<Event> getInitialEvent() {
        return initialEvent;
    }

    public void setInitialEvent(List<Event> initialEvent) {
        this.initialEvent = initialEvent;
    }

    public String getPreset() {
        return preset;
    }

    public void setPreset(String preset) {
        this.preset = preset;
    }

    @JsonProperty("is_direct")
    public Boolean getDirect() {
        return direct;
    }

    public void setDirect(Boolean direct) {
        this.direct = direct;
    }

    @JsonProperty("power_level_content_override")
    public RoomPowerLevelsContent getPowerLevelContentOverride() {
        return powerLevelContentOverride;
    }

    public void setPowerLevelContentOverride(RoomPowerLevelsContent powerLevelContentOverride) {
        this.powerLevelContentOverride = powerLevelContentOverride;
    }
}
