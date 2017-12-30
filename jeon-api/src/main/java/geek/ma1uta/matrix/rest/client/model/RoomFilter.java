package geek.ma1uta.matrix.rest.client.model;

import geek.ma1uta.matrix.RoomId;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class RoomFilter {

    private List<RoomId> notRooms;
    private List<RoomId> rooms;
    private RoomEventFilter ephemeral;
    private Boolean includeLeave;
    private RoomEventFilter state;
    private RoomEventFilter timeline;
    private RoomEventFilter accountData;
}
