package io.github.ma1uta.matrix.client.model.encryption;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class DeviceKeys {

    private String userId;
    private String deviceId;
    private List<String> algorithms;
    private Map<String, String> keys;
    private Map<String, Map<String, String>> signatures;
    private UnsignedDeviceInfo unsignedDeviceInfo;
}
