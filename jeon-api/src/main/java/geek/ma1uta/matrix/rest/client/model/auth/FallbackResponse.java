package geek.ma1uta.matrix.rest.client.model.auth;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class FallbackResponse {

    private String session;
}
