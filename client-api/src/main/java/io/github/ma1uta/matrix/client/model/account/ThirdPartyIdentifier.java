package io.github.ma1uta.matrix.client.model.account;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ThirdPartyIdentifier {

    private String medium;
    private String address;
}
