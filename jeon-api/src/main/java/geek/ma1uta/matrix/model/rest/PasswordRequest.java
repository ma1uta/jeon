package geek.ma1uta.matrix.model.rest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordRequest {

    private String newPassword;
    private AuthenticationData auth;
}
