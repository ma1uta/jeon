package geek.ma1uta.matrix.client.model.eventcontext;

import geek.ma1uta.matrix.rest.client.model.Event;
import geek.ma1uta.matrix.rest.client.model.StateEvent;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class EventContextResponse {

    private String start;
    private String end;
    private List<Event> eventsBefore;
    private Event event;
    private List<Event> eventsAfter;
    private StateEvent state;
}
