package io.github.ma1uta.matrix.client.model.account;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordRequest {

    private String newPassword;
    private AuthenticationData auth;
}
