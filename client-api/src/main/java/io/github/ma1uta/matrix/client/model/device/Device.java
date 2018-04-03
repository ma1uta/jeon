package io.github.ma1uta.matrix.client.model.device;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Device {

    private String deviceId;
    private String displayName;
    private String lastSeenIp;
    private Long listSeenTs;
}
