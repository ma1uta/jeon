package geek.ma1uta.matrix.rest.client.model.encryption;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class UploadRequest {

    private DeviceKeys deviceKeys;
    private Map<String, Object> oneTimeKeys;
}
