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

import io.github.ma1uta.matrix.event.CallAnswer;
import io.github.ma1uta.matrix.event.CallCandidates;
import io.github.ma1uta.matrix.event.CallHangup;
import io.github.ma1uta.matrix.event.CallInvite;
import io.github.ma1uta.matrix.event.Direct;
import io.github.ma1uta.matrix.event.Event;
import io.github.ma1uta.matrix.event.ForwardedRoomKey;
import io.github.ma1uta.matrix.event.FullyRead;
import io.github.ma1uta.matrix.event.IgnoredUserList;
import io.github.ma1uta.matrix.event.Presence;
import io.github.ma1uta.matrix.event.RawEvent;
import io.github.ma1uta.matrix.event.Receipt;
import io.github.ma1uta.matrix.event.RoomAliases;
import io.github.ma1uta.matrix.event.RoomAvatar;
import io.github.ma1uta.matrix.event.RoomCanonicalAlias;
import io.github.ma1uta.matrix.event.RoomCreate;
import io.github.ma1uta.matrix.event.RoomEncrypted;
import io.github.ma1uta.matrix.event.RoomEncryption;
import io.github.ma1uta.matrix.event.RoomGuestAccess;
import io.github.ma1uta.matrix.event.RoomHistoryVisibility;
import io.github.ma1uta.matrix.event.RoomJoinRules;
import io.github.ma1uta.matrix.event.RoomKey;
import io.github.ma1uta.matrix.event.RoomKeyRequest;
import io.github.ma1uta.matrix.event.RoomMember;
import io.github.ma1uta.matrix.event.RoomMessage;
import io.github.ma1uta.matrix.event.RoomMessageFeedback;
import io.github.ma1uta.matrix.event.RoomName;
import io.github.ma1uta.matrix.event.RoomPinned;
import io.github.ma1uta.matrix.event.RoomPowerLevels;
import io.github.ma1uta.matrix.event.RoomRedaction;
import io.github.ma1uta.matrix.event.RoomServerAcl;
import io.github.ma1uta.matrix.event.RoomThirdPartyInvite;
import io.github.ma1uta.matrix.event.RoomTopic;
import io.github.ma1uta.matrix.event.Sticker;
import io.github.ma1uta.matrix.event.Tag;
import io.github.ma1uta.matrix.event.Typing;
import io.github.ma1uta.matrix.event.content.RawEventContent;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * Event deserializer.
 */
public abstract class EventDeserializer {

    private static EventDeserializer instance;

    /**
     * Get the event deserializer.
     *
     * @return the event deserializer.
     */
    public static EventDeserializer getInstance() {
        if (instance == null) {
            synchronized (EventDeserializer.class) {
                if (instance == null) {
                    ServiceLoader<EventDeserializer> serviceLoader = ServiceLoader.load(EventDeserializer.class);
                    Iterator<EventDeserializer> iterator = serviceLoader.iterator();
                    if (iterator.hasNext()) {
                        instance = iterator.next();
                        instance.init();
                    } else {
                        throw new RuntimeException("Cannot find the event deserializer. Please put one to the classpath.");
                    }
                }
            }
        }
        return instance;
    }

    /**
     * Initialize the deserializer.
     */
    protected void init() {
    }

    /**
     * Deserialize an event.
     *
     * @param props the event properties.
     * @return the event instance.
     */
    public Event deserialize(Map props) {
        if (props == null) {
            return null;
        }

        String type = (String) props.get("type");

        if (type == null) {
            throw new RuntimeException("Missing the type.");
        }

        switch (type) {
            case CALL_ANSWER:
                return new CallAnswer(props);
            case CALL_CANDIDATES:
                return new CallCandidates(props);
            case CALL_HANGUP:
                return new CallHangup(props);
            case CALL_INVITE:
                return new CallInvite(props);
            case DIRECT:
                return new Direct(props);
            case FORWARDED_ROOM_KEY:
                return new ForwardedRoomKey(props);
            case FULLY_READ:
                return new FullyRead(props);
            case IGNORED_USER_LIST:
                return new IgnoredUserList(props);
            case PRESENCE:
                return new Presence(props);
            case RECEIPT:
                return new Receipt(props);
            case ROOM_ALIASES:
                return new RoomAliases(props);
            case ROOM_AVATAR:
                return new RoomAvatar(props);
            case ROOM_CANONICAL_ALIAS:
                return new RoomCanonicalAlias(props);
            case ROOM_CREATE:
                return new RoomCreate(props);
            case ROOM_GUEST_ACCESS:
                return new RoomGuestAccess(props);
            case ROOM_ENCRIPTION:
                return new RoomEncryption(props);
            case ROOM_ENCRYPTED:
                return new RoomEncrypted(props);
            case ROOM_HISTORY_VISIBILITY:
                return new RoomHistoryVisibility(props);
            case ROOM_JOIN_RULES:
                return new RoomJoinRules(props);
            case ROOM_KEY:
                return new RoomKey(props);
            case ROOM_KEY_REQUEST:
                return new RoomKeyRequest(props);
            case ROOM_MEMBER:
                return new RoomMember(props);
            case ROOM_MESSAGE:
                return new RoomMessage(props);
            case ROOM_MESSAGE_FEEDBACK:
                return new RoomMessageFeedback(props);
            case ROOM_NAME:
                return new RoomName(props);
            case ROOM_PINNED_EVENTS:
                return new RoomPinned(props);
            case ROOM_POWER_LEVELS:
                return new RoomPowerLevels(props);
            case ROOM_REDACTION:
                return new RoomRedaction(props);
            case ROOM_THIRD_PARTY_INVITE:
                return new RoomThirdPartyInvite(props);
            case ROOM_TOPIC:
                return new RoomTopic(props);
            case STICKER:
                return new Sticker(props);
            case TAG:
                return new Tag(props);
            case TYPING:
                return new Typing(props);
            case ROOM_SERVER_ACL:
                return new RoomServerAcl(props);
            default:
                return parse(props, type);
        }
    }

    /**
     * Deserialize an event.
     *
     * @param bytes the event.
     * @return the event instance.
     * @throws IOException when conversion failed.
     */
    public Event deserialize(byte[] bytes) throws IOException {
        return deserialize(bytesToMap(bytes));
    }

    /**
     * Deserialize an event.
     *
     * @param inputStream the event stream.
     * @return the event instance.
     * @throws IOException when conversion failed.
     */
    public Event deserialize(InputStream inputStream) throws IOException {
        return deserialize(streamToMap(inputStream));
    }

    protected Event parse(Map props, String type) {
        RawEvent event = new RawEvent();
        event.setType(type);
        event.setProperties(props);
        event.setContent(new RawEventContent(props));
        return event;
    }

    /**
     * Convert a byte array to the property map.
     *
     * @param bytes byte array.
     * @return the property map.
     * @throws IOException when conversion failed.
     */
    protected abstract Map bytesToMap(byte[] bytes) throws IOException;

    /**
     * Convert a input stream to the property map.
     *
     * @param inputStream the input stream.
     * @return the property map.
     * @throws IOException when conversion failed.
     */
    protected abstract Map streamToMap(InputStream inputStream) throws IOException;
}
