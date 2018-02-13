package io.github.ma1uta.matrix.client.model.search;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class EventContextRequest {

    private Long beforeLimit;
    private Long afterLimit;
    private Boolean includeProfile;
}