package geek.ma1uta.matrix.rest.client.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class Unsigned {

    private Long age;
    private Event redactedBecause;
    private String transactionId;
    private Map<String, Object> prevContent;
}
