package geek.ma1uta.matrix.rest.client.model.receipt;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class ReceiptRequest {

    interface ReceiptType {
        String READ = "read";
    }

    private String roomId;
    private String receiptType;
    private String eventId;
}
