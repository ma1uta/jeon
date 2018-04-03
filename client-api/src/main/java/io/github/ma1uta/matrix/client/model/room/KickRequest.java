package io.github.ma1uta.matrix.client.model.room;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KickRequest {

    private String userId;
    private String reason;
}
