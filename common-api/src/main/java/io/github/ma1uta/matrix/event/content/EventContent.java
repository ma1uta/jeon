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

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Parent class of all event contents part in the Event.
 */
@Schema(
    description = "Parent class of all event contents part in the Event.",
    subTypes = {
        CallAnswerContent.class,
        CallCandidatesContent.class,
        CallHangupContent.class,
        CallInviteContent.class,
        DirectContent.class,
        FullyReadContent.class,
        IgnoredUserListContent.class,
        PresenceContent.class,
        ReceiptContent.class,
        RoomAliasesContent.class,
        RoomAvatarContent.class,
        RoomCanonicalAliasContent.class,
        RoomCreateContent.class,
        RoomGuestAccessContent.class,
        RoomHistoryVisibilityContent.class,
        RoomJoinRulesContent.class,
        RoomMemberContent.class,
        RoomMessageContent.class,
        RoomNameContent.class,
        RoomPinnedContent.class,
        RoomPowerLevelsContent.class,
        RoomRedactionContent.class,
        RoomThirdPartyInviteContent.class,
        RoomTopicContent.class,
        StickerContent.class,
        TagContent.class,
        TypingContent.class,
        RoomEncryptionContent.class,
        RoomEncryptedContent.class,
        RoomKeyContent.class,
        RoomKeyRequestContent.class,
        ForwardedRoomKeyContent.class,
        RoomServerAclContent.class
    }
)
public interface EventContent {
}
