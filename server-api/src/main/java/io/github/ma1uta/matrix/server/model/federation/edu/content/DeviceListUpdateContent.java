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

package io.github.ma1uta.matrix.server.model.federation.edu.content;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ma1uta.matrix.server.model.federation.edu.content.nested.DeviceKeys;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;

/**
 * Device list update.
 */
@Schema(
    description = "Device list update."
)
public class DeviceListUpdateContent implements EphemeralDataUnitContent {

    /**
     * Required. The user ID who owns this device.
     */
    @Schema(
        name = "user_id",
        description = "The user ID who owns this device.",
        required = true
    )
    @JsonbProperty("user_id")
    private String userId;

    /**
     * Required. The ID of the device whose details are changing.
     */
    @Schema(
        name = "device_id",
        description = "The ID of the device whose details are changing.",
        required = true
    )
    @JsonbProperty("device_id")
    private String deviceId;

    /**
     * The public human-readable name of this device. Will be absent if the device has no name.
     */
    @Schema(
        name = "device_display_name",
        description = "The public human-readable name of this device. Will be absent if the device has no name."
    )
    @JsonbProperty("device_display_name")
    private String deviceDisplayName;

    /**
     * Required. An ID sent by the server for this update, unique for a given user_id. Used to identify any gaps in the sequence
     * of m.device_list_update EDUs broadcast by a server.
     */
    @Schema(
        name = "stream_id",
        description = "An ID sent by the server for this update, unique for a given user_id. Used to identify any gaps in the sequence"
            + " of m.device_list_update EDUs broadcast by a server.",
        required = true
    )
    @JsonbProperty("stream_id")
    private String streamId;

    /**
     * The stream_ids of any prior m.device_list_update EDUs sent for this user which have not been referred to already in
     * an EDU's prev_id field. If the receiving server does not recognise any of the prev_ids, it means an EDU has been lost
     * and the server should query a snapshot of the device list via /user/keys/query in order to correctly interpret future
     * m.device_list_update EDUs. May be missing or empty for the first EDU in a sequence.
     */
    @Schema(
        name = "prev_id",
        description = "The stream_ids of any prior m.device_list_update EDUs sent for this user which have not been referred to already in"
            + " an EDU's prev_id field. If the receiving server does not recognise any of the prev_ids, it means an EDU has been lost"
            + " and the server should query a snapshot of the device list via/user/keys/query in order to correctly interpret future"
            + " m.device_list_update EDUs.May be missing or empty for the first EDU in a sequence."
    )
    @JsonbProperty("prev_id")
    private String prevId;

    /**
     * True if the server is announcing that this device has been deleted.
     */
    @Schema(
        description = "True if the server is announcing that this device has been deleted."
    )
    private Boolean deleted;

    /**
     * The updated identity keys (if any) for this device. May be absent if the device has no E2E keys defined.
     */
    @Schema(
        description = "The updated identity keys (if any) for this device. May be absent if the device has no E2E keys defined."
    )
    private DeviceKeys keys;

    @JsonProperty("user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @JsonProperty("device_id")
    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @JsonProperty("device_displat_name")
    public String getDeviceDisplayName() {
        return deviceDisplayName;
    }

    public void setDeviceDisplayName(String deviceDisplayName) {
        this.deviceDisplayName = deviceDisplayName;
    }

    @JsonProperty("stream_id")
    public String getStreamId() {
        return streamId;
    }

    public void setStreamId(String streamId) {
        this.streamId = streamId;
    }

    @JsonProperty("prev_id")
    public String getPrevId() {
        return prevId;
    }

    public void setPrevId(String prevId) {
        this.prevId = prevId;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public DeviceKeys getKeys() {
        return keys;
    }

    public void setKeys(DeviceKeys keys) {
        this.keys = keys;
    }
}
