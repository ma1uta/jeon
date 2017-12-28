package geek.ma1uta.matrix.model;

public enum EventType implements EnumWithCode {

    ROOM_ALIASES("m.room.aliases"),
    ROOM_CANONICAL_ALIAS("m.room.canonical_alias"),
    ROOM_CREATE("m.room.create"),
    ROOM_JOIN_RULES("m.room.join_rules"),
    ROOM_MEMBER("m.room.member"),
    ROOM_POWER_LEVELS("m.room.power_levels"),
    ROOM_REDACTION("m.room.redaction"),

    ROOM_MESSAGE("m.room.message"),
    ROOM_MESSAGE_FEEDBACK("m.room.message.feedback"),
    ROOM_NAME("m.room.name"),
    ROOM_TOPIC("m.room.topic"),
    ROOM_AVATAR("m.room.avatar"),
    ROOM_PINNED_EVENTS("m.room.pinned_events"),

    CALL_INVITE("m.call.invite"),
    CALL_CANDIDATES("m.call.candidates"),
    CALL_ANSWER("m.call.answer"),
    CALL_HANGUP("m.call.hangup"),

    TYPING("m.typing"),

    RECEIPT("m.receipt"),

    PRESENCE("m.presence"),

    HISTORY_VESIBILITY("m.room.history_visibility"),

    THIRD_PARTY_INVITE("m.room.third_party_invite"),

    GUEST_ACCESS("m.room.guest_access"),

    TAG("m.tag"),

    DIRECT("m.direct");

    private String code;

    EventType(String code) {
        this.code = code;
    }

    @Override
    public String code() {
        return code;
    }
}
