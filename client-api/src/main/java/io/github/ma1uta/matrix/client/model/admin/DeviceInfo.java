package io.github.ma1uta.matrix.client.model.admin;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DeviceInfo {

    private List<SessionInfo> sessions;
}
