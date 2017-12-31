package geek.ma1uta.matrix.rest.client.model.room;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class Invite3pid {

    private String idServer;
    private String medium;
    private String address;
}
