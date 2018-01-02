package geek.ma1uta.matrix.client.model.auth;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class LoginRequest {

    /** AuthType */
    private String type;
    private String user;
    private String medium;
    private String address;
    private String password;
    private String token;
    private String deviceId;
    private String initialDeviceDisplayName;
}
