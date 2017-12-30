package geek.ma1uta.matrix.rest.client.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class RegisterRequest {

    private AuthenticationData auth;
    private Boolean bindEmail;
    private String username;
    private String password;
    private String deviceId;
    private String initialDeviceDisplayName;
}
