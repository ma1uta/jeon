package geek.ma1uta.matrix.rest.client.model;

import geek.ma1uta.matrix.EventId;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class SendEventResponse {

    private EventId eventId;

}
