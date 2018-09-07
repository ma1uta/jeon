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

package io.github.ma1uta.matrix.client.model.account;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * JSON body request to delete 3pid.
 */
@ApiModel(description = "JSON body request to delte 3pid.")
public class Delete3PidRequest {

    /**
     * Required. The medium of the third party identifier being removed. One of: ["email", "msisdn"].
     */
    @ApiModelProperty(
        value = "The medium of the third party identifier being removed",
        required = true,
        allowableValues = "email, msisdn"
    )
    private String medium;

    /**
     * Required. The third party address being removed.
     */
    @ApiModelProperty(
        value = "The third party address neing removed.",
        required = true
    )
    private String address;

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
