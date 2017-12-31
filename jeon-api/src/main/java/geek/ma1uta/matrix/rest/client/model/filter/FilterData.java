package geek.ma1uta.matrix.rest.client.model.filter;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class FilterData {

    interface EventFormat {
        String CLIENT = "client";
        String FEDERATION = "federation";
    }

    private List<String> eventFields;
    private String eventFormat;
    private Filter presence;
    private Filter accountData;
    private RoomFilter room;
}
