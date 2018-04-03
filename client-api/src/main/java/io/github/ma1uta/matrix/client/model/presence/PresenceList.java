package io.github.ma1uta.matrix.client.model.presence;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PresenceList {

    private List<String> invite;
    private List<String> drop;
}
