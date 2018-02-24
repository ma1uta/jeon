package io.github.ma1uta.matrix.client.model.room;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RoomId {

    private String roomId;
    private List<String> servers;
}
