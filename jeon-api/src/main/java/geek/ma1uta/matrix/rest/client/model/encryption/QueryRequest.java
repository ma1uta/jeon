package geek.ma1uta.matrix.rest.client.model.encryption;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class QueryRequest {

    private Long timeout;
    private Map<String, List<String>> deviceKeys;
}
