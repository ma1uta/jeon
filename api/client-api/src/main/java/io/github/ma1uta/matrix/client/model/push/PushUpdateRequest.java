package io.github.ma1uta.matrix.client.model.push;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PushUpdateRequest {

    private List<String> actions;
    private List<PushCondition> conditions;
    private String pattern;
}
