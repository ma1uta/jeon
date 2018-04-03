package io.github.ma1uta.matrix.client.model.eventcontext;

import io.github.ma1uta.matrix.client.model.Event;
import io.github.ma1uta.matrix.client.model.StateEvent;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EventContextResponse {

    private String start;
    private String end;
    private List<Event> eventsBefore;
    private Event event;
    private List<Event> eventsAfter;
    private StateEvent state;
}
