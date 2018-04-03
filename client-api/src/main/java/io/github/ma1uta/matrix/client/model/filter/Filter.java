package io.github.ma1uta.matrix.client.model.filter;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Filter {

    private Long limit;
    private List<String> notSenders;
    private List<String> notTypes;
    private List<String> senders;
    private List<String> types;
}
