package geek.ma1uta.matrix.client.model.search;

import geek.ma1uta.matrix.client.model.profile.Profile;
import geek.ma1uta.matrix.rest.client.model.Event;
import geek.ma1uta.matrix.rest.client.model.profile.Profile;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class EventContextResponse {

    private String start;
    private String end;
    private Map<String, Profile> profileInfo;
    private List<Event> eventsBefore;
    private List<Event> eventsAfter;
}
