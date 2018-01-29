package io.github.ma1uta.matrix.client.model.room;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class JoinedRoomsResponse {

    private List<String> joinedRooms;
}
