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

package io.github.ma1uta.matrix.client.model.encryption;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * JSON body parameters for Publishes end-to-end encryption keys for the device.
 */
public class UploadRequest {

    /**
     * Identity keys for the device. May be absent if no new identity keys are required.
     */
    @JsonProperty("device_keys")
    private DeviceKeys deviceKeys;

    /**
     * One-time public keys for "pre-key" messages. The names of the properties should be in the format (algorithm):(key_id).
     * The format of the key is determined by the key algorithm.
     * <p/>
     * May be absent if no new one-time keys are required.
     */
    @JsonProperty("one_time_keys")
    private Map<String, Object> oneTimeKeys;

    public DeviceKeys getDeviceKeys() {
        return deviceKeys;
    }

    public void setDeviceKeys(DeviceKeys deviceKeys) {
        this.deviceKeys = deviceKeys;
    }

    public Map<String, Object> getOneTimeKeys() {
        return oneTimeKeys;
    }

    public void setOneTimeKeys(Map<String, Object> oneTimeKeys) {
        this.oneTimeKeys = oneTimeKeys;
    }
}
