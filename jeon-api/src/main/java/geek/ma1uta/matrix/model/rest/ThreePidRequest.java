package geek.ma1uta.matrix.model.rest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ThreePidRequest {

    private ThreePidCred[] threePidCreds;
    private Boolean bind;
}
