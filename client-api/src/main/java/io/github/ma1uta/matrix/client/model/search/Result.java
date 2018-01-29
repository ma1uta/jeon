package io.github.ma1uta.matrix.client.model.search;

import io.github.ma1uta.matrix.client.model.Event;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class Result {

    private Long rank;
    private Event result;
    private EventContextResponse context;
}
