package io.github.ma1uta.matrix.client.model.auth;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class FallbackResponse {

    private String session;
}
