package geek.ma1uta.matrix.client.model.room;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class InviteRequest {

    private String userId;
    private String idServer;
    private String medium;
    private String address;
}
