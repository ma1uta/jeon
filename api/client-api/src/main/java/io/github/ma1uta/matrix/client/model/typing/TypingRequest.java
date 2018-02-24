package io.github.ma1uta.matrix.client.model.typing;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TypingRequest {

    private Boolean typing;
    private Long timeout;
}
