package geek.ma1uta.matrix.rest.client.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class LoginRequest {

    private AuthType type;
    private String user;
    private String medium;
    private String address;
    private String password;
    private String token;
    private String deviceId;
    private String initialDeviceDisplayName;
}
