package io.github.ma1uta.matrix.client.model.search;

import io.github.ma1uta.matrix.client.model.StateEvent;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class RoomEventResults {

    private Long count;
    private List<Result> results;
    private Map<String, List<StateEvent>> state;
    private Map<String, Map<String, GroupValue>> groups;
    private String nextBatch;
}