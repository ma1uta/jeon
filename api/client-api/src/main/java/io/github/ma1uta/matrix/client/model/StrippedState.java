package io.github.ma1uta.matrix.client.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class StrippedState {

    private Map<String, Object> content;
    private String stateKey;
    private String type;
}
