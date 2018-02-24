package io.github.ma1uta.matrix.client.model.sync;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SyncResponse {

    private String nextBatch;
    private Rooms rooms;
    private Presence presence;
    private AccountData accountData;
    private ToDevice toDevice;
    private DeviceLists deviceLists;
}
