package geek.ma1uta.matrix.model.rest;

import geek.ma1uta.matrix.model.AuthType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationStage {

    private AuthType[] stages;
}
