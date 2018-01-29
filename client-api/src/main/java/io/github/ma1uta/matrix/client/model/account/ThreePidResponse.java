package io.github.ma1uta.matrix.client.model.account;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class ThreePidResponse {

    private ThirdPartyIdentifier[] threepids;
}
