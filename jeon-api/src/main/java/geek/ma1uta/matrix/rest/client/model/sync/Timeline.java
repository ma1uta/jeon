package geek.ma1uta.matrix.rest.client.model.sync;

import geek.ma1uta.matrix.rest.client.model.Event;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class Timeline {

    private List<Event> events;
    private Boolean limited;
    private String prevBatch;
}
