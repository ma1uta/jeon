package io.github.ma1uta.matrix.client.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class Unsigned {

    private Long age;
    private Event redactedBecause;
    private String transactionId;
    private Map<String, Object> prevContent;
}
