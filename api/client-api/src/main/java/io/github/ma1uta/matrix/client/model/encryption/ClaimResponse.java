package io.github.ma1uta.matrix.client.model.encryption;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class ClaimResponse {

    private Map<String, Object> failures;
    private Map<String, Map<String, Object>> oneTimeKeys;
}
