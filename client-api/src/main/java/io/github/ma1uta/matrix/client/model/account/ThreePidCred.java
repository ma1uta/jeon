package io.github.ma1uta.matrix.client.model.account;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ThreePidCred {

    private String sid;
    private String clientSecret;
    private String idServer;
}
