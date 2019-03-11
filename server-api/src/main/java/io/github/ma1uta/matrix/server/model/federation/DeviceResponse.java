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

package io.github.ma1uta.matrix.server.model.federation;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import javax.json.bind.annotation.JsonbProperty;

/**
 * Device response.
 */
@Schema(
    description = "Device response."
)
public class DeviceResponse {

    /**
     * Required. The user ID devices were requested for.
     */
    @Schema(
        name = "user_id",
        description = "The user ID devices were requested for.",
        required = true
    )
    @JsonbProperty("user_id")
    private String userId;

    /**
     * Required. A unique ID for a given user_id which describes the version of the returned device list.
     * This is matched with the stream_id field in m.device_list_update EDUs in order to incrementally update the returned device_list.
     */
    @Schema(
        name = "stream_id",
        description = "A unique ID for a given user_id which describes the version of the returned device list. This is matched with"
            + " the stream_id field in m.device_list_update EDUs in order to incrementally update the returned device_list.",
        required = true
    )
    @JsonbProperty("stream_id")
    private Long streamId;

    /**
     * Required. The user's devices. May be empty.
     */
    @Schema(
        description = "The user's devices. May be empty.",
        required = true
    )
    private List<UserDevice> devices;

    @JsonProperty("user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @JsonProperty("stream_id")
    public Long getStreamId() {
        return streamId;
    }

    public void setStreamId(Long streamId) {
        this.streamId = streamId;
    }

    public List<UserDevice> getDevices() {
        return devices;
    }

    public void setDevices(List<UserDevice> devices) {
        this.devices = devices;
    }
}
