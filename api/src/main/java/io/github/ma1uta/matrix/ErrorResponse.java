package io.github.ma1uta.matrix;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * Any errors which occur at the Matrix API level MUST return a "standard error response".
 */
@Getter
@Setter
@JsonPropertyOrder(alphabetic = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    /**
     * Standard error codes.
     */
    public interface Code {

        /**
         * Forbidden access, e.g. joining a room without permission, failed login.
         */
        String M_FORBIDDEN = "M_FORBIDDEN";

        /**
         * The access token specified was missing.
         */
        String M_MISSING_TOKEN = "M_MISSING_TOKEN";

        /**
         * The access token specified was not recognised.
         */
        String M_UNKNOWN_TOKEN = "M_UNKNOWN_TOKEN";

        /**
         * Request contained valid JSON, but it was malformed in some way, e.g. missing required keys, invalid values for keys.
         */
        String M_BAD_JSON = "M_BAD_JSON";

        /**
         * Request did not contain valid JSON.
         */
        String M_NOT_JSON = "M_NOT_JSON";

        /**
         * No resource was found for this request.
         */
        String M_NOT_FOUND = "M_NOT_FOUND";

        /**
         * Too many requests have been sent in a short period of time. Wait a while then try again.
         */
        String M_LIMIT_EXCEEDED = "M_LIMIT_EXCEEDED";

        /**
         * Encountered when trying to register a user ID which has been taken.
         */
        String M_USER_IN_USE = "M_USER_IN_USE";

        /**
         * Encountered when trying to register a user ID which is not valid.
         */
        String M_INVALID_PASSWORD = "M_INVALID_PASSWORD";

        /**
         * Sent when the room alias given to the createRoom API is already in use.
         */
        String M_ROOM_IN_USE = "M_ROOM_IN_USE";

        /**
         * Sent when the initial state given to the createRoom API is invalid.
         */
        String M_INVALID_ROOM_STATE = "M_INVALID_ROOM_STATE";

        /**
         * Encountered when specifying bad pagination query parameters.
         */
        String M_BAD_PAGINATION = "M_BAD_PAGINATION";

        /**
         * Sent when a threepid given to an API cannot be used because the same threepid is already in use.
         */
        String M_THREEPID_IN_USE = "M_THREEPID_IN_USE";

        /**
         * Sent when a threepid given to an API cannot be used because no record matching the threepid was found.
         */
        String M_THREEPID_NOT_FOUND = "M_THREEPID_NOT_FOUND";

        /**
         * The client's request used a third party server, eg. ID server, that this server does not trust.
         */
        String M_SERVER_NOT_TRUSTED = "M_SERVER_NOT_TRUSTED";

        /**
         * The session of the identity server not found or not validated.
         */
        String M_SESSION_NOT_VALIDATED = "M_SESSION_NOT_VALIDATED";
    }

    private String errcode;
    private String error;
    private Long retryAfterMs;
    private String[] completed;
    private Map<String, Map<String, String>> params;
    private String session;

    public ErrorResponse(String errcode, String error) {
        this.errcode = errcode;
        this.error = error;
    }

    public ErrorResponse(String errcode, String error, Long retryAfterMs) {
        this.errcode = errcode;
        this.error = error;
        this.retryAfterMs = retryAfterMs;
    }
}
