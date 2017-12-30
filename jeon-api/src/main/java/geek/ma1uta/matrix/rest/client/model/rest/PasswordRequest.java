package geek.ma1uta.matrix.rest.client.model.rest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordRequest {

    private String newPassword;
    private AuthenticationData auth;
}
