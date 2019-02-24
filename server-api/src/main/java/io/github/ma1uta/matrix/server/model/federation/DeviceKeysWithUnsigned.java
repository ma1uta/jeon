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

import io.github.ma1uta.matrix.server.model.federation.edu.content.nested.DeviceKeys;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Device keys with unsigned info.
 */
@Schema(
    description = "Device keys with unsigned info."
)
public class DeviceKeysWithUnsigned extends DeviceKeys {

    /**
     * Additional data added to the device key information by intermediate servers, and not covered by the signatures.
     */
    @Schema(
        description = "Additional data added to the device key information by intermediate servers, and not covered by the signatures."
    )
    private UnsignedDeviceInfo unsigned;

    public UnsignedDeviceInfo getUnsigned() {
        return unsigned;
    }

    public void setUnsigned(UnsignedDeviceInfo unsigned) {
        this.unsigned = unsigned;
    }
}
