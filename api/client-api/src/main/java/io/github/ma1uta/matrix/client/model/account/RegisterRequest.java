package io.github.ma1uta.matrix.client.model.account;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {

    private AuthenticationData auth;
    private Boolean bindEmail;
    private String username;
    private String password;
    private String deviceId;
    private String initialDeviceDisplayName;
}
