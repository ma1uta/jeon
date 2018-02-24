package io.github.ma1uta.matrix.client.model.encryption;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ChangesResponse {

    private List<String> changes;
}
