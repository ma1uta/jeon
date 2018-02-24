package io.github.ma1uta.matrix.client.model.presence;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PresenceStatus {

    interface PresenceType {
        String ONLINE = "online";
        String OFFLINE = "offline";
        String UNAVAILABLE = "unavailable";
    }

    private String presence;
    private String statusMsg;
    private Long lastActiveAgo;
    private Boolean currentlyActive;
}
