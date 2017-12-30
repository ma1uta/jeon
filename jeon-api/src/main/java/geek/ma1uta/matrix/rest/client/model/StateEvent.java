package geek.ma1uta.matrix.rest.client.model;

import geek.ma1uta.matrix.EventId;
import geek.ma1uta.matrix.RoomId;
import geek.ma1uta.matrix.UserId;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class StateEvent {

    private Object content;
    private EventType type;
    private EventId eventId;
    private RoomId roomId;
    private UserId sender;
    private Long originServerTs;
    private Unsigned unsigned;
    private Map<String, Object> prevContent;
    private String stateKey;
}
