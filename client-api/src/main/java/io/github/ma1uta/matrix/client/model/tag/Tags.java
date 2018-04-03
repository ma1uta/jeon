package io.github.ma1uta.matrix.client.model.tag;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class Tags {

    private Map<String, Map<String, Object>> tags;
}
