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

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ma1uta.matrix.Event;
import io.github.ma1uta.matrix.EventContent;
import io.github.ma1uta.matrix.events.nested.Invite;

/**
 * Adjusts the membership state for a user in a room. It is preferable to use the membership APIs (/rooms/&lt;room id&gt;/invite etc)
 * when performing membership actions rather than adjusting the state directly as there are a restricted set of valid transformations.
 * For example, user A cannot force user B to join a room, and trying to force this state change directly will fail.
 */
public class RoomMemberEventContent implements EventContent {

    /**
     * The avatar URL for this user, if any. This is added by the homeserver.
     */
    @JsonProperty("avatar_url")
    private String avatarUrl;

    /**
     * The display name for this user, if any. This is added by the homeserver.
     */
    private String displayname;

    /**
     * Required. The membership state of the user. One of: ["invite", "join", "knock", "leave", "ban"].
     */
    private String membership;

    /**
     * Flag indicating if the room containing this event was created with the intention of being a direct chat. See Direct Messaging.
     */
    @JsonProperty("is_direct")
    private Boolean isDirect;

    /**
     * Third-party invites.
     */
    @JsonProperty("third_party_invite")
    private Invite thirdPartyInvite;

    @Override
    public String type() {
        return Event.EventType.ROOM_MEMBER;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public String getMembership() {
        return membership;
    }

    public void setMembership(String membership) {
        this.membership = membership;
    }

    public Boolean getDirect() {
        return isDirect;
    }

    public void setDirect(Boolean direct) {
        isDirect = direct;
    }

    public Invite getThirdPartyInvite() {
        return thirdPartyInvite;
    }

    public void setThirdPartyInvite(Invite thirdPartyInvite) {
        this.thirdPartyInvite = thirdPartyInvite;
    }
}
