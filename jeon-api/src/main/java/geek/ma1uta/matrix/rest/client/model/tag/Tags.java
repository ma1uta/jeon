package geek.ma1uta.matrix.rest.client.model.tag;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class Tags {

    private Map<String, Map<String, Object>> tags;
}
