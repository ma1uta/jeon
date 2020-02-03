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

import io.github.ma1uta.matrix.event.content.RoomThirdPartyInviteContent;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Acts as an m.room.member invite event, where there isn't a target user_id to invite. This event contains a token and a
 * public key whose private key must be used to sign the token. Any user who can present that signature may use this invitation
 * to join the target room.
 */
@Schema(
    description = "Acts as an m.room.member invite event, where there isn't a target user_id to invite."
        + " This event contains a token and a public key whose private key must be used to sign the token."
        + " Any user who can present that signature may use this invitation to join the target room."
)
public class RoomThirdPartyInvite extends StateEvent<RoomThirdPartyInviteContent> {

    /**
     * Acts as an m.room.member invite event, where there isn't a target user_id to invite. This event contains a token and
     * a public key whose private key must be used to sign the token. Any user who can present that signature may use this
     * invitation to join the target room.
     */
    public static final String TYPE = "m.room.third_party_invite";

    @Override
    public String getType() {
        return TYPE;
    }
}
