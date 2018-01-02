package geek.ma1uta.matrix.rest.client.model.search;

import geek.ma1uta.matrix.rest.client.model.Event;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class Result {

    private Long rank;
    private Event result;
    private EventContextResponse context;
}
