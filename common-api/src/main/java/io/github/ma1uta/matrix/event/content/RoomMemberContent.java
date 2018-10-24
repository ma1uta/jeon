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

package io.github.ma1uta.matrix.event.content;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ma1uta.matrix.event.nested.Invite;
import io.github.ma1uta.matrix.event.nested.RoomMemberUnsigned;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;

/**
 * Adjusts the membership state for a user in a room. It is preferable to use the membership APIs (/rooms/&lt;room id&gt;/invite etc)
 * when performing membership actions rather than adjusting the state directly as there are a restricted set of valid transformations.
 * For example, user A cannot force user B to join a room, and trying to force this state change directly will fail.
 */
@Schema(
    description = "Adjusts the membership state for a user in a room. It is preferable to use the membership APIs"
        + " (/rooms/<room id>/invite etc) when performing membership actions rather than adjusting the state directly"
        + " as there are a restricted set of valid transformations. For example, user A cannot force user B to join a room,"
        + " and trying to force this state change directly will fail."
)
public class RoomMemberContent implements EventContent {

    /**
     * The avatar URL for this user, if any. This is added by the homeserver.
     */
    @Schema(
        name = "avatar_url",
        description = "The avatar URL for this user, if any. This is added by the homeserver."
    )
    @JsonbProperty("avatar_url")
    private String avatarUrl;

    /**
     * The display name for this user, if any. This is added by the homeserver.
     */
    @Schema(
        name = "displayname",
        description = "The display name for this user, if any. This is added by the homeserver."
    )
    @JsonbProperty("displayname")
    private String displayName;

    /**
     * Required. The membership state of the user. One of: ["invite", "join", "knock", "leave", "ban"].
     */
    @Schema(
        description = "The membership state of the user.",
        required = true,
        allowableValues = "invite, join, knock, leave, ban"
    )
    private String membership;

    /**
     * Flag indicating if the room containing this event was created with the intention of being a direct chat. See Direct Messaging.
     */
    @Schema(
        name = "is_direct",
        description = "Flag indicating if the room containing this event was created with the intention of being a direct chat."
            + " See Direct Messaging."
    )
    @JsonbProperty("is_direct")
    private Boolean isDirect;

    /**
     * Third-party invites.
     */
    @Schema(
        name = "third_party_invite",
        description = "Third-party invites."
    )
    @JsonbProperty("third_party_invite")
    private Invite thirdPartyInvite;

    /**
     * Contains optional extra information about the event.
     */
    @Schema(
        description = "Contains optional extra information about the event."
    )
    private RoomMemberUnsigned unsigned;

    @JsonProperty("avatar_url")
    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @JsonProperty("displayname")
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getMembership() {
        return membership;
    }

    public void setMembership(String membership) {
        this.membership = membership;
    }

    @JsonProperty("is_direct")
    public Boolean getDirect() {
        return isDirect;
    }

    public void setDirect(Boolean direct) {
        isDirect = direct;
    }

    @JsonProperty("third_party_invite")
    public Invite getThirdPartyInvite() {
        return thirdPartyInvite;
    }

    public void setThirdPartyInvite(Invite thirdPartyInvite) {
        this.thirdPartyInvite = thirdPartyInvite;
    }

    public RoomMemberUnsigned getUnsigned() {
        return unsigned;
    }

    public void setUnsigned(RoomMemberUnsigned unsigned) {
        this.unsigned = unsigned;
    }
}
