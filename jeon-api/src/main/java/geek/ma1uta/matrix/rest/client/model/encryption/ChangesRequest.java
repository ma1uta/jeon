package geek.ma1uta.matrix.rest.client.model.encryption;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class ChangesRequest {

    private String from;
    private String to;
}
