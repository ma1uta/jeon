package geek.ma1uta.matrix.client.model.encryption;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class QueryResponse {

    private Map<String, Object> failures;
    private Map<String, Map<String, DeviceKeys>> deviceKeys;
}
