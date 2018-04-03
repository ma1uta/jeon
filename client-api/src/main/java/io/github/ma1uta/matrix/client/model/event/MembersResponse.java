package io.github.ma1uta.matrix.client.model.event;

import io.github.ma1uta.matrix.client.model.Event;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MembersResponse {

    private List<Event> chunk;
}
