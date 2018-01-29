package io.github.ma1uta.matrix.client.model.sync;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class SyncResponse {

    private String nextBatch;
    private Rooms rooms;
    private Presence presence;
    private AccountData accountData;
    private ToDevice toDevice;
    private DeviceLists deviceLists;
}
