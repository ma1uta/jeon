/*
 * Copyright sablintolya@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.ma1uta.matrix;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Any errors which occur at the Matrix API level MUST return a "standard error response".
 */
@JsonPropertyOrder(alphabetic = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    /**
     * Standard error codes.
     */
    public static class Code {

        protected Code() {
        }

        /**
         * Forbidden access, e.g. joining a room without permission, failed login.
         */
        public static final String M_FORBIDDEN = "M_FORBIDDEN";

        /**
         * The access token specified was missing.
         */
        public static final String M_MISSING_TOKEN = "M_MISSING_TOKEN";

        /**
         * The access token specified was not recognised.
         */
        public static final String M_UNKNOWN_TOKEN = "M_UNKNOWN_TOKEN";

        /**
         * Request contained valid JSON, but it was malformed in some way, e.g. missing required keys, invalid values for keys.
         */
        public static final String M_BAD_JSON = "M_BAD_JSON";

        /**
         * Request did not contain valid JSON.
         */
        public static final String M_NOT_JSON = "M_NOT_JSON";

        /**
         * No resource was found for this request.
         */
        public static final String M_NOT_FOUND = "M_NOT_FOUND";

        /**
         * Too many requests have been sent in a short period of time. Wait a while then try again.
         */
        public static final String M_LIMIT_EXCEEDED = "M_LIMIT_EXCEEDED";

        /**
         * Encountered when trying to register a user ID which has been taken.
         */
        public static final String M_USER_IN_USE = "M_USER_IN_USE";

        /**
         * Encountered when trying to register a user ID which is not valid.
         */
        public static final String M_INVALID_PASSWORD = "M_INVALID_PASSWORD";

        /**
         * Sent when the room alias given to the createRoom API is already in use.
         */
        public static final String M_ROOM_IN_USE = "M_ROOM_IN_USE";

        /**
         * Sent when the initial state given to the createRoom API is invalid.
         */
        public static final String M_INVALID_ROOM_STATE = "M_INVALID_ROOM_STATE";

        /**
         * Encountered when specifying bad pagination query parameters.
         */
        public static final String M_BAD_PAGINATION = "M_BAD_PAGINATION";

        /**
         * Sent when a threepid given to an API cannot be used because the same threepid is already in use.
         */
        public static final String M_THREEPID_IN_USE = "M_THREEPID_IN_USE";

        /**
         * The client's request used a third party server, eg. ID server, that this server does not trust.
         */
        public static final String M_SERVER_NOT_TRUSTED = "M_SERVER_NOT_TRUSTED";

        /**
         * The session of the identity server not found or not validated.
         */
        public static final String M_SESSION_NOT_VALIDATED = "M_SESSION_NOT_VALIDATED";

        /**
         * The request was missing one or more parameters.
         */
        public static final String M_MISSING_PARAMS = "M_MISSING_PARAMS";

        /**
         * The request contained one or more invalid parameters.
         */
        public static final String M_INVALID_PARAM = "M_INVALID_PARAM";

        /**
         * A session could not be located for the given parameters.
         */
        public static final String M_NO_VALID_SESSION = "M_NO_VALID_SESSION";

        /**
         * The session has expired and must be renewed.
         */
        public static final String M_SESSION_EXPIRED = "M_SESSION_EXPIRED";

        /**
         * The email address provided was not valid.
         */
        public static final String M_INVALID_EMAIL = "M_INVALID_EMAIL";

        /**
         * There was an error sending an email. Typically seen when attempting to verify ownership of a given email address.
         */
        public static final String M_EMAIL_SEND_ERROR = "M_EMAIL_SEND_ERROR";

        /**
         * The provided third party address was not valid.
         */
        public static final String M_INVALID_ADDRESS = "M_INVALID_ADDRESS";

        /**
         * There was an error sending a notification. Typically seen when attempting to verify ownership of a given third party address.
         */
        public static final String M_SEND_ERROR = "M_SEND_ERROR";

        /**
         * The request contained an unrecognised value, such as an unknown token or medium.
         */
        public static final String M_UNRECOGNIZED = "M_UNRECOGNIZED";

        /**
         * An unknown error has occurred.
         */
        public static final String M_UNKNOWN = "M_UNKNOWN";
    }

    /**
     * Error code.
     */
    private String errcode;

    /**
     * Error message.
     */
    private String error;

    /**
     * Time to retry send a request.
     */
    private Long retryAfterMs;

    public ErrorResponse() {
    }

    public ErrorResponse(String errcode, String error) {
        this.errcode = errcode;
        this.error = error;
    }

    public ErrorResponse(String errcode, String error, Long retryAfterMs) {
        this.errcode = errcode;
        this.error = error;
        this.retryAfterMs = retryAfterMs;
    }

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Long getRetryAfterMs() {
        return retryAfterMs;
    }

    public void setRetryAfterMs(Long retryAfterMs) {
        this.retryAfterMs = retryAfterMs;
    }
}
