package geek.ma1uta.matrix.rest.client.model;

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
    private Map<AuthType, Map<String, String>> params;
    private String session;
}
