package io.github.ma1uta.matrix.client.model.auth;

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
    /** AuthType */
    private Map<String, Map<String, String>> params;
    private String session;
}
