package geek.ma1uta.matrix.rest.client.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class Event {

    private String eventId;
    private String roomId;
    private Map<String, Object> content;
    private Long originServerTs;
    private String sender;
    private String stateKey;
    private String type;
    private Unsigned unsigned;
    private Map<String, Object> prevContent;
    private List<StrippedState> inviteRoomState;
    private Long age;
}
