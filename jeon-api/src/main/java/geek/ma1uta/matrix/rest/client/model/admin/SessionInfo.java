package geek.ma1uta.matrix.rest.client.model.admin;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class SessionInfo {

    private List<ConnectionInfo> connections;
}