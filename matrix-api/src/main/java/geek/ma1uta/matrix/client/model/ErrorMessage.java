package geek.ma1uta.matrix.client.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class ErrorMessage {

    interface Code {
        String M_FORBIDDEN = "M_FORBIDDEN";
        String M_UNKNOWN_TOKEN = "M_UNKNOWN_TOKEN";
        String M_BAD_JSON = "M_BAD_JSON";
        String M_NOT_JSON = "M_NOT_JSON";
        String M_NOT_FOUND = "M_NOT_FOUND";
        String M_LIMIT_EXCEEDED = "M_LIMIT_EXCEEDED";
        String M_USER_IN_USE = "M_USER_IN_USE";
        String M_INVALID_PASSWORD = "M_INVALID_PASSWORD";
        String M_ROOM_IN_USE = "M_ROOM_IN_USE";
        String M_INVALID_ROOM_STATE = "M_INVALID_ROOM_STATE";
        String M_BAD_PAGINATION = "M_BAD_PAGINATION";
        String M_THREEPID_IN_USE = "M_THREEPID_IN_USE";
        String M_THREEPID_NOT_FOUND = "M_THREEPID_NOT_FOUND";
        String M_SERVER_NOT_TRUSTED = "M_SERVER_NOT_TRUSTED";
    }

    private String errcode;
    private String error;
    private Long retryAfterMs;
}
