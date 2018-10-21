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

package io.github.ma1uta.matrix.support;

import static io.github.ma1uta.matrix.event.Event.EventType.CALL_ANSWER;
import static io.github.ma1uta.matrix.event.Event.EventType.CALL_CANDIDATES;
import static io.github.ma1uta.matrix.event.Event.EventType.CALL_HANGUP;
import static io.github.ma1uta.matrix.event.Event.EventType.CALL_INVITE;
import static io.github.ma1uta.matrix.event.Event.EventType.DIRECT;
import static io.github.ma1uta.matrix.event.Event.EventType.FORWARDED_ROOM_KEY;
import static io.github.ma1uta.matrix.event.Event.EventType.FULLY_READ;
import static io.github.ma1uta.matrix.event.Event.EventType.IGNORED_USER_LIST;
import static io.github.ma1uta.matrix.event.Event.EventType.PRESENCE;
import static io.github.ma1uta.matrix.event.Event.EventType.RECEIPT;
import static io.github.ma1uta.matrix.event.Event.EventType.ROOM_ALIASES;
import static io.github.ma1uta.matrix.event.Event.EventType.ROOM_AVATAR;
import static io.github.ma1uta.matrix.event.Event.EventType.ROOM_CANONICAL_ALIAS;
import static io.github.ma1uta.matrix.event.Event.EventType.ROOM_CREATE;
import static io.github.ma1uta.matrix.event.Event.EventType.ROOM_ENCRIPTION;
import static io.github.ma1uta.matrix.event.Event.EventType.ROOM_ENCRYPTED;
import static io.github.ma1uta.matrix.event.Event.EventType.ROOM_GUEST_ACCESS;
import static io.github.ma1uta.matrix.event.Event.EventType.ROOM_HISTORY_VISIBILITY;
import static io.github.ma1uta.matrix.event.Event.EventType.ROOM_JOIN_RULES;
import static io.github.ma1uta.matrix.event.Event.EventType.ROOM_KEY;
import static io.github.ma1uta.matrix.event.Event.EventType.ROOM_KEY_REQUEST;
import static io.github.ma1uta.matrix.event.Event.EventType.ROOM_MEMBER;
import static io.github.ma1uta.matrix.event.Event.EventType.ROOM_MESSAGE;
import static io.github.ma1uta.matrix.event.Event.EventType.ROOM_MESSAGE_FEEDBACK;
import static io.github.ma1uta.matrix.event.Event.EventType.ROOM_NAME;
import static io.github.ma1uta.matrix.event.Event.EventType.ROOM_PINNED_EVENTS;
import static io.github.ma1uta.matrix.event.Event.EventType.ROOM_POWER_LEVELS;
import static io.github.ma1uta.matrix.event.Event.EventType.ROOM_REDACTION;
import static io.github.ma1uta.matrix.event.Event.EventType.ROOM_SERVER_ACL;
import static io.github.ma1uta.matrix.event.Event.EventType.ROOM_THIRD_PARTY_INVITE;
import static io.github.ma1uta.matrix.event.Event.EventType.ROOM_TOPIC;
import static io.github.ma1uta.matrix.event.Event.EventType.STICKER;
import static io.github.ma1uta.matrix.event.Event.EventType.TAG;
import static io.github.ma1uta.matrix.event.Event.EventType.TYPING;

import io.github.ma1uta.matrix.event.content.CallAnswerContent;
import io.github.ma1uta.matrix.event.content.CallCandidatesContent;
import io.github.ma1uta.matrix.event.content.CallHangupContent;
import io.github.ma1uta.matrix.event.content.CallInviteContent;
import io.github.ma1uta.matrix.event.content.DirectContent;
import io.github.ma1uta.matrix.event.content.EventContent;
import io.github.ma1uta.matrix.event.content.ForwardedRoomKeyContent;
import io.github.ma1uta.matrix.event.content.FullyReadContent;
import io.github.ma1uta.matrix.event.content.IgnoredUserListContent;
import io.github.ma1uta.matrix.event.content.PresenceContent;
import io.github.ma1uta.matrix.event.content.RawEventContent;
import io.github.ma1uta.matrix.event.content.ReceiptContent;
import io.github.ma1uta.matrix.event.content.RoomAliasesContent;
import io.github.ma1uta.matrix.event.content.RoomAvatarContent;
import io.github.ma1uta.matrix.event.content.RoomCanonicalAliasContent;
import io.github.ma1uta.matrix.event.content.RoomCreateContent;
import io.github.ma1uta.matrix.event.content.RoomEncryptionContent;
import io.github.ma1uta.matrix.event.content.RoomGuestAccessContent;
import io.github.ma1uta.matrix.event.content.RoomHistoryVisibilityContent;
import io.github.ma1uta.matrix.event.content.RoomJoinRulesContent;
import io.github.ma1uta.matrix.event.content.RoomKeyContent;
import io.github.ma1uta.matrix.event.content.RoomKeyRequestContent;
import io.github.ma1uta.matrix.event.content.RoomMemberContent;
import io.github.ma1uta.matrix.event.content.RoomMessageFeedbackContent;
import io.github.ma1uta.matrix.event.content.RoomNameContent;
import io.github.ma1uta.matrix.event.content.RoomPinnedContent;
import io.github.ma1uta.matrix.event.content.RoomPowerLevelsContent;
import io.github.ma1uta.matrix.event.content.RoomRedactionContent;
import io.github.ma1uta.matrix.event.content.RoomServerAclContent;
import io.github.ma1uta.matrix.event.content.RoomThirdPartyInviteContent;
import io.github.ma1uta.matrix.event.content.RoomTopicContent;
import io.github.ma1uta.matrix.event.content.StickerContent;
import io.github.ma1uta.matrix.event.content.TagContent;
import io.github.ma1uta.matrix.event.content.TypingContent;

import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * The event content deserializer.
 */
public abstract class EventContentDeserializer {

    private static EventContentDeserializer instance;

    /**
     * Get the deserializer instance.
     *
     * @return the deserializer instance.
     */
    public static EventContentDeserializer getInstance() {
        if (instance == null) {
            synchronized (EventContentDeserializer.class) {
                if (instance == null) {
                    ServiceLoader<EventContentDeserializer> serviceLoader = ServiceLoader.load(EventContentDeserializer.class);
                    Iterator<EventContentDeserializer> iterator = serviceLoader.iterator();
                    if (iterator.hasNext()) {
                        instance = iterator.next();
                    } else {
                        throw new RuntimeException("Cannot find the Event content deserializer. Please put one to the classpath.");
                    }
                }
            }
        }
        return instance;
    }

    /**
     * Deserialize a event content.
     *
     * @param props the event content properties.
     * @param type  the event type.
     * @param <C>   the event content class.
     * @return the event content instance.
     */
    @SuppressWarnings("unchecked")
    public <C extends EventContent> C deserialize(Map props, String type) {
        if (props == null) {
            return null;
        }

        if (type == null) {
            throw new RuntimeException("Missing the type.");
        }

        switch (type) {
            case CALL_ANSWER:
                return (C) new CallAnswerContent(props);
            case CALL_CANDIDATES:
                return (C) new CallCandidatesContent(props);
            case CALL_HANGUP:
                return (C) new CallHangupContent(props);
            case CALL_INVITE:
                return (C) new CallInviteContent(props);
            case DIRECT:
                return (C) new DirectContent(props);
            case FORWARDED_ROOM_KEY:
                return (C) new ForwardedRoomKeyContent(props);
            case FULLY_READ:
                return (C) new FullyReadContent(props);
            case IGNORED_USER_LIST:
                return (C) new IgnoredUserListContent(props);
            case PRESENCE:
                return (C) new PresenceContent(props);
            case RECEIPT:
                return (C) new ReceiptContent(props);
            case ROOM_ALIASES:
                return (C) new RoomAliasesContent(props);
            case ROOM_AVATAR:
                return (C) new RoomAvatarContent(props);
            case ROOM_CANONICAL_ALIAS:
                return (C) new RoomCanonicalAliasContent(props);
            case ROOM_CREATE:
                return (C) new RoomCreateContent(props);
            case ROOM_ENCRYPTED:
                return (C) RoomEncryptedDeserializer.getInstance().deserialize(props);
            case ROOM_ENCRIPTION:
                return (C) new RoomEncryptionContent(props);
            case ROOM_GUEST_ACCESS:
                return (C) new RoomGuestAccessContent(props);
            case ROOM_HISTORY_VISIBILITY:
                return (C) new RoomHistoryVisibilityContent(props);
            case ROOM_JOIN_RULES:
                return (C) new RoomJoinRulesContent(props);
            case ROOM_KEY:
                return (C) new RoomKeyContent(props);
            case ROOM_KEY_REQUEST:
                return (C) new RoomKeyRequestContent(props);
            case ROOM_MEMBER:
                return (C) new RoomMemberContent(props);
            case ROOM_MESSAGE:
                return (C) RoomMessageDeserializer.getInstance().deserialize(props);
            case ROOM_MESSAGE_FEEDBACK:
                return (C) new RoomMessageFeedbackContent(props);
            case ROOM_NAME:
                return (C) new RoomNameContent(props);
            case ROOM_PINNED_EVENTS:
                return (C) new RoomPinnedContent(props);
            case ROOM_POWER_LEVELS:
                return (C) new RoomPowerLevelsContent(props);
            case ROOM_REDACTION:
                return (C) new RoomRedactionContent(props);
            case ROOM_SERVER_ACL:
                return (C) new RoomServerAclContent(props);
            case ROOM_THIRD_PARTY_INVITE:
                return (C) new RoomThirdPartyInviteContent(props);
            case ROOM_TOPIC:
                return (C) new RoomTopicContent(props);
            case STICKER:
                return (C) new StickerContent(props);
            case TAG:
                return (C) new TagContent(props);
            case TYPING:
                return (C) new TypingContent(props);
            default:
                return (C) parse(props, type);
        }
    }

    /**
     * Deserialize a event content.
     *
     * @param bytes the event content.
     * @param type  the event type.
     * @return the event content instance.
     */
    public EventContent deserialize(byte[] bytes, String type) {
        return deserialize(bytesToMap(bytes), type);
    }

    /**
     * Deserialize a event content.
     *
     * @param inputStream the event content.
     * @param type        the event type.
     * @return the event content instance.
     */
    public EventContent deserialize(InputStream inputStream, String type) {
        return deserialize(streamToMap(inputStream), type);
    }

    protected EventContent parse(Map props, String type) {
        return new RawEventContent(props);
    }

    /**
     * Convert a byte array to the property map.
     *
     * @param bytes byte array.
     * @return the property map.
     */
    protected abstract Map bytesToMap(byte[] bytes);

    /**
     * Convert a input stream to the property map.
     *
     * @param inputStream the input stream.
     * @return the property map.
     */
    protected abstract Map streamToMap(InputStream inputStream);
}
