package io.github.ma1uta.matrix.client.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class StateEvent {

    private Object content;
    private String type;
    private String eventId;
    private String roomId;
    private String sender;
    private Long originServerTs;
    private Unsigned unsigned;
    private Map<String, Object> prevContent;
    private String stateKey;
}
