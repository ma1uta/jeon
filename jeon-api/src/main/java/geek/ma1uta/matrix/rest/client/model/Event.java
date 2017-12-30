package geek.ma1uta.matrix.rest.client.model;

import geek.ma1uta.matrix.EventId;
import geek.ma1uta.matrix.RoomId;
import geek.ma1uta.matrix.UserId;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class Event {

    private EventId eventId;
    private RoomId roomId;
    private Map<String, Object> content;
    private Long originServerTs;
    private UserId sender;
    private String stateKey;
    private EventType type;
    private Unsigned unsigned;
    private Map<String, Object> prevContent;
    private List<StrippedState> inviteRoomState;
    private Long age;
}
