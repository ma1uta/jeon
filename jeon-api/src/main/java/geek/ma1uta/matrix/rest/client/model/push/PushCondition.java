package geek.ma1uta.matrix.rest.client.model.push;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class PushCondition {

    interface Kind {
        String EVENT_MATCH = "event_match";
        String CONTAINS_DISPLAY_NAME = "contains_display_name";
        String ROOM_MEMBER_COUNT = "room_member_count";
    }

    private String kind;
    private String key;
    private String pattern;
    private String is;
}
