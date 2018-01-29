package io.github.ma1uta.matrix.client.model.search;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class Groupings {

    private List<Group> groupBy;
}
