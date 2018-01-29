package io.github.ma1uta.matrix.client.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class StrippedState {

    private Map<String, Object> content;
    private String stateKey;
    private String type;
}
