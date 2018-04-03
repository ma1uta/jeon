package io.github.ma1uta.matrix.client.model.receipt;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReceiptRequest {

    interface ReceiptType {
        String READ = "read";
    }

    private String roomId;
    private String receiptType;
    private String eventId;
}
