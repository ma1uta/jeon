package geek.ma1uta.matrix.rest.client.model.rest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ThreePidResponse {

    private ThirdPartyIdentifier[] threepids;
}
