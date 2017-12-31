package geek.ma1uta.matrix.rest.client.model.sync;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class SyncRequest {

    public interface Presense {
        String OFFLINE = "offline";
    }

    private String filter;
    private String since;
    private Boolean fullState;
    private String setPresence;
    private Long timeout;
}
