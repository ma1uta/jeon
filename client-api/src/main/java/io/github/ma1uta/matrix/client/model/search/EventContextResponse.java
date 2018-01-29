package io.github.ma1uta.matrix.client.model.search;

import io.github.ma1uta.matrix.client.model.profile.Profile;
import io.github.ma1uta.matrix.client.model.Event;
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
