package geek.ma1uta.matrix.rest.client.model.device;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class DeviceUpdateRequest {

    private String displayName;
}
