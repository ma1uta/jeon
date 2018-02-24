package io.github.ma1uta.matrix.client.model.admin;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConnectionInfo {

    private String ip;
    private Long lastSeen;
    private String userAgent;
}
