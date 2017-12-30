package geek.ma1uta.matrix.rest.client.model.rest;

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
