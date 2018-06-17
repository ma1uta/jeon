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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * UnsignedDeviceInfo.
 */
@ApiModel(description = "Unsigned device info.")
public class UnsignedDeviceInfo {

    /**
     * The display name which the user set on the device.
     */
    @ApiModelProperty(name = "device_display_name", value = "The display name which the user set on the device.")
    @JsonProperty("device_display_name")
    private String deviceDisplayName;

    public String getDeviceDisplayName() {
        return deviceDisplayName;
    }

    public void setDeviceDisplayName(String deviceDisplayName) {
        this.deviceDisplayName = deviceDisplayName;
    }
}
