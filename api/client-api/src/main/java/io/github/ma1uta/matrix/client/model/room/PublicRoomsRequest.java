package io.github.ma1uta.matrix.client.model.room;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PublicRoomsRequest {

    private Long limit;
    private String since;
    private PublicRoomsFilter filter;
}
