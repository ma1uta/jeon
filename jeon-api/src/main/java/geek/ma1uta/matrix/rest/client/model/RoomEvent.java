package geek.ma1uta.matrix.rest.client.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomEvent extends Event<EventType> {

    private String eventId;

    private String roomId;

    private String sender;

    private Long originServerTs;

    private UnsignedData unsigned;

    private String stateKey;
}
