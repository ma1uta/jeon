package geek.ma1uta.matrix.rest.client.model.search;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class EventContextRequest {

    private Long beforeLimit;
    private Long afterLimit;
    private Boolean includeProfile;
}
