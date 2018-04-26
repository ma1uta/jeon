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

package io.github.ma1uta.matrix.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

/**
 * Event.
 */
public class Event {

    /**
     * Event types.
     */
    public final class EventType {

        private EventType() {
            //singleton
        }

        /**
         * State Event: state_key - The homeserver domain which owns these room aliases.
         * <p/>
         * This event is sent by a homeserver directly to inform of changes to the list of aliases it knows about for that room.
         * The state_key for this event is set to the homeserver which owns the room alias. The entire set of known aliases for
         * the room is the union of all the m.room.aliases events, one for each homeserver. Clients should check the validity of
         * any room alias given in this list before presenting it to the user as trusted fact. The lists given by this event should
         * be considered simply as advice on which aliases might exist, for which the client can perform the lookup to confirm whether
         * it receives the correct room ID.
         * <p/>
         * <ul>
         * <li>aliases [string] Required. A list of room aliases.</li>
         * </ul>
         */
        public static final String ROOM_ALIASES = "m.room.aliases";

        /**
         * State Event: state_key - A zero-length string.
         * <p/>
         * This event is used to inform the room about which alias should be considered the canonical one. This could be for
         * display purposes or as suggestion to users which alias to use to advertise the room.
         * <p/>
         * A room with an m.room.canonical_alias event with an absent, null, or empty alias field should be treated the same as
         * a room with no m.room.canonical_alias event.
         * <p/>
         * <ul>
         * <li>alias string Required. The canonical alias.</li>
         * </ul>
         */
        public static final String ROOM_CANONICAL_ALIAS = "m.room.canonical_alias";

        /**
         * State Event: state_key - A zero-length string.
         * <p/>
         * This is the first event in a room and cannot be changed. It acts as the root of all other events.
         * <p/>
         * <ul>
         * <li>creator string Required. The user_id of the room creator. This is set by the homeserver.</li>
         * <li>m.federate boolean Whether users on other servers can join this room. Defaults to true if key does not exist.</li>
         * </ul>
         */
        public static final String ROOM_CREATE = "m.room.create";

        /**
         * State Event: state_key - A zero-length string.
         * <p/>
         * A room may be public meaning anyone can join the room without any prior action. Alternatively, it can be invite
         * meaning that a user who wishes to join the room must first receive an invite to the room from someone already inside
         * of the room. Currently, knock and private are reserved keywords which are not implemented.
         * <p/>
         * <ul>
         * <li>join_rule enum Required. The type of rules used for users wishing to join this room. One of: ["public", "knock",
         * "invite", "private"]</li>
         * </ul>
         */
        public static final String ROOM_JOIN_RULES = "m.room.join_rules";

        /**
         * State Event: state_key - The user_id this membership event relates to.
         * <p/>
         * Adjusts the membership state for a user in a room. It is preferable to use the membership APIs
         * (/rooms/&lt;room id&gt;/invite etc)
         * when performing membership actions rather than adjusting the state directly as there are a restricted set of valid
         * transformations. For example, user A cannot force user B to join a room, and trying to force this state change directly
         * will fail.
         * <p/>
         * The following membership states are specified:
         * <ul>
         * <li>invite - The user has been invited to join a room, but has not yet joined it. They may not participate in the room
         * until they join.</li>
         * <li>join - The user has joined the room (possibly after accepting an invite), and may participate in it.</li>
         * <li>leave - The user was once joined to the room, but has since left (possibly by choice, or possibly by being kicked).</li>
         * <li>ban - The user has been banned from the room, and is no longer allowed to join it until they are un-banned from
         * the room (by having their membership state set to a value other than ban).</li>
         * <li>knock - This is a reserved word, which currently has no meaning.</li>
         * </ul>
         * <p/>
         * The third_party_invite property will be set if this invite is an invite event and is the successor
         * of an m.room.third_party_invite event, and absent otherwise.
         * <p/>
         * This event may also include an invite_room_state key outside the content key. If present, this contains an array of
         * StrippedState Events. These events provide information on a subset of state events such as the room name.
         * <p/>
         * EventContent
         * <table>
         * <tr>
         * <td>EventContent Key</td>
         * <td>Type</td>
         * <td>Description</td>
         * </tr>
         * <tr>
         * <td>avatar_url</td>
         * <td>string</td>
         * <td>The avatar URL for this user, if any. This is added by the homeserver.</td>
         * </tr>
         * <tr>
         * <td>displayname</td>
         * <td>string or null</td>
         * <td>The display name for this user, if any. This is added by the homeserver.</td>
         * </tr>
         * <tr>
         * <td>membership</td>
         * <td>enum</td>
         * <td>Required. The membership state of the user. One of: ["invite", "join", "knock", "leave", "ban"]</td>
         * </tr>
         * <tr>
         * <td>is_direct</td>
         * <td>boolean</td>
         * <td>Flag indicating if the room containing this event was created with the intention of being a direct chat.
         * See Direct Messaging.</td>
         * </tr>
         * <tr>
         * <td>third_party_invite</td>
         * <td>Invite</td>
         * <td></td>
         * </tr>
         * </table>
         * <p/>
         * Invite
         * <table>
         * <tr>
         * <td>Invite Key</td>
         * <td>Type</td>
         * <td>Description</td>
         * </tr>
         * <tr>
         * <td>display_name</td>
         * <td>string</td>
         * <td>Required. A name which can be displayed to represent the user instead of their third party identifier</td>
         * <td>signed</td>
         * <td>signed</td>
         * <td>Required. A block of content which has been signed, which servers can use to verify the event.
         * Clients should ignore this.</td>
         * </tr>
         * </table>
         * <p/>
         * signed
         * <table>
         * <tr>
         * <td>signed Key</td>
         * <td>Type</td>
         * <td>Description</td>
         * </tr>
         * <tr>
         * <td>mxid</td>
         * <td>string</td>
         * <td>Required. The invited matrix user ID. Must be equal to the user_id property of the event.</td>
         * </tr>
         * <tr>
         * <td>signatures</td>
         * <td>Signatures</td>
         * <td>Required. A single signature from the verifying server, in the format specified by the Signing Events section of
         * the server-server API.</td>
         * </tr>
         * <tr>
         * <td>token</td>
         * <td>string</td>
         * <td>Required. The token property of the containing third_party_invite object.</td>
         * </tr>
         * </table>
         */
        public static final String ROOM_MEMBER = "m.room.member";

        /**
         * State Event: state_key - A zero-length string.
         * <p/>
         * This event specifies the minimum level a user must have in order to perform a certain action. It also specifies the
         * levels of each user in the room.
         * <p/>
         * If a user_id is in the users list, then that user_id has the associated power level. Otherwise they have the default
         * level users_default. If users_default is not supplied, it is assumed to be 0. If the room contains no m.room.power_levels
         * event, the room's creator has a power level of 100, and all other users have a power level of 0.
         * <p/>
         * The level required to send a certain event is governed by events, state_default and events_default. If an event type is
         * specified in events, then the user must have at least the level specified in order to send that event. If the event type
         * is not supplied, it defaults to events_default for Message Events and state_default for State Events.
         * <p/>
         * If there is no state_default in the m.room.power_levels event, the state_default is 50. If there is no events_default
         * in the m.room.power_levels event, the events_default is 0. If the room contains no m.room.power_levels event, both the
         * state_default and events_default are 0.
         * <p/>
         * The power level required to invite a user to the room, kick a user from the room, ban a user from the room, or redact
         * an event, is defined by invite, kick, ban, and redact, respectively. Each of these levels defaults to 50 if they are
         * not specified in the m.room.power_levels event, or if the room contains no m.room.power_levels event.
         *
         * <ul>
         * <li>ban number The level required to ban a user. Defaults to 50 if unspecified.</li>
         * <li>events {string: number} The level required to send specific event types. This is a mapping from event type to
         * power level required.</li>
         * <li>events_default number The default level required to send message events. Can be overridden by the events key.
         * Defaults to 0 if unspecified.</li>
         * <li>invite number The level required to invite a user. Defaults to 50 if unspecified.</li>
         * <li>kick number The level required to kick a user. Defaults to 50 if unspecified.</li>
         * <li>redact number The level required to redact an event. Defaults to 50 if unspecified.</li>
         * <li>state_default number The default level required to send state events. Can be overridden by the events key.
         * Defaults to 50 if unspecified, but 0 if there is no m.room.power_levels event at all.</li>
         * <li>users {string: number} The power levels for specific users. This is a mapping from user_id to power level for
         * that user.</li>
         * <li>users_default number The default power level for every user in the room, unless their user_id is mentioned
         * in the users key. Defaults to 0 if unspecified.</li>
         * </ul>
         */
        public static final String ROOM_POWER_LEVELS = "m.room.power_levels";

        /**
         * Message Event
         * <p/>
         * Events can be redacted by either room or server admins. Redacting an event means that all keys not required by the
         * protocol are stripped off, allowing admins to remove offensive or illegal content that may have been attached to any
         * event. This cannot be undone, allowing server owners to physically delete the offending data. There is also a concept
         * of a moderator hiding a message event, which can be undone, but cannot be applied to state events. The event that has
         * been redacted is specified in the redacts event level key.
         */
        public static final String ROOM_REDACTION = "m.room.redaction";

        /**
         * This event is used when sending messages in a room. Messages are not limited to be text. The msgtype key outlines
         * the type of message, e.g. text, audio, image, video, etc. The body key is text and MUST be used with every kind of
         * msgtype as a fallback mechanism for when a client cannot render a message. This allows clients to display something
         * even if it is just plain text. For more information on msgtypes, see m.room.message msgtypes.
         *
         * <ul>
         * <li>body string Required. The textual representation of this message.</li>
         * <li>msgtype string Required. The type of message, e.g. m.image, m.text</li>
         * </ul>
         */
        public static final String ROOM_MESSAGE = "m.room.message";

        /**
         * NB: Usage of this event is discouraged in favour of the receipts module. Most clients will not recognise this event.
         * Feedback events are events sent to acknowledge a message in some way. There are two supported acknowledgements: delivered
         * (sent when the event has been received) and read (sent when the event has been observed by the end-user).
         * The target_event_id should reference the m.room.message event being acknowledged.
         *
         * <ul>
         * <li>target_event_id string Required. The event that this feedback is related to.</li>
         * <li>type enum Required. The type of feedback. One of: ["delivered", "read"]</li>
         * </ul>
         * <p/>
         * Usage of this event is discouraged for several reasons:
         * <ul>
         * <li>The number of feedback events will grow very quickly with the number of users in the room. This event provides
         * no way to "batch" feedback, unlike the receipts module.</li>
         * <li>Pairing feedback to messages gets complicated when paginating as feedback arrives before the message
         * it is acknowledging.</li>
         * </ul>
         * <p/>
         * There are no guarantees that the client has seen the event ID being acknowledged.
         */
        public static final String ROOM_MESSAGE_FEEDBACK = "m.room.message.feedback";

        /**
         * State Event: state_key - A zero-length string.
         * <p/>
         * A room has an opaque room ID which is not human-friendly to read. A room alias is human-friendly, but not all
         * rooms have room aliases. The room name is a human-friendly string designed to be displayed to the end-user.
         * The room name is not unique, as multiple rooms can have the same room name set.
         * <p/>
         * A room with an m.room.name event with an absent, null, or empty name field should be treated the same as a room
         * with no m.room.name event.
         * <p/>
         * An event of this type is automatically created when creating a room using /createRoom with the name key.
         * <p/>
         * <ul>
         * <li>name string Required. The name of the room. This MUST NOT exceed 255 bytes.</li>
         * </ul>
         */
        public static final String ROOM_NAME = "m.room.name";

        /**
         * State Event: state_key - A zero-length string.
         * <p/>
         * A topic is a short message detailing what is currently being discussed in the room. It can also be used as a way to
         * display extra information about the room, which may not be suitable for the room name. The room topic can also be set
         * when creating a room using /createRoom with the topic key.
         *
         * <ul>
         * <li>topic string Required. The topic text.</li>
         * </ul>
         */
        public static final String ROOM_TOPIC = "m.room.topic";

        /**
         * State Event: state_key - A zero-length string.
         * <p/>
         * A picture that is associated with the room. This can be displayed alongside the room information.
         *
         * <ul>
         * <li>info ImageInfo Metadata about the image referred to in url.</li>
         * <li>url string Required. The URL to the image.</li>
         * </ul>
         * <p/>
         * ImageInfo
         * <ul>
         * <li>h integer The intended display height of the image in pixels. This may differ from the intrinsic dimensions of the
         * image file.</li>
         * <li>w integer The intended display width of the image in pixels. This may differ from the intrinsic dimensions of the
         * image file.</li>
         * <li>mimetype string The mimetype of the image, e.g. image/jpeg.</li>
         * <li>size integer Size of the image in bytes.</li>
         * <li>thumbnail_url string The URL to a thumbnail of the image.</li>
         * <li>thumbnail_info ThumbnailInfo Metadata about the image referred to in thumbnail_url.</li>
         * </ul>
         * ThumbnailInfo
         * <ul>
         * <li>h integer The intended display height of the image in pixels. This may differ from the intrinsic dimensions of
         * the image file.</li>
         * <li>w integer The intended display width of the image in pixels. This may differ from the intrinsic dimensions of
         * the image file.</li>
         * <li>mimetype string The mimetype of the image, e.g. image/jpeg.</li>
         * <li>size integer Size of the image in bytes.</li>
         * </ul>
         */
        public static final String ROOM_AVATAR = "m.room.avatar";

        /**
         * State Event: state_key - A zero-length string.
         * <p/>
         * This event is used to "pin" particular events in a room for other participants to review later. The order of the
         * pinned events is guaranteed and based upon the order supplied in the event. Clients should be aware that the current
         * user may not be able to see some of the events pinned due to visibility settings in the room. Clients are responsible
         * for determining if a particular event in the pinned list is displayable, and have the option to not display it if it
         * cannot be pinned in the client.
         *
         * <ul>
         * <li>pinned [string] Required. An ordered list of event IDs to pin.</li>
         * </ul>
         */
        public static final String ROOM_PINNED_EVENTS = "m.room.pinned_events";

        /**
         *
         */
        public static final String CALL_INVITE = "m.call.invite";
        public static final String CALL_CANDIDATES = "m.call.candidates";
        public static final String CALL_ANSWER = "m.call.answer";
        public static final String CALL_HANGUP = "m.call.hangup";

        public static final String TYPING = "m.typing";

        public static final String RECEIPT = "m.receipt";

        public static final String PRESENCE = "m.presence";

        public static final String HISTORY_VESIBILITY = "m.room.history_visibility";

        public static final String THIRD_PARTY_INVITE = "m.room.third_party_invite";

        public static final String GUEST_ACCESS = "m.room.guest_access";

        public static final String TAG = "m.tag";

        public static final String DIRECT = "m.direct";
    }

    /**
     * Message types.
     */
    public static final class MessageType {

        private MessageType() {
            //singleton
        }

        /**
         * This message is the most basic message and is used to represent text.
         *
         * <table>
         * <tr>
         * <td>Content Key</td>
         * <td>Type</td>
         * <td>Description</td>
         * </tr>
         * <tr>
         * <td>body</td>
         * <td>string</td>
         * <td>Required. The body of the message.</td>
         * </tr>
         * <tr>
         * <td>msgtype</td>
         * <td>string</td>
         * <td>Required. Must be 'm.text'.</td>
         * </tr>
         * </table>
         */
        public static final String TEXT = "m.text";

        /**
         * This message is similar to m.text except that the sender is 'performing' the action contained in the body key,
         * similar to /me in IRC. This message should be prefixed by the name of the sender. This message could also be
         * represented in a different colour to distinguish it from regular m.text messages.
         *
         * <table>
         * <tr>
         * <td>Content Key</td>
         * <td>Type</td>
         * <td>Description</td>
         * </tr>
         * <tr>
         * <td>body</td>
         * <td>string</td>
         * <td>Required. The emote action to perform.</td>
         * </tr>
         * <tr>
         * <td>msgtype</td>
         * <td>string</td>
         * <td>Required. Must be 'm.emote'.</td>
         * </tr>
         * </table>
         */
        public static final String EMOTE = "m.emote";

        /**
         * The m.notice type is primarily intended for responses from automated clients. An m.notice message must be treated the
         * same way as a regular m.text message with two exceptions. Firstly, clients should present m.notice messages to users
         * in a distinct manner, and secondly, m.notice messages must never be automatically responded to. This helps to prevent
         * infinite-loop situations where two automated clients continuously exchange messages.
         *
         * <table>
         * <tr>
         * <td>Content Key</td>
         * <td>Type</td>
         * <td>Description</td>
         * </tr>
         * <tr>
         * <td>body</td>
         * <td>string</td>
         * <td>Required. The notice text to send.</td>
         * </tr>
         * <tr>
         * <td>msgtype</td>
         * <td>string</td>
         * <td>Required. Must be 'm.notice'.</td>
         * </tr>
         * </table>
         */
        public static final String NOTICE = "m.notice";

        /**
         * This message represents a single image and an optional thumbnail.
         *
         * <table>
         * <tr>
         * <td>Content Key</td>
         * <td>Type</td>
         * <td>Description</td>
         * </tr>
         * <tr>
         * <td>body</td>
         * <td>string</td>
         * <td>Required. A textual representation of the image. This could be the alt text of the image, the filename of the
         * image, or some kind of content description for accessibility e.g. 'image attachment'.</td>
         * </tr>
         * <tr>
         * <td>info</td>
         * <td>ImageInfo</td>
         * <td>Metadata about the image referred to in url.</td>
         * </tr>
         * <tr>
         * <td>msgtype</td>
         * <td>string</td>
         * <td>Required. Must be 'm.image'.</td>
         * </tr>
         * <tr>
         * <td>url</td>
         * <td>string</td>
         * <td>Required. The URL to the image.</td>
         * </tr>
         * </table>
         * <p>
         * ImageInfo
         * <table>
         * <tr>
         * <td>Content Key</td>
         * <td>Type</td>
         * <td>Description</td>
         * </tr>
         * <tr>
         * <td>h</td>
         * <td>integer</td>
         * <td>The intended display height of the image in pixels. This may differ from the intrinsic dimensions of the
         * image file.</td>
         * </tr>
         * <tr>
         * <td>w</td>
         * <td>integer</td>
         * <td>The intended display width of the image in pixels. This may differ from the intrinsic dimensions of the
         * image file.</td>
         * </tr>
         * <tr>
         * <td>mimetype</td>
         * <td>string</td>
         * <td>The mimetype of the image, e.g. image/jpeg.</td>
         * </tr>
         * <tr>
         * <td>thumbnail_url</td>
         * <td>string</td>
         * <td>The URL to a thumbnail of the image.</td>
         * </tr>
         * <tr>
         * <td>thumbnail_info</td>
         * <td>ThumbnailInfo</td>
         * <td>Metadata about the image referred to in thumbnail_url.</td>
         * </tr>
         * </table>
         * <p>
         * ThumbnailInfo
         * <table>
         * <tr>
         * <td>Content Key</td>
         * <td>Type</td>
         * <td>Description</td>
         * </tr>
         * <tr>
         * <td>h</td>
         * <td>integer</td>
         * <td>The intended display height of the image in pixels. This may differ from the intrinsic dimensions of the
         * image file.</td>
         * </tr>
         * <tr>
         * <td>w</td>
         * <td>integer</td>
         * <td>The intended display width of the image in pixels. This may differ from the intrinsic dimensions of the
         * image file.</td>
         * </tr>
         * <tr>
         * <td>mimetype</td>
         * <td>string</td>
         * <td>The mimetype of the image, e.g. image/jpeg.</td>
         * </tr>
         * <tr>
         * <td>size</td>
         * <td>integer</td>
         * <td>Size of the image in bytes.</td>
         * </tr>
         * </table>
         */
        public static final String IMAGE = "m.image";

        /**
         *
         */
        public static final String FILE = "m.file";
        public static final String LOCATION = "m.location";
        public static final String VIDEO = "m.video";
        public static final String AUDIO = "m.audio";
    }

    /**
     * Membership states.
     */
    public static class MembershipState {

        private MembershipState() {
            //singleton
        }

        /**
         * The user has been invited to join a room, but has not yet joined it. They may not participate in the room
         * until they join.
         */
        public static final String INVITE = "invite";

        /**
         * The user has joined the room (possibly after accepting an invite), and may participate in it.
         */
        public static final String JOIN = "join";

        /**
         * The user was once joined to the room, but has since left (possibly by choice, or possibly by being kicked).
         */
        public static final String LEAVE = "leave";

        /**
         * The user has been banned from the room, and is no longer allowed to join it until they are un-banned from
         * the room (by having their membership state set to a value other than ban).
         */
        public static final String BAN = "ban";

        /**
         * This is a reserved word, which currently has no meaning.
         */
        public static final String KNOCK = "knock";
    }

    public static class Visibility {

        private Visibility() {
            //singleton
        }

        public static final String WORLD_READABLE = "world_readable";
        public static final String SHARED = "shared";
        public static final String INVITED = "invited";
        public static final String JOINED = "joined";
    }

    // ---- Common properties ----
    /**
     * The fields in this object will vary depending on the type of event. When interacting with the REST API, this is the HTTP body.
     */
    private Map<String, Object> content;

    /**
     * Required. The type of event. This SHOULD be namespaced similar to Java package naming conventions e.g.
     * 'com.example.subdomain.event.type'
     */
    private String type;

    // ---- Room events ----
    /**
     * Required. The globally unique event identifier.
     */
    @JsonProperty("event_id")
    private String eventId;

    /**
     * Required. The ID of the room associated with this event.
     */
    @JsonProperty("room_id")
    private String roomId;

    /**
     * Required. Contains the fully-qualified ID of the user who sent this event.
     */
    private String sender;

    /**
     * Required. Timestamp in milliseconds on originating homeserver when this event was sent.
     */
    @JsonProperty("origin_server_ts")
    private Long originServerTs;

    /**
     * Contains optional extra information about the event.
     */
    private Unsigned unsigned;

    // ---- State events ----
    /**
     * Optional. The previous content for this event. If there is no previous content, this key will be missing.
     */
    @JsonProperty("prev_content")
    private Map<String, Object> prevContent;

    /**
     * Required. A unique key which defines the overwriting semantics for this piece of room state. This value is often a
     * zero-length string. The presence of this key makes this event a State Event. The key MUST NOT start with '_'.
     */
    @JsonProperty("state_key")
    private String stateKey;

    /**
     * This contains an array of StrippedState Events. These events provide information on a subset of state events such as the room name.
     */
    private List<StrippedState> inviteRoomState;
    private Long age;

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public Map<String, Object> getContent() {
        return content;
    }

    public void setContent(Map<String, Object> content) {
        this.content = content;
    }

    public Long getOriginServerTs() {
        return originServerTs;
    }

    public void setOriginServerTs(Long originServerTs) {
        this.originServerTs = originServerTs;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getStateKey() {
        return stateKey;
    }

    public void setStateKey(String stateKey) {
        this.stateKey = stateKey;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Unsigned getUnsigned() {
        return unsigned;
    }

    public void setUnsigned(Unsigned unsigned) {
        this.unsigned = unsigned;
    }

    public Map<String, Object> getPrevContent() {
        return prevContent;
    }

    public void setPrevContent(Map<String, Object> prevContent) {
        this.prevContent = prevContent;
    }

    public List<StrippedState> getInviteRoomState() {
        return inviteRoomState;
    }

    public void setInviteRoomState(List<StrippedState> inviteRoomState) {
        this.inviteRoomState = inviteRoomState;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }
}
