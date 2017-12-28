package geek.ma1uta.matrix.model.rest;

import geek.ma1uta.matrix.model.AuthType;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class AuthenticationFlows {

    private String errcode;
    private String error;
    private String[] completed;
    private AuthenticationStage[] flows;
    private Map<AuthType, Map<String, String>> params;
    private String session;
}
