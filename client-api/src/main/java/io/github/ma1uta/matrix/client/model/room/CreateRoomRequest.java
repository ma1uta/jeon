package io.github.ma1uta.matrix.client.model.room;

import io.github.ma1uta.matrix.client.model.StateEvent;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateRoomRequest {

    interface Visibility {
        String PUBLIC = "public";
        String PRIVATE = "private";
    }

    interface Preset {
        String PRIVATE_CHAT = "private_chat";
        String PUBLIC_CHAT = "public_chat";
        String TRUSTED_PRIVATE_CHAT = "trustet_private_chat";
    }

    private String visibility;
    private String roomAliasName;
    private String name;
    private String topic;
    private List<String> invite;
    private List<Invite3pid> invite3pid;
    private Object creationContent;
    private List<StateEvent> initialEvent;
    private String preset;
    private Boolean isDirect;
}
