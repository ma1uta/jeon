package geek.ma1uta.matrix.rest.client.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class StateEvent extends Event<EventType> {
    private Map<String, Object> prevContent;

    private String stateKey;
}
