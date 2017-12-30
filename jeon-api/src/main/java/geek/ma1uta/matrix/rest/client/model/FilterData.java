package geek.ma1uta.matrix.rest.client.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class FilterData {

    private List<String> eventFields;
    private EventFormat eventFormat;
    private Filter presence;
    private Filter accountData;
    private RoomFilter room;
}
