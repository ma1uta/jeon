package geek.ma1uta.matrix.model.rest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ThreePidResponse {

    private ThirdPartyIdentifier[] threepids;
}
