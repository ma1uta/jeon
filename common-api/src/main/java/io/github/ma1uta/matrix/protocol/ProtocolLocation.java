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

package io.github.ma1uta.matrix.protocol;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Map;

/**
 * Protocol Location.
 */
@ApiModel(description = "Protocol Location.")
public class ProtocolLocation {

    /**
     * Required. An alias for a matrix room.
     */
    @ApiModelProperty(
        value = "An alias for a matrix room.",
        required = true
    )
    private String alias;

    /**
     * Required. The protocol ID that the third party location is a part of.
     */
    @ApiModelProperty(
        value = "The protocol ID that the third party location is a part of.",
        required = true
    )
    private String protocol;

    /**
     * Required. Information used to identify this third party location.
     */
    @ApiModelProperty(
        value = "Information used to identify this third party location.",
        required = true
    )
    private Map<String, String> fields;

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public Map<String, String> getFields() {
        return fields;
    }

    public void setFields(Map<String, String> fields) {
        this.fields = fields;
    }
}
