package io.github.ma1uta.matrix.client.model.sync;

import io.github.ma1uta.matrix.client.model.Event;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Timeline {

    private List<Event> events;
    private Boolean limited;
    private String prevBatch;
}
