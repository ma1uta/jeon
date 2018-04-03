package io.github.ma1uta.matrix.client.model.voip;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class VoipResponse {

    private String username;
    private String password;
    private List<String> uris;
    private Long ttl;
}
