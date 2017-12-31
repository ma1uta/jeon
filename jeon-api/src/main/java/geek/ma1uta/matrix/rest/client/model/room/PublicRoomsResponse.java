package geek.ma1uta.matrix.rest.client.model.room;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class PublicRoomsResponse {

    private List<PublicRoomsChunk> chunk;
    private String nextBatch;
    private String prevBatch;
    private Long totalRoomCountEstimate;
}
