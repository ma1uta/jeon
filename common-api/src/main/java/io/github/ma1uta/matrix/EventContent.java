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

package io.github.ma1uta.matrix;

import io.github.ma1uta.matrix.events.CallAnswer;
import io.github.ma1uta.matrix.events.CallCandidates;
import io.github.ma1uta.matrix.events.CallHangup;
import io.github.ma1uta.matrix.events.CallInvite;
import io.github.ma1uta.matrix.events.Direct;
import io.github.ma1uta.matrix.events.IgnoredUserList;
import io.github.ma1uta.matrix.events.Presence;
import io.github.ma1uta.matrix.events.Receipt;
import io.github.ma1uta.matrix.events.RoomAliases;
import io.github.ma1uta.matrix.events.RoomAvatar;
import io.github.ma1uta.matrix.events.RoomCanonicalAlias;
import io.github.ma1uta.matrix.events.RoomCreate;
import io.github.ma1uta.matrix.events.RoomGuestAccess;
import io.github.ma1uta.matrix.events.RoomHistoryVisibility;
import io.github.ma1uta.matrix.events.RoomJoinRules;
import io.github.ma1uta.matrix.events.RoomMember;
import io.github.ma1uta.matrix.events.RoomMessage;
import io.github.ma1uta.matrix.events.RoomName;
import io.github.ma1uta.matrix.events.RoomPinned;
import io.github.ma1uta.matrix.events.RoomPowerLevels;
import io.github.ma1uta.matrix.events.RoomRedaction;
import io.github.ma1uta.matrix.events.RoomThirdPartyInvite;
import io.github.ma1uta.matrix.events.RoomTopic;
import io.github.ma1uta.matrix.events.Sticker;
import io.github.ma1uta.matrix.events.Tag;
import io.github.ma1uta.matrix.events.Typing;
import io.swagger.annotations.ApiModel;

/**
 * Parent class of all event contents part in the Event.
 */
@ApiModel(
    description = "Parent class of all event contents part in the Event.",
    subTypes = {
        CallAnswer.class,
        CallCandidates.class,
        CallHangup.class,
        CallInvite.class,
        Direct.class,
        IgnoredUserList.class,
        Presence.class,
        Receipt.class,
        RoomAliases.class,
        RoomAvatar.class,
        RoomCanonicalAlias.class,
        RoomCreate.class,
        RoomGuestAccess.class,
        RoomHistoryVisibility.class,
        RoomJoinRules.class,
        RoomMember.class,
        RoomMessage.class,
        RoomName.class,
        RoomPinned.class,
        RoomPowerLevels.class,
        RoomRedaction.class,
        RoomThirdPartyInvite.class,
        RoomTopic.class,
        Sticker.class,
        Tag.class,
        Typing.class
    },
    discriminator = "type")
public interface EventContent {
}
