package io.github.ma1uta.matrix.client.model.sync;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class JoinedRoom {

    private State state;
    private Timeline timeline;
    private Ephemeral ephemeral;
    private AccountData accountData;
    private UnreadNotificationCounts unreadNotifications;
}
