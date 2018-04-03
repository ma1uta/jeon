package io.github.ma1uta.matrix.client.model.search;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GroupValue {

    private String nextBatch;
    private Long order;
    private List<String> results;
}
