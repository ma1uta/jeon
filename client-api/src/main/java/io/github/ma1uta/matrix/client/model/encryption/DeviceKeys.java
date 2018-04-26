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

import java.util.List;
import java.util.Map;

/**
 * Device Keys.
 */
public class DeviceKeys {

    /**
     * Required. The ID of the user the device belongs to. Must match the user ID used when logging in.
     */
    @JsonProperty("user_id")
    private String userId;

    /**
     * Required. The ID of the device these keys belong to. Must match the device ID used when logging in.
     */
    @JsonProperty("device_id")
    private String deviceId;

    /**
     * Required. The encryption algorithms supported by this device.
     */
    private List<String> algorithms;

    /**
     * Required. Public identity keys. The names of the properties should be in the format <algorithm>:<device_id>.
     * The keys themselves should be encoded as specified by the key algorithm.
     */
    private Map<String, String> keys;

    /**
     * Required. Signatures for the device key object. A map from user ID, to a map from (algorithm):(device_id) to the signature.
     * <p/>
     * The signature is calculated using the process described at Signing JSON.
     */
    private Map<String, Map<String, String>> signatures;

    /**
     * Additional data added to the device key information by intermediate servers, and not covered by the signatures.
     */
    private UnsignedDeviceInfo unsignedDeviceInfo;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public List<String> getAlgorithms() {
        return algorithms;
    }

    public void setAlgorithms(List<String> algorithms) {
        this.algorithms = algorithms;
    }

    public Map<String, String> getKeys() {
        return keys;
    }

    public void setKeys(Map<String, String> keys) {
        this.keys = keys;
    }

    public Map<String, Map<String, String>> getSignatures() {
        return signatures;
    }

    public void setSignatures(Map<String, Map<String, String>> signatures) {
        this.signatures = signatures;
    }

    public UnsignedDeviceInfo getUnsignedDeviceInfo() {
        return unsignedDeviceInfo;
    }

    public void setUnsignedDeviceInfo(UnsignedDeviceInfo unsignedDeviceInfo) {
        this.unsignedDeviceInfo = unsignedDeviceInfo;
    }
}
