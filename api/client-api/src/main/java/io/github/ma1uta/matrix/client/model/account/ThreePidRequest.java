package io.github.ma1uta.matrix.client.model.account;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ThreePidRequest {

    private ThreePidCred[] threePidCreds;
    private Boolean bind;
}
