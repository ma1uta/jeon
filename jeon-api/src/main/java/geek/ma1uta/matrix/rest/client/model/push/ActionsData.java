package geek.ma1uta.matrix.rest.client.model.push;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class ActionsData {

    private List<String> actions;
}