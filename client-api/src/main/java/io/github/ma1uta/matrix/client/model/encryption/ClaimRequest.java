package io.github.ma1uta.matrix.client.model.encryption;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class ClaimRequest {

    private Long timeout;
    private Map<String, Map<String, String>> oneTimeKeys;
}
