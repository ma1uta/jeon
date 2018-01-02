package geek.ma1uta.matrix.client.model.event;

import geek.ma1uta.matrix.rest.client.model.Event;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class MembersResponse {

    private List<Event> chunk;
}
