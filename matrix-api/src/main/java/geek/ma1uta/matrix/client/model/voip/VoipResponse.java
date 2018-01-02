package geek.ma1uta.matrix.client.model.voip;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class VoipResponse {

    private String username;
    private String password;
    private List<String> uris;
    private Long ttl;
}
