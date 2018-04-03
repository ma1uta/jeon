package io.github.ma1uta.matrix.client.model.event;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class JoinedMembersResponse {

    private Map<String, RoomMember> joined;
}
