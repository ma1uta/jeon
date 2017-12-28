package geek.ma1uta.matrix.model.rest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {

    private String userId;
    private String accessToken;
    private String homeServer;
    private String deviceId;
}
