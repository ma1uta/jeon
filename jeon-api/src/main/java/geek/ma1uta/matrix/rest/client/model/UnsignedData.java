package geek.ma1uta.matrix.rest.client.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnsignedData {
    private Long age;

    private Event redactedBecause;

    private String transactionId;
}
