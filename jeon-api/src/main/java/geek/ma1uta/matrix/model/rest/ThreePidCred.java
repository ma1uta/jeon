package geek.ma1uta.matrix.model.rest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ThreePidCred {

    private String sid;
    private String clientSecret;
    private String idServer;
}
