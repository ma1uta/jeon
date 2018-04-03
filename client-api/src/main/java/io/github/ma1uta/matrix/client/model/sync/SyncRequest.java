package io.github.ma1uta.matrix.client.model.sync;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SyncRequest {

    public interface Presense {
        String OFFLINE = "offline";
    }

    private String filter;
    private String since;
    private Boolean fullState;
    private String setPresence;
    private Long timeout;
}
