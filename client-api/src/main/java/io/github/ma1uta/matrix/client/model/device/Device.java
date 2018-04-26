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

package io.github.ma1uta.matrix.client.model.device;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Device.
 */
public class Device {

    /**
     * Required. Identifier of this device.
     */
    @JsonProperty("device_id")
    private String deviceId;

    /**
     * Display name set by the user for this device. Absent if no name has been set.
     */
    @JsonProperty("display_name")
    private String displayName;

    /**
     * The IP address where this device was last seen. (May be a few minutes out of date, for efficiency reasons).
     */
    @JsonProperty("last_seen_ip")
    private String lastSeenIp;

    /**
     * The timestamp (in milliseconds since the unix epoch) when this devices was last seen. (May be a few minutes out of date,
     * for efficiency reasons).
     */
    @JsonProperty("last_seen_ts")
    private Long lastSeenTs;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getLastSeenIp() {
        return lastSeenIp;
    }

    public void setLastSeenIp(String lastSeenIp) {
        this.lastSeenIp = lastSeenIp;
    }

    public Long getLastSeenTs() {
        return lastSeenTs;
    }

    public void setLastSeenTs(Long lastSeenTs) {
        this.lastSeenTs = lastSeenTs;
    }
}
