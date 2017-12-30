package geek.ma1uta.matrix.rest.client.model.rest;

import geek.ma1uta.matrix.rest.client.model.AuthType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationData {

    private AuthType type;
    private String session;

    private String user;
    private String password;

    private String medium;
    private String address;

    private String response;

    private String token;
    private String txnId;

    private String uri;

    private ThreePidCred[] threePidCreds;
}
