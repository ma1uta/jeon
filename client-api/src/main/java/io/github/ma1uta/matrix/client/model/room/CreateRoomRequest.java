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

package io.github.ma1uta.matrix.client.model.room;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ma1uta.matrix.Event;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * JSON body request for creation api.
 */
@ApiModel(description = "JSON body request for creation api.")
public class CreateRoomRequest {

    /**
     * Visibility.
     */
    public static class Visibility {

        protected Visibility() {
        }

        /**
         * Public.
         */
        public static final String PUBLIC = "public";

        /**
         * Private.
         */
        public static final String PRIVATE = "private";
    }

    /**
     * Presets.
     */
    public static class Preset {

        protected Preset() {
        }

        /**
         * Private.
         */
        public static final String PRIVATE_CHAT = "private_chat";

        /**
         * Public.
         */
        public static final String PUBLIC_CHAT = "public_chat";

        /**
         * Trusted.
         */
        public static final String TRUSTED_PRIVATE_CHAT = "trusted_private_chat";
    }

    /**
     * A public visibility indicates that the room will be shown in the published room list. A private visibility will hide the room
     * from the published room list. Rooms default to private visibility if this key is not included. NB: This should not be confused
     * with join_rules which also uses the word public. One of: ["public", "private"]
     */
    @ApiModelProperty(value = "A public visibility indicates that the room will be shown in the published room list. A private visibility "
        + "will hide the room from the published room list. Rooms default to private visibility if this key is not included. "
        + "NB: This should not be confused with join_rules which also uses the word public.", allowableValues = "[\"public\",\"private\"]")
    private String visibility;

    /**
     * The desired room alias local part. If this is included, a room alias will be created and mapped to the newly created room.
     * The alias will belong on the same homeserver which created the room. For example, if this was set to "foo" and sent to the
     * homeserver "example.com" the complete room alias would be #foo:example.com.
     */
    @ApiModelProperty(name = "room_alias_name", value = "The desired room alias local part. If this is included, a room alias "
        + "will be created and mapped to the newly created room. The alias will belong on the same homeserver which created the "
        + "room. For example, if this was set to \"foo\" and sent to the homeserver \"example.com\" the complete room alias would "
        + "be #foo:example.com.")
    @JsonProperty("room_alias_name")
    private String roomAliasName;

    /**
     * If this is included, an m.room.name event will be sent into the room to indicate the name of the room. See Room Events for
     * more information on m.room.name.
     */
    @ApiModelProperty("If this is included, an m.room.name event will be sent into the room to indicate the name of the room. "
        + "See Room Events for more information on m.room.name.")
    private String name;

    /**
     * If this is included, an m.room.topic event will be sent into the room to indicate the topic for the room. See Room Events for
     * more information on m.room.topic.
     */
    @ApiModelProperty("If this is included, an m.room.topic event will be sent into the room to indicate the topic for the room. "
        + "See Room Events for more information on m.room.topic.")
    private String topic;

    /**
     * A list of user IDs to invite to the room. This will tell the server to invite everyone in the list to the newly created room.
     */
    @ApiModelProperty("A list of user IDs to invite to the room. This will tell the server to invite everyone in the list "
        + "to the newly created room.")
    private List<String> invite;

    /**
     * A list of objects representing third party IDs to invite into the room.
     */
    @ApiModelProperty(name = "invite_3pid", value = "A list of objects representing third party IDs to invite into the room.")
    @JsonProperty("invite_3pid")
    private List<Invite3pid> invite3pid;

    /**
     * Extra keys to be added to the content of the m.room.create. The server will clobber the following keys: creator.
     * Future versions of the specification may allow the server to clobber other keys.
     */
    @ApiModelProperty(name = "creation_content", value = "Extra keys to be added to the content of the m.room.create. "
        + "The server will clobber the following keys: creator. Future versions of the specification may allow the server "
        + "to clobber other keys.")
    @JsonProperty("creation_content")
    private Object creationContent;

    /**
     * A list of state events to set in the new room. This allows the user to override the default state events set in the new room.
     * The expected format of the state events are an object with type, state_key and content keys set.
     * <p/>
     * Takes precedence over events set by presets, but gets overriden by name and topic keys.
     */
    @ApiModelProperty(name = "initial_event", value = "A list of state events to set in the new room. This allows the user to "
        + "override the default state events set in the new room. The expected format of the state events are an object with "
        + "type, state_key and content keys set.")
    @JsonProperty("initial_event")
    private List<Event> initialEvent;

    /**
     * Convenience parameter for setting various default state events based on a preset. Must be either:
     * <p/>
     * private_chat => join_rules is set to invite. history_visibility is set to shared.
     * <p/>
     * trusted_private_chat => join_rules is set to invite. history_visibility is set to shared. All invitees are given the same
     * power level as the room creator.
     * <p/>
     * public_chat: => join_rules is set to public. history_visibility is set to shared. One of: ["private_chat", "public_chat",
     * "trusted_private_chat"]
     */
    @ApiModelProperty(value = "Convenience parameter for setting various default state events based on a preset.",
        allowableValues = "[\"private_chat\",\"public_chat\",\"trusted_private_chat\"]")
    private String preset;

    /**
     * This flag makes the server set the is_direct flag on the m.room.member events sent to the users in invite and invite_3pid.
     * See Direct Messaging for more information.
     */
    @ApiModelProperty(name = "is_direct", value = "This flag makes the server set the is_direct flag on the m.room.member events "
        + "sent to the users in invite and invite_3pid. See Direct Messaging for more information.")
    @JsonProperty("is_direct")
    private Boolean isDirect;

    /**
     * Allows guests to join the room. See Guest Access for more information.
     */
    @ApiModelProperty(name = "guest_can_join", value = "Allows guests to join the room. See Guest Access for more information.")
    @JsonProperty("guest_can_join")
    private Boolean guestCanJoin;

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

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

    public List<Invite3pid> getInvite3pid() {
        return invite3pid;
    }

    public void setInvite3pid(List<Invite3pid> invite3pid) {
        this.invite3pid = invite3pid;
    }

    public Object getCreationContent() {
        return creationContent;
    }

    public void setCreationContent(Object creationContent) {
        this.creationContent = creationContent;
    }

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

    public Boolean getDirect() {
        return isDirect;
    }

    public void setDirect(Boolean direct) {
        isDirect = direct;
    }

    public Boolean getGuestCanJoin() {
        return guestCanJoin;
    }

    public void setGuestCanJoin(Boolean guestCanJoin) {
        this.guestCanJoin = guestCanJoin;
    }
}
