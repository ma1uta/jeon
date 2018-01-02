package geek.ma1uta.matrix.client.model.search;

import geek.ma1uta.matrix.rest.client.model.filter.Filter;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class RoomEvents {

    interface Content {
        String CONTENT_BODY = "content.body";
        String CONTENT_NAME = "content.name";
        String CONTENT_TOPIC = "content.topic";
    }

    interface Order {
        String RECENT = "recent";
        String rank = "rank";
    }

    private String searchTerm;
    private List<String> keys;
    private Filter filter;
    private String orderBy;
    private EventContextRequest eventContext;
    private Boolean includeState;
    private Groupings groupings;
}
