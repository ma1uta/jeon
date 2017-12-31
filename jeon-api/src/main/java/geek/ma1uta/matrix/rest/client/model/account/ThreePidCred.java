package geek.ma1uta.matrix.rest.client.model.account;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class ThreePidCred {

    private String sid;
    private String clientSecret;
    private String idServer;
}
