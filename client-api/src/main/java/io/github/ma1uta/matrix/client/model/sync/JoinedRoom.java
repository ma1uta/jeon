package io.github.ma1uta.matrix.client.model.sync;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinedRoom {

    private State state;
    private Timeline timeline;
    private Ephemeral ephemeral;
    private AccountData accountData;
    private UnreadNotificationCounts unreadNotifications;
}
