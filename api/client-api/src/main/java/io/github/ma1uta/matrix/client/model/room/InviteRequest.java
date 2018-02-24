package io.github.ma1uta.matrix.client.model.room;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InviteRequest {

    private String userId;
    private String idServer;
    private String medium;
    private String address;
}
