package io.github.ma1uta.matrix.client.model.sendtodevice;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class SendToDeviceRequest {

    private Map<String, Map<String, Object>> messages;
}
