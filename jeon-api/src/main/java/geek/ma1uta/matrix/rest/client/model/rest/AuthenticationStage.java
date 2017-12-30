package geek.ma1uta.matrix.rest.client.model.rest;

import geek.ma1uta.matrix.rest.client.model.AuthType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationStage {

    private AuthType[] stages;
}
