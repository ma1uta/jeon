package io.github.ma1uta.matrix.client.model.push;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PushersRequest extends Pusher {

    private String deviceDisplayName;
    private Boolean append;
}
