package geek.ma1uta.matrix.rest.client.model;

import geek.ma1uta.matrix.RoomId;
import geek.ma1uta.matrix.UserId;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class RoomEventFilter {

    private Long limit;
    private List<UserId> notSenders;
    private List<EventType> notTypes;
    private List<UserId> senders;
    private List<EventType> types;
    private List<RoomId> notRooms;
    private List<RoomId> rooms;
    private Boolean containsUrl;
}
