package geek.ma1uta.matrix.client.model.auth;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class AuthenticationStage {

    /** AuthType */
    private String[] stages;
}
