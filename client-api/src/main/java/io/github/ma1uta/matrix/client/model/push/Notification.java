package io.github.ma1uta.matrix.client.model.push;

import io.github.ma1uta.matrix.client.model.Event;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class Notification {

    private List<Object> actions;
    private Event event;
    private String profileTag;
    private Boolean read;
    private String roomId;
    private Long ts;
}
