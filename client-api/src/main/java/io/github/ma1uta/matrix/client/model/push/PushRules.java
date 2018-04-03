package io.github.ma1uta.matrix.client.model.push;

public interface PushRules {

    String MASTER = ".m.rule.master";
    String SUPPRESS_NOTICES = ".m.rule.suppress_notices";
    String INVITE_FOR_ME = ".m.rule.invite_for_me";
    String MEMBER_EVENT = ".m.rule.member_event";
    String CONTAINS_DISPLAY_NAME = ".m.rule.contains_display_name";

    String CONTAINS_USER_NAME = ".m.rule.contains_user_name";

    String CALL = ".m.rule.call";
    String ROOM_ONE_TO_ONE = ".m.rule.room_one_to_one";
    String MESSAGE = ".m.rule.message";
}
