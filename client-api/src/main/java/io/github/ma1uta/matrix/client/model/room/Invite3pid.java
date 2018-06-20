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

package io.github.ma1uta.matrix.client.model.room;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Invite 3pid.
 */
@ApiModel(description = "Invite 3pid.")
public class Invite3pid {

    /**
     * Required. The hostname+port of the identity server which should be used for third party identifier lookups.
     */
    @ApiModelProperty(name = "id_server", value = "The hostname+port of the identity server which should be used for third party "
        + "identifier lookups.", required = true)
    @JsonProperty("id_server")
    private String idServer;

    /**
     * Required. The kind of address being passed in the address field, for example email.
     */
    @ApiModelProperty(value = "The kind of address being passed in the address field, for example email.", required = true)
    private String medium;

    /**
     * Required. The invitee's third party identifier.
     */
    @ApiModelProperty(value = "The invitee's third party identifier.", required = true)
    private String address;

    public String getIdServer() {
        return idServer;
    }

    public void setIdServer(String idServer) {
        this.idServer = idServer;
    }

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
