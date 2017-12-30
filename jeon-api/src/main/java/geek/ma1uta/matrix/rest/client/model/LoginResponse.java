package geek.ma1uta.matrix.rest.client.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class LoginResponse {

    private String userId;
    private String accessToken;
    private String homeServer;
    private String deviceId;
}
