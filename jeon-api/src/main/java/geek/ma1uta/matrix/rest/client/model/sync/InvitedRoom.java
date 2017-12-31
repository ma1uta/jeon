package geek.ma1uta.matrix.rest.client.model.sync;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class InvitedRoom {

    private InviteState inviteState;
}
