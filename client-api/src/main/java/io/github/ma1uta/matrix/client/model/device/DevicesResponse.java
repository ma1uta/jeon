package io.github.ma1uta.matrix.client.model.device;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class DevicesResponse {

    private List<Device> devices;
}
