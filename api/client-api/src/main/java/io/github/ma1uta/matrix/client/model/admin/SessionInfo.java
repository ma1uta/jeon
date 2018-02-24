package io.github.ma1uta.matrix.client.model.admin;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SessionInfo {

    private List<ConnectionInfo> connections;
}
