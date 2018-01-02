package geek.ma1uta.matrix.client.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class Event {

    interface EventType {

        String ROOM_ALIASES = "m.room.aliases";
        String ROOM_CANONICAL_ALIAS = "m.room.canonical_alias";
        String ROOM_CREATE = "m.room.create";
        String ROOM_JOIN_RULES = "m.room.join_rules";
        String ROOM_MEMBER = "m.room.member";
        String ROOM_POWER_LEVELS = "m.room.power_levels";
        String ROOM_REDACTION = "m.room.redaction";

        String ROOM_MESSAGE = "m.room.message";
        String ROOM_MESSAGE_FEEDBACK = "m.room.message.feedback";
        String ROOM_NAME = "m.room.name";
        String ROOM_TOPIC = "m.room.topic";
        String ROOM_AVATAR = "m.room.avatar";
        String ROOM_PINNED_EVENTS = "m.room.pinned_events";

        String CALL_INVITE = "m.call.invite";
        String CALL_CANDIDATES = "m.call.candidates";
        String CALL_ANSWER = "m.call.answer";
        String CALL_HANGUP = "m.call.hangup";

        String TYPING = "m.typing";

        String RECEIPT = "m.receipt";

        String PRESENCE = "m.presence";

        String HISTORY_VESIBILITY = "m.room.history_visibility";

        String THIRD_PARTY_INVITE = "m.room.third_party_invite";

        String GUEST_ACCESS = "m.room.guest_access";

        String TAG = "m.tag";

        String DIRECT = "m.direct";
    }

    interface MessageType {

        String TEXT = "m.text";
        String EMOTE = "m.emote";
        String NOTICE = "m.notice";
        String IMAGE = "m.image";
        String FILE = "m.file";
        String LOCATION = "m.location";
        String VIDEO = "m.video";
        String AUDIO = "m.audio";
    }

    interface MembershipState {
        String INVITE = "invite";
        String JOIN = "join";
        String LEAVE = "leave";
        String BAN = "ban";
        String KNOCK = "knock";
    }

    interface Visibility {
        String WORLD_READABLE = "world_readable";
        String SHARED = "shared";
        String INVITED = "invited";
        String JOINED = "joined";
    }

    private String eventId;
    private String roomId;
    private Map<String, Object> content;
    private Long originServerTs;
    private String sender;
    private String stateKey;
    private String type;
    private Unsigned unsigned;
    private Map<String, Object> prevContent;
    private List<StrippedState> inviteRoomState;
    private Long age;
}
