package io.github.ma1uta.matrix.client.model.room;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class ThirdPartySigned {

    private String sender;
    private String mxid;
    private String token;
    private Map<String, Map<String, String>> signatures;
    private ThirdPartySigned signed;
}
