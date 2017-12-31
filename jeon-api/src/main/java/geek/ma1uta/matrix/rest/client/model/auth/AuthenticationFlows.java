package geek.ma1uta.matrix.rest.client.model.auth;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class AuthenticationFlows {

    private String errcode;
    private String error;
    private String[] completed;
    private AuthenticationStage[] flows;
    /** AuthType */
    private Map<String, Map<String, String>> params;
    private String session;
}
