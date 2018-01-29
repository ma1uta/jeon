package io.github.ma1uta.matrix.client.model.device;

import io.github.ma1uta.matrix.client.model.account.AuthenticationData;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class DeviceDeleteRequest {

    private AuthenticationData auth;
}
