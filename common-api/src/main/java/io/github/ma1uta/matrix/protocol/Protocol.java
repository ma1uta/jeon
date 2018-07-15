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

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.Map;

/**
 * Protocol metadata.
 */
@ApiModel(description = "Protocola metadata.")
public class Protocol {

    /**
     * Fields used to identify a third party user.
     */
    @ApiModelProperty(name = "user_fields", value = "Fields used to identify a third party user.")
    @JsonProperty("user_fields")
    private List<String> userFields;

    /**
     * Fields used to identify a third party location.
     */
    @ApiModelProperty(name = "location_fields", value = "Fields used to identify a third party location.")
    @JsonProperty("location_fields")
    private List<String> locationFields;

    /**
     * An icon representing the third party protocol.
     */
    @ApiModelProperty("An icon representing the third party protocol.")
    private String icon;

    /**
     * All location or user fields should have an entry here.
     */
    @ApiModelProperty(name = "field_types", value = "All location or user fields should have an entry here.")
    @JsonProperty("field_types")
    private Map<String, FieldMetadata> fieldTypes;

    /**
     * A list of objects representing independent instances of configuration.
     * For instance multiple networks on IRC if multiple are bridged by the same bridge.
     */
    @ApiModelProperty("A list of objects representing independent instances of configuration. For instance multiple networks"
        + "on IRC if multiple are bridged by the same bridge.")
    private List<Instance> instances;

    public List<String> getUserFields() {
        return userFields;
    }

    public void setUserFields(List<String> userFields) {
        this.userFields = userFields;
    }

    public List<String> getLocationFields() {
        return locationFields;
    }

    public void setLocationFields(List<String> locationFields) {
        this.locationFields = locationFields;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Map<String, FieldMetadata> getFieldTypes() {
        return fieldTypes;
    }

    public void setFieldTypes(Map<String, FieldMetadata> fieldTypes) {
        this.fieldTypes = fieldTypes;
    }

    public List<Instance> getInstances() {
        return instances;
    }

    public void setInstances(List<Instance> instances) {
        this.instances = instances;
    }
}
