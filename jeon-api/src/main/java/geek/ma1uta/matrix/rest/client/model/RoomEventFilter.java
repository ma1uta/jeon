package geek.ma1uta.matrix.rest.client.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class RoomEventFilter {

    private Long limit;
    private List<String> notSenders;
    private List<EventType> notTypes;
    private List<String> senders;
    private List<EventType> types;
    private List<String> notRooms;
    private List<String> rooms;
    private Boolean containsUrl;
}
