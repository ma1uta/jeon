package geek.ma1uta.matrix.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StateEvent extends Event<EventType> {
    private EventContent prevContent;

    private String stateKey;
}
