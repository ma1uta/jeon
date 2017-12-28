package geek.ma1uta.matrix.model.rest;

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
