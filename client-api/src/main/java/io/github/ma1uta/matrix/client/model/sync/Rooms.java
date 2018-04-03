package io.github.ma1uta.matrix.client.model.sync;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class Rooms {

    private Map<String, JoinedRoom> join;
    private Map<String, InvitedRoom> invite;
    private Map<String, LeftRoom> leave;
}
