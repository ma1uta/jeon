package geek.ma1uta.matrix.rest.client.model.event;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class RoomMember {

    private String displayName;
    private String avatarUrl;
}
