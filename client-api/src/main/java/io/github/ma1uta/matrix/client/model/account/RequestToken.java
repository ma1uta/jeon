package io.github.ma1uta.matrix.client.model.account;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestToken {

    private String idServer;
    private String clientSecret;
    private String email;
    private String sendAttempt;
}
