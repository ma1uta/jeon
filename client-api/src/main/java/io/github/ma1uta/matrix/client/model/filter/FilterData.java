package io.github.ma1uta.matrix.client.model.filter;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
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
