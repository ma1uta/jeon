package geek.ma1uta.matrix.rest.client.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class Event {

    private String eventId;
    private Map<String, Object> content;
    private Long originServerTs;
    private String sender;
    private String stateKey;
    private EventType type;
    private Unsigned unsigned;
}
