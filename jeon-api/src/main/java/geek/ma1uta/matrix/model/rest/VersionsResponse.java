package geek.ma1uta.matrix.model.rest;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class VersionsResponse {

    private String[] versions;
}
