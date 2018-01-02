package geek.ma1uta.matrix.client.model.search;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class Group {

    public static final String ROOM_ID = "room_id";
    public static final String SENDER = "sender";

    private String key;
}
