package io.github.ma1uta.matrix.client.model.event;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class RoomMember {

    private String displayName;
    private String avatarUrl;
}
