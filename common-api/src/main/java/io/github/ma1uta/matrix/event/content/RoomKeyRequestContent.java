/*
 * Copyright Anatoliy Sablin tolya@sablin.xyz
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

package io.github.ma1uta.matrix.event.content;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ma1uta.matrix.event.nested.RequestedKeyInfo;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;

/**
 * This event type is used to request keys for end-to-end encryption. It is sent as an unencrypted to-device event.
 */
@Schema(
    description = "This event type is used to request keys for end-to-end encryption. It is sent as an unencrypted to-device event."
)
public class RoomKeyRequestContent implements EventContent {

    /**
     * Request.
     */
    public static final String REQUEST = "request";

    /**
     * Cancel Request.
     */
    public static final String CANCEL_REQUEST = "cancel_request";

    /**
     * Information about the requested key. Required when action is request.
     */
    @Schema(
        description = "Information about the requested key. Required when action is request."
    )
    private RequestedKeyInfo body;

    /**
     * Required. One of: ["request", "cancel_request"].
     */
    @Schema(
        description = "Request action.",
        required = true,
        allowableValues = "request, cancel_request"
    )
    private String action;

    /**
     * Required. ID of the device requesting the key.
     */
    @Schema(
        name = "requesting_device_id",
        description = "ID of the device requesting the key.",
        required = true
    )
    @JsonbProperty("requesting_device_id")
    private String requestingDeviceId;

    /**
     * Required. A random string uniquely identifying the request for a key. If the key is requested multiple times, it should be reused.
     * It should also reused in order to cancel a request.
     */
    @Schema(
        name = "request_id",
        description = "A random string uniquely identifying the request for a key. If the key is requested multiple times,"
            + " it should be reused. It should also reused in order to cancel a request.",
        required = true)
    @JsonbProperty("request_id")
    private String requestId;

    public RequestedKeyInfo getBody() {
        return body;
    }

    public void setBody(RequestedKeyInfo body) {
        this.body = body;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @JsonProperty("requesting_device_id")
    public String getRequestingDeviceId() {
        return requestingDeviceId;
    }

    public void setRequestingDeviceId(String requestingDeviceId) {
        this.requestingDeviceId = requestingDeviceId;
    }

    @JsonProperty("request_id")
    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
