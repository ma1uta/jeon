package geek.ma1uta.matrix.rest.client.model.sync;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class Rooms {

    private Map<String, JoinedRoom> join;
    private Map<String, InvitedRoom> invite;
    private Map<String, LeftRoom> leave;
}
