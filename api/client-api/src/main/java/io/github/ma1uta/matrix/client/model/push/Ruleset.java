package io.github.ma1uta.matrix.client.model.push;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Ruleset {

    private List<PushRule> content;
    private List<PushRule> override;
    private List<PushRule> room;
    private List<PushRule> sender;
    private List<PushRule> underride;
}
