package io.github.ma1uta.matrix.client.model.room;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class ThirdPartySigned {

    private String sender;
    private String mxid;
    private String token;
    private Map<String, Map<String, String>> signatures;
    private ThirdPartySigned signed;
}
