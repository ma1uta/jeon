package geek.ma1uta.matrix.rest.client.model.rest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ThreePidRequest {

    private ThreePidCred[] threePidCreds;
    private Boolean bind;
}
