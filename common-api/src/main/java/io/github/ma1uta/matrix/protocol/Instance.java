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

package io.github.ma1uta.matrix.protocol;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;
import javax.json.bind.annotation.JsonbProperty;

/**
 * Instance.
 */
@Schema(
    description = "Instance."
)
public class Instance {

    /**
     * Required. A human-readable description for the protocol, such as the name.
     */
    @Schema(
        description = "Description.",
        required = true
    )
    private String desc;

    /**
     * An optional content URI representing the protocol. Overrides the one provided at the higher level Protocol object.
     */
    @Schema(
        description = "An optional content URI representing the protocol. Overrides the one provided at the higher level Protocol object."
    )
    private String icon;

    /**
     * Required. Preset values for fields the client may use to search by.
     */
    @Schema(
        description = "Preset values for fields the client may use to search by.",
        required = true
    )
    private Map<String, String> fields;

    /**
     * Required. A unique identifier across all instances.
     */
    @Schema(
        name = "network_id",
        description = "A unique identifier across all instances.",
        required = true
    )
    @JsonbProperty("network_id")
    private String networkId;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Map<String, String> getFields() {
        return fields;
    }

    public void setFields(Map<String, String> fields) {
        this.fields = fields;
    }

    @JsonProperty("network_id")
    public String getNetworkId() {
        return networkId;
    }

    public void setNetworkId(String networkId) {
        this.networkId = networkId;
    }
}
