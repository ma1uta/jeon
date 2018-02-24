package io.github.ma1uta.matrix.client.model.push;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NotificationResponse {

    private String nextToken;
    private List<Notification> notifications;
}
