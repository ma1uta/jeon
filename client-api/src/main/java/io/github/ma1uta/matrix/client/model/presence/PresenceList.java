package io.github.ma1uta.matrix.client.model.presence;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class PresenceList {

    private List<String> invite;
    private List<String> drop;
}
