package io.github.ma1uta.matrix.client.model.push;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PushRule {

    private List<Object> actions;
    private Boolean isDefault;
    private Boolean enabled;
    private String ruleId;
    private List<PushCondition> conditions;
    private String pattern;
}
