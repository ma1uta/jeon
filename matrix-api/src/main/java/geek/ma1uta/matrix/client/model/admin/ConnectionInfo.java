package geek.ma1uta.matrix.client.model.admin;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class ConnectionInfo {

    private String ip;
    private Long lastSeen;
    private String userAgent;
}
