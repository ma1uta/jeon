package geek.ma1uta.matrix.client.model.sync;

import geek.ma1uta.matrix.client.model.Event;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class State {

    private List<Event> events;
}
