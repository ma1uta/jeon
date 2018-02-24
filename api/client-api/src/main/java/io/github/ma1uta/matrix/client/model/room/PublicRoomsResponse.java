package io.github.ma1uta.matrix.client.model.room;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PublicRoomsResponse {

    private List<PublicRoomsChunk> chunk;
    private String nextBatch;
    private String prevBatch;
    private Long totalRoomCountEstimate;
}
