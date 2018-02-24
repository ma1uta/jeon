package io.github.ma1uta.matrix.client.model.encryption;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class UploadResponse {

    private Map<String, Long> oneTimeKeyCounts;
}
