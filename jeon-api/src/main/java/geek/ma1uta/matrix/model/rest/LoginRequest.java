package geek.ma1uta.matrix.model.rest;

import geek.ma1uta.matrix.model.AuthType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

    private AuthType type;
    private String user;
    private String medium;
    private String address;
    private String password;
    private String token;
    private String deviceId;
    private String initialDeviceDisplayName;
}
