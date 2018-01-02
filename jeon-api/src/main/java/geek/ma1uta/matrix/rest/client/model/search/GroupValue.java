package geek.ma1uta.matrix.rest.client.model.search;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class GroupValue {

    private String nextBatch;
    private Long order;
    private List<String> results;
}
