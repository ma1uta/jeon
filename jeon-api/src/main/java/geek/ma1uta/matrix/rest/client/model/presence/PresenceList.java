package geek.ma1uta.matrix.rest.client.model.presence;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class PresenceList {

    private List<String> invite;
    private List<String> drop;
}
