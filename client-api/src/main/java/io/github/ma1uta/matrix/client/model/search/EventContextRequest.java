package io.github.ma1uta.matrix.client.model.search;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventContextRequest {

    private Long beforeLimit;
    private Long afterLimit;
    private Boolean includeProfile;
}
