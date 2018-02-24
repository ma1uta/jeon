package io.github.ma1uta.matrix.client.model.push;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pusher {

    private String pushkey;
    private String kind;
    private String appId;
    private String appDisplayName;
    private String profileTag;
    private String lang;
    private PusherData data;
}
