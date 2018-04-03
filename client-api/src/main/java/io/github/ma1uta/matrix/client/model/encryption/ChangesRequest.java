package io.github.ma1uta.matrix.client.model.encryption;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangesRequest {

    private String from;
    private String to;
}
