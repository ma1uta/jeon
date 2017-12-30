package geek.ma1uta.matrix.rest.client.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class SyncRequest {

    private String filter;
    private String since;
    private Boolean fullState;
    private PresenceType setPresence;
    private Long timeout;
}
