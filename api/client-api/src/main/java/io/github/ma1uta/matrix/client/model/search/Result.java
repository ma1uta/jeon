package io.github.ma1uta.matrix.client.model.search;

import io.github.ma1uta.matrix.client.model.Event;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Result {

    private Long rank;
    private Event result;
    private EventContextResponse context;
}
